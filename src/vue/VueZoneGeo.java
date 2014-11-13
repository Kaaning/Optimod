package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import modele.Noeud;
import modele.Tournee;
import modele.Troncon;
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
        setSize(largeur,hauteur);
        plan = new VuePlan(zoneGeo, ctrl);
        add(plan);
<<<<<<< HEAD
        creerVueTournee();
//        add(new VueTournee(zoneGeo, ctrl, zoneGeo.getTournee()));
	}

	public void creerVueTournee() {
		this.vueTournee = new VueTournee(this.zoneGeo.getTournee() , this.ctrl);
		
	}
	
	public int MAJVueZoneGeographique () {
		return 0;
=======
     }
	
	public void creerVueTournee(Tournee tournee){
		this.vueTournee = new VueTournee(tournee, ctrl);
		this.add(vueTournee);
		this.repaint();
>>>>>>> origin/Jérémy
	}

}
