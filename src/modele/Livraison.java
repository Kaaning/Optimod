package modele;

public class Livraison {

	private int id;
	private Noeud noeud;
	private int client;
	private String etat;
	
	public Livraison(int id, int idClient, Noeud adresse) {
		this.id = id;
		client = idClient;
		noeud = adresse;
		etat="en attente";
	}
	
	public Livraison(int idClient, Noeud adresse) {
		client = idClient;
		noeud = adresse;
		etat="en attente";
	}

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
