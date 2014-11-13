package vue;

import javax.swing.JPanel;

import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

public class VueEtape extends JPanel{
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Tournee tournee;
	
	public VueEtape(Controleur controleur){
		ctrl = controleur;
	}

}
