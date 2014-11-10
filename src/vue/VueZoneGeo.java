package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import modele.Noeud;
import modele.Tron�on;
import modele.ZoneGeographique;

public class VueZoneGeo extends JPanel{
	
	private ZoneGeographique zoneGeo;
	private ArrayList<VueNoeud> vueNoeuds;
	private ArrayList<VueTron�on> vueTron�ons;
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
        vueTron�ons = new ArrayList<VueTron�on>();
        
        setSize(largeur,hauteur);
		setLocation(x,y);
		setLayout(null);
		creerVueTron�ons();
		creerVueNoeuds();
		/*----- Test -----*/
//		add(vt);
//		vt.setLocation(vt.tron�on.source.getX(),vt.tron�on.source.getY());
		
     }
	
	public void creerVueTron�ons(){
		ArrayList<Tron�on> tron�ons = zoneGeo.getTron�ons();
		for(Tron�on tron�on : tron�ons){
			VueTron�on vn = new VueTron�on(tron�on);
			vueTron�ons.add(vn);
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
        // Dessin des tron�ons
        for (VueTron�on vt : vueTron�ons){
        	vt.dessiner(g);
        }
        // Dessin des noeuds
        super.paintChildren(g);
    }
	

	

}
