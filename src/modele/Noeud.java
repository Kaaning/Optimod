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
	private List<Tron�on> tron�onsSortans = new ArrayList<Tron�on>();
	private List<Tron�on> tron�onsEntrants = new ArrayList<Tron�on>();
	private boolean visite=false;
	
	/**
	 * Constructeur sans param�tres de l'Objet Noeud
	 */
	public Noeud() {
	}
	
	/**
	 * Constructeur avec param�tres de l'Objet Noeud
	 * @param unId l'ID du Noeud � construire et ses coordonn�es X et Y dans le plan
	 * @param unX l'absisse X du Noeud dans le plan
	 * @param unY l'oordonn�e Y du Noeud dans le plan
	 */
	public Noeud(int unId, int unX, int unY) {
		id = unId;
		x = unX;
		y = unY;
	}
	
	/**
	 * Ajoute un objet tron�on � la liste des tron�ons sortants du Noeud 
	 * @param tron�on L'Objet Tron�on � rajouter 
	 *
	 */
	public void ajouterTron�onSortant(Tron�on tron�on) {
		this.tron�onsSortans.add(tron�on);
	}
	
	/**
	 * Ajoute un objet tron�on � la liste des tron�ons entrants du Noeud
	 * @param tron�on L'Objet Tron�on � rajouter
	 *
	 */
	public void ajouterTron�onEntrants(Tron�on tron�on) {
		this.tron�onsEntrants.add(tron�on);
	}
	
	/**
	 * Accesseur de l'Attribut tron�onsEntrants
	 * @return List<Tron�on> Liste des Objets Tron�ons entrants de Noeud
	 *
	 */
	public List<Tron�on> getTron�onsEntrants() {
		return this.tron�onsEntrants;
	}
	
	/**
	 * Accesseur de l'Attribut tron�onsSortants
	 * @return List<Tron�on> Liste des Objets Tron�ons sortants de Noeud
	 *
	 */
	public List<Tron�on> getTron�onsSortants() {
		return this.tron�onsSortans;
	}
	
	
	/**
	 * Accesseur de l'Attribut Id repr�santant l'Identifiant du Noeud
	 * @return int la valeur de l'Attribut Id
	 *
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Accesseur de l'Attribut X repr�santant l'absisse du Noeud dans le plan
	 * @return int la valeur de l'Attribut X
	 *
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Accesseur de l'Attribut Y repr�sentant l'ordonn�e du Noeud dans le plan
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
