package modele;

public class Livraison {

	private int id;
	private int noeud;
	private int client;
	private String etat;
	
	public Livraison(int id, int idClient, int idNoeud) {
		this.id = id;
		client = idClient;
		noeud = idNoeud;
		etat="en attente";
	}
	
	public Livraison(int idClient, int idNoeud) {
		client = idClient;
		noeud = idNoeud;
		etat="en attente";
	}
	
	
}
