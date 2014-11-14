package modele;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

import modele.Dijkstra;
import tsp.Graph;
import tsp.TSP;

public class monGraph implements Graph{

	
	Tournee tournee;
	Dijkstra dijkstra;
	int[][] coutLivraison;
	ArrayList<Noeud> memory;

	public monGraph(ZoneGeographique zoneGeographique) {
		 
		this.tournee = zoneGeographique.getTournee();
		this.dijkstra = new Dijkstra(zoneGeographique);
		this.memory = new ArrayList<Noeud>();
		
	}
	
	
	public void calculerCoutLivraison () {
		
		int size = tournee.getLivraison().size();
		int [][] result = new int[size+1][size+1];
				
		for (int i = 0 ; i<size+1 ; i ++ ) {
			for (int j = 0 ; j < size ; j++) {
				if (i == j) {
					result[i][j]=0;
				}
				else {
					result[i][j]= this.getMaxArcCost() + 1;
				}	
			}
		}
				
		ArrayList<PlageHoraire> plages = (ArrayList<PlageHoraire>) tournee.getPlages();
		
		
		// Mise en place de la mémoire tampon 
		
		memory.add(tournee.getZg().getNoeudById(tournee.getEntrepot()));
		for (int i = 0 ; i<size ; i++) {
			memory.add(tournee.getLivraison().get(i).getNoeud());
		}
		
		
		// Construction du graphe
		
		// Point d'entrée : l'entrepot
		for (int i = 0 ; i<plages.get(0).getLivraisons().size(); i++) {
			int cout = dijkstra.getMinCost(tournee.getEntrepot() , plages.get(0).getLivraisons().get(i).getNoeud().getId() );
			result[memory.indexOf(tournee.getZg().getNoeudById(tournee.getEntrepot()))][memory.indexOf(plages.get(0).getLivraisons().get(i).getNoeud())] = cout;
		}
		
		// Livraisons dans les plages horraires
		for (int i=0 ; i< plages.size() ; i++ ) {
			for (int j = 0 ; j< plages.get(i).getLivraisons().size(); j++ ) {
				
				// noeuds de la même plage horaire
				for (int w = 0 ; w < plages.get(i).getLivraisons().size(); w++ ) {
					if (plages.get(i).getLivraisons().get(j).getId() !=  plages.get(i).getLivraisons().get(w).getId() ) {
						
						int cout = dijkstra.getMinCost(plages.get(i).getLivraisons().get(j).getNoeud().getId() , plages.get(i).getLivraisons().get(w).getNoeud().getId() );
						result[memory.indexOf(plages.get(i).getLivraisons().get(j).getNoeud())][memory.indexOf(plages.get(i).getLivraisons().get(w).getNoeud())] = cout;
					}
				}
				
				// noeuds de la plage horaire suivante
				if ( i != plages.size() - 1 ) {
					for (int w = 0 ; w < plages.get(i+1).getLivraisons().size(); w++ ) {
						int cout = dijkstra.getMinCost(plages.get(i).getLivraisons().get(j).getNoeud().getId() , plages.get(i+1).getLivraisons().get(w).getNoeud().getId() );
						result[memory.indexOf(plages.get(i).getLivraisons().get(j).getNoeud())][memory.indexOf(plages.get(i+1).getLivraisons().get(w).getNoeud())] = cout;
					}
				}
				
				// si c'est la derniere plage horaire ça pointe vers l'entrepot
				else {
					int cout = dijkstra.getMinCost(plages.get(i).getLivraisons().get(j).getNoeud().getId() , tournee.getEntrepot() );
					result[memory.indexOf(plages.get(i).getLivraisons().get(j).getNoeud())][memory.indexOf(tournee.getZg().getNoeudById(tournee.getEntrepot()))] = cout;
					
				}
			}
		}
		
		this.coutLivraison = result;
		
	}
	
	public List<Etape> calculerItineraire () {
		
		this.calculerCoutLivraison();
		
		TSP tsp = new TSP(this);
		tsp.solve(Integer.MAX_VALUE, Integer.MAX_VALUE);
		int[] itineraire = tsp.getNext();
		
		List<Noeud> itineraireNoeud = new ArrayList<Noeud>();
		
		int suivant = 0;
		for(int i = 0; i < itineraire.length; i++) {
			itineraireNoeud.add(memory.get(suivant));
			suivant = itineraire[suivant];
		}
		
		itineraireNoeud.add(memory.get(0));
		
		List<Etape> result = new ArrayList<Etape>();
		
		for (int i = 0 ; i < itineraireNoeud.size() - 1 ; i++ ) {
			List<Troncon> chemin = this.dijkstra.getPath(itineraireNoeud.get(i), itineraireNoeud.get(i+1));
			result.add(new Etape(chemin , "A faire"));
		}
		
		return result;
	}
	
	@Override
	public int getMaxArcCost() {
		return this.dijkstra.getMaxArcCost();
	}

	@Override
	public int getMinArcCost() {
		return this.dijkstra.getMinArcCost();
	}

	@Override
	public int getNbVertices() {
		return this.coutLivraison.length;
	}

	@Override
	public int[][] getCost() {
		return this.coutLivraison;
	}

	@Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException {
		List<Integer> succ = new ArrayList<Integer>();
		for(int j = 0; j < this.coutLivraison[i].length; j++) {
			if(this.coutLivraison[i][j] != 0 || this.coutLivraison[i][j] != this.getMaxArcCost()+1) {
				succ.add(j);
			}
		}
		int[] res = new int[succ.size()];
		for(int j = 0; j < res.length; j++) {
			res[j] = succ.get(j);
		}
		return res;
	
	}

	@Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		return getSucc(i).length;
	}
	

}
