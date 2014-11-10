package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import modele.ZoneGeographique;

public class Fenetre extends JFrame{

	private VueZoneGeo plan;
 
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
        plan =  new VueZoneGeo(0,0,500,500, Color.gray, new ZoneGeographique());
        plan.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.out.println("Souris cliquee en x="+e.getX()+" y="+e.getY());
            }
        });
        getContentPane().add(plan);
//        plan.repaint();
        setVisible(true);
    }
}
