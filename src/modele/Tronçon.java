/**
 * 
 */
package modele;

/**
 * Classe de Gestion des Tron�ons
 * @author MohamedRiadh
 *
 */

public class Tron�on {
	private String nomRue;
	private double vitesse;
	private double longueur;
	private Noeud cible = new Noeud();
	private Noeud source = new Noeud();
	
	/**
	 * Constructeur avec param�tres de l'Objet Tron�on
	 * @param uneRue le nom de la rue que mod�lise l'Objet Tron�on
	 * @param vitesse indique la vitesse moyenne enregistr� sur le Tron�on
	 * @param longueur indique la longueur du Tron�on
	 */
	public Tron�on(String uneRue, double uneVitesse, double uneLongueur) {
		nomRue = uneRue;
		vitesse = uneVitesse;
		longueur = uneLongueur;
	}
	
	/**
	 * Accesseur de l'Attribut nomRue de l'Objet Tron�on
	 * @return String la valeur de l'Attribut nomRue de l'Objet Tron�on
	 *
	 */
	public String getNomRue() {
		return this.nomRue;
	}
	
	/**
	 * Accesseur de l'Attribut vitesse de l'Objet Tron�on
	 * @return double la valeur de l'Attribut vitesse de l'Objet Tron�on
	 *
	 */
	public double getVitesse() {
		return this.vitesse;
	}
	
	/**
	 * Accesseur de l'Attribut longueur de l'Objet Tron�on
	 * @return double la valeur de l'Attribut longueur de l'Objet Tron�on
	 *
	 */
	public double getLongueur() {
		return this.longueur;
	}
	
	/**
	 * Accesseur de l'Attribut cible qui repr�sente le Noeud cible (destination) du Tron�on 
	 * @return Noeud l'Attribut cible de l'Objet Tron�on 
	 *
	 */
	public Noeud getCible() {
		return this.cible;
	}
	
	/**
	 * Accesseur de l'Attribut source qui repr�sente le Noeud source (d�part) du Tron�on 
	 * @return Noeud l'Attribut source de l'Objet Tron�on
	 *
	 */
	public Noeud getSource() {
		return this.source;
	}
	
	/**
	 * Mutateur de l'Attribut cible qui repr�sente le Noeud cible (destination) du Tron�on
	 * @param uneCible Objet Noeud � mettre en tant qu'Attribut cible de l'Objet Tron�on 
	 *
	 */
	public void setCible(Noeud uneCible) {
		cible = uneCible;
	}
	
	/**
	 * Mutateur de l'Attribut source qui repr�sente le Noeud source (d�part) du Tron�on
	 * @param uneSource Objet Noeud � mettre en tant qu'Attribut source de l'Objet Tron�on
	 *
	 */
	public void setSource(Noeud uneSource) {
		source = uneSource;
	}
}
