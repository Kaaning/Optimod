package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.Livraison;
import modele.PlageHoraire;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

/**
 * @author H4303 - 2014
 */
public class VuePlage extends JPanel{
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Tournee tournee;
	private Box box;
	
	private List<VueLivraison> vueLs = new ArrayList<VueLivraison>();
	private JLabel plageDisplay = new JLabel();

	/**Constructeur de VuePlage
	 * @param controleur : lien vers le controleur
	 * @param plage : lien vers le modele
	 */
	public VuePlage(Controleur controleur, PlageHoraire plage){
		ctrl = controleur;
		
		box = Box.createVerticalBox();
		this.add(box);
		
		plageDisplay.setText(DisplayH(plage.getHeureDebut())+" - "+DisplayH(plage.getHeureFin()));
		plageDisplay.setFont(new Font("Serif", Font.PLAIN, 20));
		box.add(plageDisplay);
		
		
		for (Livraison l : plage.getLivraisons()){
			VueLivraison vueL = new VueLivraison(ctrl, l);
        	vueLs.add(vueL);
        	box.add(vueL);
		}
		
	}
	
	/**Renvoie une description de la plageHoraire
	 * @param d : Date
	 * @return String
	 */
	private String DisplayH(Date d){
		String heure = String.valueOf(d.getHours());
		String minute = String.valueOf(d.getMinutes());
		if( d.getHours()==0)
		{
			heure="00";
		}
		if( d.getMinutes()==0)
		{
			minute="00";
		}
		return heure+"h"+minute;
	}
	
}
