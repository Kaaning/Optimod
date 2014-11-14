package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Livraison;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

public class VueLivraison extends JPanel{
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Tournee tournee;
	
	private JLabel livraisonDisplay = new JLabel();
	
	public VueLivraison(Controleur controleur, Livraison l){
		ctrl = controleur;
		livraisonDisplay.setText("Livraison n°"+ l.getId() +" : Noeud "+l.getNoeud().getId() + " - idClient " + l.getClient() );
		livraisonDisplay.setFont(new Font("Serif", Font.PLAIN, 12));
		
		this.add(livraisonDisplay);
	}



}
