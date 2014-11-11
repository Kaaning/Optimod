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
<<<<<<< HEAD
	private List<Tronçon> tronçonsSortans = new ArrayList<Tronçon>();
	private List<Tronçon> tronçonsEntrants = new ArrayList<Tronçon>();
	private boolean visite=false;
=======
	private List<Tron�on> tron�onsSortans = new ArrayList<Tron�on>();
	private List<Tron�on> tron�onsEntrants = new ArrayList<Tron�on>();
<<<<<<< HEAD
	private boolean visite=false;
=======
>>>>>>> master
>>>>>>> William-TestAffichage
	
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
	 * Ajoute un objet tronçon à la liste des tronçons sortants du Noeud 
	 * @param tronçon L'Objet Tronçon à rajouter 
	 *
	 */
	public void ajouterTronçonSortant(Tronçon tronçon) {
		this.tronçonsSortans.add(tronçon);
	}
	
	/**
	 * Ajoute un objet tronçon à la liste des tronçons entrants du Noeud
	 * @param tronçon L'Objet Tronçon à rajouter
	 *
	 */
	public void ajouterTronçonEntrants(Tronçon tronçon) {
		this.tronçonsEntrants.add(tronçon);
	}
	
	/**
	 * Accesseur de l'Attribut tronçonsEntrants
	 * @return List<Tronçon> Liste des Objets Tronçons entrants de Noeud
	 *
	 */
	public List<Tronçon> getTronçonsEntrants() {
		return this.tronçonsEntrants;
	}
	
	/**
	 * Accesseur de l'Attribut tronçonsSortants
	 * @return List<Tronçon> Liste des Objets Tronçons sortants de Noeud
	 *
	 */
	public List<Tronçon> getTronçonsSortants() {
		return this.tronçonsSortans;
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
<<<<<<< HEAD
	 * Accesseur de l'Attribut Y représantant l'ordonnée du Noeud dans le plan
=======
<<<<<<< HEAD
	 * Accesseur de l'Attribut Y repr�sentant l'ordonn�e du Noeud dans le plan
=======
	 * Accesseur de l'Attribut Y repr�santant l'ordonn�e du Noeud dans le plan
>>>>>>> master
>>>>>>> William-TestAffichage
	 * @return int la valeur de l'Attribut Y
	 *
	 */
	public int getY() {
		return y;
<<<<<<< HEAD
	}
	
	public boolean getVisite(){
		return visite;
	}
	
	public void setVisite(boolean visite){
		this.visite=visite;
=======
>>>>>>> master
	}
	
	public boolean getVisite(){
		return visite;
	}
	
	public void setVisite(boolean visite){
		this.visite=visite;
	}
}
