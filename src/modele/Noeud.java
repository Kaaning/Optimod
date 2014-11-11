/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Gestion des Noeuds
 * @author MohamedRiadh
 *
 */
public class Noeud {
	private int id;
	private int x;
	private int y;
	private List<Troncon> tronconsSortans = new ArrayList<Troncon>();
	private List<Troncon> tronconsEntrants = new ArrayList<Troncon>();
	private boolean visite=false;
	
	/**
	 * Constructeur sans paramètres de l'Objet Noeud
	 */
	public Noeud() {
	}
	
	/**
	 * Constructeur avec paramètres de l'Objet Noeud
	 * @param unId l'ID du Noeud à construire et ses coordonnées X et Y dans le plan
	 * @param unX l'absisse X du Noeud dans le plan
	 * @param unY l'oordonnée Y du Noeud dans le plan
	 */
	public Noeud(int unId, int unX, int unY) {
		id = unId;
		x = unX;
		y = unY;
	}
	
	/**
	 * Ajoute un objet troncon à la liste des troncons sortants du Noeud 
	 * @param troncon L'Objet Troncon à rajouter 
	 *
	 */
	public void ajouterTronconSortant(Troncon troncon) {
		this.tronconsSortans.add(troncon);
	}
	
	/**
	 * Ajoute un objet troncon à la liste des troncons entrants du Noeud
	 * @param troncon L'Objet Troncon à rajouter
	 *
	 */
	public void ajouterTronconEntrants(Troncon troncon) {
		this.tronconsEntrants.add(troncon);
	}
	
	/**
	 * Accesseur de l'Attribut tronconsEntrants
	 * @return List<Troncon> Liste des Objets Troncons entrants de Noeud
	 *
	 */
	public List<Troncon> getTronconsEntrants() {
		return this.tronconsEntrants;
	}
	
	/**
	 * Accesseur de l'Attribut tronconsSortants
	 * @return List<Troncon> Liste des Objets Troncons sortants de Noeud
	 *
	 */
	public List<Troncon> getTronconsSortants() {
		return this.tronconsSortans;
	}
	
	
	/**
	 * Accesseur de l'Attribut Id représantant l'Identifiant du Noeud
	 * @return int la valeur de l'Attribut Id
	 *
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Accesseur de l'Attribut X représantant l'absisse du Noeud dans le plan
	 * @return int la valeur de l'Attribut X
	 *
	 */
	public int getX() {
		return x;
	}
	
	/**
	* Accesseur de l'Attribut Y repr�santant l'ordonn�e du Noeud dans le plan
	 * @return int la valeur de l'Attribut Y
	 *
	 */
	public int getY() {
		return y;
	}
	
	public boolean getVisite(){
		return visite;
	}
	
	public void setVisite(boolean visite){
		this.visite=visite;
	}
}
