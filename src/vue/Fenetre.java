package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import modele.ZoneGeographique;

public class Fenetre extends JFrame{

	private VueZoneGeo plan;
	private double echelle = 500.0/800.0;
 
    public static void main(String[] args) {
        new Fenetre(900,700);
    }
 
    public Fenetre(int largeur, int hauteur) {
        // Creation d'un cadre contenant un menu, deux boutons,
        // un ecouteur de souris et une zone de dessin
        super("Optimod");
        setSize(largeur,hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Destruction à la fermeture
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 -getWidth()/2, dim.height/2-getHeight()/2-10); //Centrage de la fenêtre
        setLayout(null);
        
//        creeMenus();
//        creeBoutons(largeur,hauteur);
        plan =  new VueZoneGeo(0,0,500,500, echelle, new ZoneGeographique());
        
        getContentPane().add(plan);
//        plan.repaint();
        setVisible(true);
    }
}
