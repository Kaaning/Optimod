package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlageHoraire {
	
	private Date heureDebut;
	private Date heureFin;
	private List<Livraison> livraisons;
	
	public PlageHoraire(Date heureDebut, Date heureFin) {
		livraisons = new ArrayList<Livraison>();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public void ajouterLivraison(int id, int client, int adresse){
		
		Livraison livraison = new Livraison(id, client, adresse);
		livraisons.add(livraison);

	}
	
	
	

}
