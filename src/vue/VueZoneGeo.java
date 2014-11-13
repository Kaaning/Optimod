package vue;

import java.awt.Color;
import javax.swing.JPanel;


import modele.ZoneGeographique;
import controleur.Controleur;

public class VueZoneGeo extends JPanel{
	
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Color couleurArrierePlan = new Color(193,193,193);
	private int largeur;
	private int hauteur;
	private VuePlan plan;
	private VueTournee vueTournee;
	
	public VueZoneGeo(int largeur, int hauteur){
		this.largeur = largeur;
        this.hauteur = hauteur;
        setSize(largeur,hauteur);
	}
	
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

	public void creerVueTournee() {
		this.vueTournee = new VueTournee(this.zoneGeo.getTournee() , this.ctrl);
		this.add(vueTournee);
		vueTournee.setLocation(500,0);
		this.repaint();
		this.revalidate();
		
	}
	
	public int MAJVueZoneGeographique () {
		creerVueTournee();
		return 0;
     }
	
	
	public void changerCouleur(int n){
		plan.changerCouleur(n);
	}

}
