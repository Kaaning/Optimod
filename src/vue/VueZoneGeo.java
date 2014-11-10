package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import modele.Noeud;
import modele.Tronçon;
import modele.ZoneGeographique;

public class VueZoneGeo extends JPanel{
	
	private ZoneGeographique zoneGeo;
	private ArrayList<VueNoeud> vueNoeuds;
	private ArrayList<VueTronçon> vueTronçons;
	private Color couleurArrierePlan;
	private int largeur;
	private int hauteur;
	private int rayon;
	
	public VueZoneGeo (int x, int y, int largeur, int hauteur, Color arrierePlan, ZoneGeographique zoneGeo) {
    	// Creation d'un panneau pour dessiner les boules
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.rayon=8;
        this.couleurArrierePlan = arrierePlan;
        this.zoneGeo = zoneGeo;
        vueNoeuds = new ArrayList<VueNoeud>();
        vueTronçons = new ArrayList<VueTronçon>();
        
        setSize(largeur,hauteur);
		setLocation(x,y);
		setLayout(null);
		creerVueTronçons();
		creerVueNoeuds();
		/*----- Test -----*/
//		add(vt);
//		vt.setLocation(vt.tronçon.source.getX(),vt.tronçon.source.getY());
		
     }
	
	public void creerVueTronçons(){
		ArrayList<Tronçon> tronçons = zoneGeo.getTronçons();
		for(Tronçon tronçon : tronçons){
			VueTronçon vn = new VueTronçon(tronçon);
			vueTronçons.add(vn);
		}
	}
	
	public void creerVueNoeuds(){
		ArrayList<Noeud> noeuds = zoneGeo.getNoeuds();
		for(Noeud noeud : noeuds){
			VueNoeud vn = new VueNoeud(noeud);
			vueNoeuds.add(vn);
			add(vn);
			vn.setLocation(vn.getXVue()-rayon, vn.getYVue()-rayon);
		}
		repaint();
	}
	
	@Override
    public void paint(Graphics g) {
        // methode appelee a chaque fois que le dessin doit etre redessine
        super.paint(g);
        setBackground(couleurArrierePlan);
        // Dessin des tronçons
        for (VueTronçon vt : vueTronçons){
        	vt.dessiner(g);
        }
        // Dessin des noeuds
        super.paintChildren(g);
    }
	

	

}
