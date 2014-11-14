package modele;
/**
 * @author H4303 - 2014
 */
public class Livraison {

	private int id;
	private Noeud noeud;
	private int client;
	private String etat;
	
	/**
	 * Constructeur de livraison
	 * @param id : numero d'identification de la livraison
	 * @param idClient : numero d'identification du client
	 * @param noeud : noeud a l'adresse de la livraison
	 */
	public Livraison(int id, int idClient, Noeud noeud) {
		this.id = id;
		client = idClient;
		this.noeud=noeud;
		etat="en attente";
	}

	/**Accesseur du numéro d'identification
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**Accesseur du noeud de la livraison
	 * @return Noeud
	 */
	public Noeud getNoeud() {
		return noeud;
	}
	/**Accesseur du client de la livraison
	 * @return int
	 */
	public int getClient() {
		return client;
	}
	/**Accesseur de l'etat de la livraison
	 * @return String
	 */
	public String getEtat() {
		return etat;
	}
	
	
}
