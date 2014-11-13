package vue;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Livraison;
import modele.PlageHoraire;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

public class VuePlage extends JPanel{
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Tournee tournee;
	
	private List<VueLivraison> vueL = new ArrayList<VueLivraison>();
	private JLabel plageDisplay = new JLabel();

	public VuePlage(Controleur controleur, PlageHoraire plage){
		ctrl = controleur;
		BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(bl);
		
		plageDisplay.setText(plage.getHeureDebut().getTime()+" - "+plage.getHeureFin().getTime());
		this.add(plageDisplay);
		
		
		for (Livraison l : plage.getLivraisons()){
			
		}
		
	}
	
}
