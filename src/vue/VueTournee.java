package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modele.Livraison;
import modele.Noeud;
import modele.PlageHoraire;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

/**
 * @author H4303 - 2014
 */
public class VueTournee extends JPanel{

	private Controleur ctrl;
	private Tournee tournee;
	private List<VuePlage> vuePlages = new ArrayList<VuePlage>();
	
	private JPanel pList;
	private VueEtape pEtape;
	private Box box;
	/**Constructeur de VueTournee
	 * @param tournee : lien vers le modele
	 * @param ctrl : lien vers le controleur
	 */
	public VueTournee (Tournee tournee, Controleur ctrl) {
    	// Creation d'un panneau pour dessiner les boules
		this.ctrl = ctrl;
		this.tournee = tournee;
		setSize(600, 500);
		
		if (tournee != null ) {
			GridLayout gl = new GridLayout();
			gl.setColumns(2);
			gl.setRows(1);
			gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
			gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
	        this.setLayout(gl);
	        
	        pList = new JPanel();
			pList.setSize(300, 500);


	        box = Box.createVerticalBox();

	        pList.add(box);
			
	        pEtape = new VueEtape(ctrl);
	                     
	
	        
			
			JScrollPane listScroller = new JScrollPane(pList);
			listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			listScroller.setPreferredSize(new Dimension(250, 250));
        
		
			if(tournee.getPlages()!=null){
		        for (PlageHoraire pl : tournee.getPlages()){
		        	VuePlage vuePl = new VuePlage(ctrl, pl);
		        	vuePlages.add(vuePl);
		        	box.add(vuePl);
		        } 
			}
			 this.add(pEtape);		
		     this.add(listScroller);
			
		}
        
	}


	/**Appelle la methode d'affichage d'une livraison
	 * @param l : livraison a afficher
	 */
	public void DisplayLivraison(Livraison l){
		pEtape.DisplayLivraison(l);
	}
	
	/**Met a jour la VueEtape
	 * @param n : Noeud clique
	 */
	public void MAJVueEtape(Noeud n) {
		pEtape.DisplayNoeud(n);
		for (Livraison l : tournee.getLivraison()){
			if (l.getNoeud()==n){
				DisplayLivraison(l);
	
				l=null;
				break;
			}
		}
		this.repaint();
		
	}


	public void MAJvueTournee() {

		box.removeAll();
		if(tournee.getPlages()!=null){
	        for (PlageHoraire pl : tournee.getPlages()){
	        	VuePlage vuePl = new VuePlage(ctrl, pl);
	        	vuePlages.add(vuePl);
	        	box.add(vuePl);
	        } 
		}
		this.repaint();
		this.revalidate();

	}
}
