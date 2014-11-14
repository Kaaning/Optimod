/**
 * 
 */
package modele;

/**
 * @author H4303 - 2014
 */

public class Troncon {
	private String nomRue;
	private double cout;
	private double longueur;
	private int nbPassage=0;
	private Noeud cible = new Noeud();
	private Noeud source = new Noeud();
	
	/**
	 * Constructeur avec parametres de l'Objet Troncon
	 * @param uneRue String le nom de la rue que modelise l'Objet Troncon
	 * @param cout double le temps necessaire pour parcourir le troncon
	 * @param longueur double la longueur de l'Objet Troncon
	 */
	public Troncon(String uneRue, double unCout, double uneLongueur) {
		nomRue = uneRue;
		cout = unCout;
		longueur = uneLongueur;
	}
	
	/**
	 * Accesseur de l'Attribut nomRue de l'Objet Troncon
	 * @return String la valeur de l'Attribut nomRue de l'Objet Troncon
	 *
	 */
	public String getNomRue() {
		return this.nomRue;
	}
	
	/**
	 * Accesseur de l'Attribut cout de l'Objet Troncon
	 * @return double la valeur de l'Attribut cout de l'Objet Troncon qui represente le temps necessaire pour parcourir le troncon
	 *
	 */
	public double getCout() {
		return this.cout;
	}
	
	/**
	 * Accesseur de l'Attribut longueur de l'Objet Troncon
	 * @return double la valeur de l'Attribut longueur de l'Objet Troncon qui represente la longueur de la rue representee par le Troncon
	 *
	 */
	public double getLongueur() {
		return this.longueur;
	}
	
	/**
	 * Accesseur de l'Attribut cible qui represente le Noeud cible (destination) du Troncon 
	 * @return Noeud l'Attribut cible de l'Objet Troncon 
	 *
	 */
	public Noeud getCible() {
		return this.cible;
	}
	
	/**
	 * Accesseur de l'Attribut source qui represente le Noeud source (depart) du Troncon 
	 * @return Noeud l'Attribut source de l'Objet Troncon
	 *
	 */
	public Noeud getSource() {
		return this.source;
	}
	
	/**
	 * Mutateur de l'Attribut cible qui represente le Noeud cible (destination) du Troncon
	 * @param uneCible Objet Noeud à mettre en tant qu'Attribut cible de l'Objet Troncon 
	 *
	 */
	public void setCible(Noeud uneCible) {
		cible = uneCible;
	}
	
	/**
	 * Mutateur de l'Attribut source qui represente le Noeud source (depart) du Troncon
	 * @param uneSource Objet Noeud à mettre en tant qu'Attribut source de l'Objet Troncon
	 *
	 */
	public void setSource(Noeud uneSource) {
		source = uneSource;
	}
	
	/**Change le nombre de passage par ce troncon
	 * @param nb : nouveau nombre
	 */
	public void setNbPassage(int nb){
		nbPassage=nb;
	}
	
	/**Renvoie le nombre de passage actuel par le troncon
	 * @return int
	 */
	public int getNbPassage(){
		return nbPassage;
	}
}
