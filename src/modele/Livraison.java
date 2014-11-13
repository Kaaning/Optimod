package modele;

public class Livraison {

	private int id;
	private Noeud noeud;
	private int client;
	private String etat;
	
	public Livraison(int id, int idClient, Noeud noeud) {
		this.id = id;
		client = idClient;
		this.noeud=noeud;
		etat="en attente";
	}
	
//	public Livraison(int idClient, int idNoeud) {
//		client = idClient;
//		noeud = idNoeud;
//		etat="en attente";
//	}

	public int getId() {
		return id;
	}

	public Noeud getNoeud() {
		return noeud;
	}

	public int getClient() {
		return client;
	}

	public String getEtat() {
		return etat;
	}
	
	
}
