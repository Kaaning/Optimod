package vue;

import java.awt.Color;

import java.awt.GridLayout;


import javax.swing.JPanel;



import modele.Noeud;
import modele.ZoneGeographique;
import controleur.Controleur;

/**
 * @author H4303 - 2014
 */
public class VueZoneGeo extends JPanel{
	
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Color couleurArrierePlan = new Color(193,193,193);
	private int largeur;
	private int hauteur;
	private VuePlan plan;
	private VueTournee vueTournee;
	
	/**Constructeur de VueZoneGeo
	 * @param largeur
	 * @param hauteur
	 */
	public VueZoneGeo(int largeur, int hauteur){
		this.largeur = largeur;
        this.hauteur = hauteur;
        setSize(largeur,hauteur);
	}
	
	/**Constructeur de VueZoneGeo
	 * @param largeur
	 * @param hauteur
	 * @param zoneGeo : lien vers le modele
	 * @param ctrl : lien vers le controleur
	 */
	public VueZoneGeo (int largeur, int hauteur, ZoneGeographique zoneGeo, Controleur ctrl) {
    	// Creation d'un panneau pour dessiner les boules
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.zoneGeo = zoneGeo;
        this.ctrl=ctrl;
        this.setLayout(null);
        setSize(largeur,hauteur);
        plan = new VuePlan(zoneGeo, ctrl);
        add(plan);

	}
	/**Cree la VueTournee associee
	 * 
	 */
	public void creerVueTournee() {
		this.vueTournee = new VueTournee(this.zoneGeo.getTournee() , this.ctrl);
		changerCouleur(zoneGeo.getTournee().getEntrepot());

		this.add(vueTournee);
		vueTournee.setLocation(500,0);
		this.repaint();
		this.revalidate();
		
	}
	
	/**Met a jour la VueZoneGeographique
	 * @return : int
	 */
	public int MAJVueZoneGeographique () {
		creerVueTournee();
		changerCouleur(zoneGeo.getTournee().getEntrepot());
		return 0;
     }
	
	/**Met a jour la VueEtape
	 * @param n : id du noeud clique
	 */

	public int MAJVueTournee () {
		vueTournee.MAJvueTournee();
		vueTournee.setLocation(500,0);
		changerCouleur(zoneGeo.getTournee().getEntrepot());
		this.repaint();
		this.revalidate();
		return 0;
     }
	

	public void MAJVueEtape(Noeud n){
		this.vueTournee.MAJVueEtape(n);
	}
	
	/**Modifie les couleurs des noeuds et troncon
	 * @param n : id de l'entrepot
	 */
	public void changerCouleur(int n){
		plan.changerCouleur(n);
	}

}
