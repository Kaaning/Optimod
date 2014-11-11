/**
 * 
 */
package modele;

/**
 * Classe de Gestion des Tronçons
 * @author MohamedRiadh
 *
 */

public class Tronçon {
	private String nomRue;
	private double vitesse;
	private double longueur;
	private Noeud cible = new Noeud();
	private Noeud source = new Noeud();
	
	/**
	 * Constructeur avec paramètres de l'Objet Tronçon
	 * @param uneRue le nom de la rue que modélise l'Objet Tronçon
	 * @param vitesse indique la vitesse moyenne enregistré sur le Tronçon
	 * @param longueur indique la longueur du Tronçon
	 */
	public Tronçon(String uneRue, double uneVitesse, double uneLongueur) {
		nomRue = uneRue;
		vitesse = uneVitesse;
		longueur = uneLongueur;
	}
	
	/**
	 * Accesseur de l'Attribut nomRue de l'Objet Tronçon
	 * @return String la valeur de l'Attribut nomRue de l'Objet Tronçon
	 *
	 */
	public String getNomRue() {
		return this.nomRue;
	}
	
	/**
	 * Accesseur de l'Attribut vitesse de l'Objet Tronçon
	 * @return double la valeur de l'Attribut vitesse de l'Objet Tronçon
	 *
	 */
	public double getVitesse() {
		return this.vitesse;
	}
	
	/**
	 * Accesseur de l'Attribut longueur de l'Objet Tronçon
	 * @return double la valeur de l'Attribut longueur de l'Objet Tronçon
	 *
	 */
	public double getLongueur() {
		return this.longueur;
	}
	
	/**
	 * Accesseur de l'Attribut cible qui représente le Noeud cible (destination) du Tronçon 
	 * @return Noeud l'Attribut cible de l'Objet Tronçon 
	 *
	 */
	public Noeud getCible() {
		return this.cible;
	}
	
	/**
	 * Accesseur de l'Attribut source qui représente le Noeud source (départ) du Tronçon 
	 * @return Noeud l'Attribut source de l'Objet Tronçon
	 *
	 */
	public Noeud getSource() {
		return this.source;
	}
	
	/**
	 * Mutateur de l'Attribut cible qui représente le Noeud cible (destination) du Tronçon
	 * @param uneCible Objet Noeud à mettre en tant qu'Attribut cible de l'Objet Tronçon 
	 *
	 */
	public void setCible(Noeud uneCible) {
		cible = uneCible;
	}
	
	/**
	 * Mutateur de l'Attribut source qui représente le Noeud source (départ) du Tronçon
	 * @param uneSource Objet Noeud à mettre en tant qu'Attribut source de l'Objet Tronçon
	 *
	 */
	public void setSource(Noeud uneSource) {
		source = uneSource;
	}
}
