package vue;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Fenetre {

	private VueZoneGeo plan;
    private JFrame cadre;
 
    public static void main(String[] args) {
        new Fenetre(600,400);
    }
 
    public Fenetre(int largeur, int hauteur) {
        // Creation d'un cadre contenant un menu, deux boutons,
        // un ecouteur de souris et une zone de dessin
        cadre = new JFrame("Optimod");
        cadre.setLayout(null);
        cadre.setSize(largeur,hauteur);
//        creeMenus();
//        creeBoutons(largeur,hauteur);
        plan =  new VueZoneGeo(0,0,largeur-100,hauteur-100, Color.gray);
//        plan.addMouseListener(new MouseAdapter(){
//            public void mouseClicked(MouseEvent e){
//                System.out.println("Souris cliquee en x="+e.getX()+" y="+e.getY());
//                plan.trouverNoeudClique(e.getX(), e.getY());
//            }
//        });
        cadre.getContentPane().add(plan);
        plan.repaint();
        cadre.setVisible(true);
    }
}
