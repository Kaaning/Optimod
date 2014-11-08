package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import modele.Noeud;

public class VueZoneGeo extends JPanel{
	
	private Vector<VueNoeud> noeuds;
	private Color couleurArrierePlan;
	private int largeur;
	private int hauteur;
	private int rayon;
	
	public VueZoneGeo (int x, int y, int largeur, int hauteur, Color arrierePlan) {
    	// Creation d'un panneau pour dessiner les boules
 		setSize(largeur,hauteur);
		setLocation(x,y);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.rayon=30;
        this.couleurArrierePlan = arrierePlan;
        noeuds = new Vector<>();
        noeuds.addElement(new VueNoeud(new Noeud(1,30,30)));
        noeuds.addElement(new VueNoeud(new Noeud(1,200,200)));
        VueNoeud vn = new VueNoeud(new Noeud(1,30,30));
        this.add(vn);
     }
	
	@Override
    public void paintComponent(Graphics g) {
        // methode appelee a chaque fois que le dessin doit etre redessine
        super.paintComponent(g);
        setBackground(couleurArrierePlan);
        Iterator<VueNoeud> it = noeuds.iterator();
        while (it.hasNext()){
            VueNoeud vn = it.next();
//            g.setColor(vn.getCouleur());
//            g.fillOval(vn.getXVue(), vn.getYVue(), rayon, rayon);
            vn.paintComponent(g);
            this.add(vn);
        }
    }
	
	public void trouverNoeudClique(int xVue, int yVue){
		Iterator<VueNoeud> it = noeuds.iterator();
        while (it.hasNext()){
            VueNoeud vn = it.next();
            if(xVue>=vn.getXVue() && xVue<=vn.getXVue()+30){
            	if(yVue>=vn.getYVue() && yVue<=vn.getYVue()+30){
//            		vn.setCouleur(Color.blue);
//            		System.out.println("A cliqué sur le noeud situé en "+vn.getXplan()+","+vn.getYplan()+" !");
//            		repaint();
            		break;
            	}
            }
            
        }
	}
	
	

}
