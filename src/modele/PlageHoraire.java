package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author H4303 - 2014
 */
public class PlageHoraire {
	
	private Date heureDebut;
	private Date heureFin;
	

	private List<Livraison> livraisons;
	
	/**Constructeur de PlageHoraire
	 * @param heureDebut
	 * @param heureFin
	 */
	public PlageHoraire(Date heureDebut, Date heureFin) {
		livraisons = new ArrayList<Livraison>();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	/**Creer et ajoute une livraison a la liste des livraisons de la plage
	 * @param id : id de la livraison
	 * @param client : numero du client
	 * @param adresse : noeud correspondant
	 */
	public void ajouterLivraison(int id, int client, Noeud adresse){	
		Livraison livraison = new Livraison(id, client,adresse);
		livraisons.add(livraison);
	}

	/**Renvoie la liste des livraisons de la plage
	 * @return List<Livraison>
	 */
	public List<Livraison> getLivraisons(){
		return livraisons;
	}
	
	/**Renvoie l'heure de debut de la plage
	 * @return Date
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}
	/**Renvoie l'heure de fin de la plage
	 * @return Date
	 */
	public Date getHeureFin() {
		return heureFin;
	}
	
	

}
