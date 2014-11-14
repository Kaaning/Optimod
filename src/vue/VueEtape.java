package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Livraison;
import modele.Noeud;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

/**
 * @author H4303 - 2014
 */
public class VueEtape extends JPanel{
	private Controleur ctrl;
	
	private JButton add = new JButton("Ajouter une livraison");
	private JButton supp = new JButton("Supprimer une livraison");
	
	private Box info = Box.createVerticalBox();
	private JLabel infoNoeud = new JLabel("Selectionner un Noeud");
	private JLabel infoLiv = new JLabel();
	private JPanel boutons = new JPanel();
	
	
	/**Constructeur de la VueEtape
	 * @param controleur : lien vers le controleur
	 */
	public VueEtape(Controleur controleur){
		ctrl = controleur;
		setSize(300, 500);
		
		GridLayout gl = new GridLayout();
		gl.setColumns(1);
		gl.setRows(2);
		gl.setHgap(20); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(20); //Cinq pixels d'espace entre les lignes (V comme Vertical)
        this.setLayout(gl);
        
        infoNoeud.setFont(new Font("Serif", Font.PLAIN, 20));
        infoLiv.setFont(new Font("Serif", Font.PLAIN, 20));
        
        Box b3 = Box.createHorizontalBox();
        
        b3.add(Box.createRigidArea(new Dimension(0,30)));
        b3.add(Box.createHorizontalGlue());
        b3.add(infoNoeud);
        b3.add(Box.createHorizontalGlue());
        
		
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		boutons.setLayout(new BoxLayout(boutons,BoxLayout.PAGE_AXIS));
		boutons.add(Box.createVerticalGlue());
		b1.add(Box.createHorizontalGlue());
		b1.add(add);
		b1.add(Box.createHorizontalGlue());
		boutons.add(b1);
		boutons.add(Box.createRigidArea(new Dimension(0,10)));
		b2.add(Box.createHorizontalGlue());
		b2.add(supp);
		b2.add(Box.createHorizontalGlue());
		boutons.add(b2);
		
		
		info.add(b3);
		info.add(infoLiv);
		
		this.add(info);
		this.add(boutons);
        
	}
	
	/**Affiche les infos de la livraison
	 * Methode appelee apres un clic sur un noeud ayant une livraison
	 * @param l : livraison
	 */
	public void DisplayLivraison(Livraison l){
		infoLiv.setText("Livraison n°"+ l.getId() + " - idClient " + l.getClient() );
	}
	
	/**Affiche les infos du noeud
	 * Methode appelee apres un clic sur un noeud
	 * @param n : noeud
	 */
	public void DisplayNoeud(Noeud n){
		infoLiv.setText("");
		infoNoeud.setText("Noeud n°"+n.getId());
	}

}
