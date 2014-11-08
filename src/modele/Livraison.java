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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoeud() {
		return noeud;
	}

	public void setNoeud(int noeud) {
		this.noeud = noeud;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}
