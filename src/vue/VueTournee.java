package vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

public class VueTournee extends JPanel{

	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Tournee tournee;
	private int largeur;
	private int hauteur;
//	private List<VueLivraison> vueLivraisons;
//	private VueEtape vueEtape;

	public VueTournee (Tournee tournee, Controleur ctrl) {
    	// Creation d'un panneau pour dessiner les boules
		this.ctrl = ctrl;
		this.zoneGeo = zoneGeo;
		this.tournee = tournee;
        this.largeur = 600;
        this.hauteur = 500;
        setSize(largeur, hauteur);
        setBackground(Color.green);
        setLayout(null);
        setLocation(500,0);
        
//        vueLivraison = new ArrayList<VueLivraison>();
	}	
}
