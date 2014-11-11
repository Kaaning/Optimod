/**
 * 
 */
package modele;

/**
 * Classe de Gestion des Troncons
 * @author MohamedRiadh
 *
 */

public class Troncon {
	private String nomRue;
	private double cout;
	private Noeud cible = new Noeud();
	private Noeud source = new Noeud();
	
	/**
	 * Constructeur avec paramètres de l'Objet Troncon
	 * @param uneRue le nom de la rue que modélise l'Objet Troncon
	 * @param cout indique le temps nécessaire pour parcourir le troncon
	 */
	public Troncon(String uneRue, double unCout) {
		nomRue = uneRue;
		cout = unCout;
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
	 * @return double la valeur de l'Attribut cout de l'Objet Troncon qui représente le temps nécessaire pour parcourir le troncon
	 *
	 */
	public double getCout() {
		return this.cout;
	}
	
	/**
	 * Accesseur de l'Attribut cible qui représente le Noeud cible (destination) du Troncon 
	 * @return Noeud l'Attribut cible de l'Objet Troncon 
	 *
	 */
	public Noeud getCible() {
		return this.cible;
	}
	
	/**
	 * Accesseur de l'Attribut source qui représente le Noeud source (départ) du Troncon 
	 * @return Noeud l'Attribut source de l'Objet Troncon
	 *
	 */
	public Noeud getSource() {
		return this.source;
	}
	
	/**
	 * Mutateur de l'Attribut cible qui représente le Noeud cible (destination) du Troncon
	 * @param uneCible Objet Noeud à mettre en tant qu'Attribut cible de l'Objet Troncon 
	 *
	 */
	public void setCible(Noeud uneCible) {
		cible = uneCible;
	}
	
	/**
	 * Mutateur de l'Attribut source qui représente le Noeud source (départ) du Troncon
	 * @param uneSource Objet Noeud à mettre en tant qu'Attribut source de l'Objet Troncon
	 *
	 */
	public void setSource(Noeud uneSource) {
		source = uneSource;
	}
}
