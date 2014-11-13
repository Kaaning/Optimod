package vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import javax.swing.Box;
import javax.swing.JLabel;

import javax.swing.JPanel;

import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

public class VueTournee extends JPanel{

	private Controleur ctrl;
	private Tournee tournee;
	private int largeur;
	private int hauteur;
//	private List<VueLivraison> vueLivraisons;
//	private VueEtape vueEtape;

	public VueTournee (Tournee tournee, Controleur ctrl) {
    	// Creation d'un panneau pour dessiner les boules
		this.ctrl = ctrl;
		this.tournee = tournee;
		setSize(600, 500);
		
		GridLayout gl = new GridLayout();
		gl.setColumns(2);
		gl.setRows(1);
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
        this.setLayout(gl);
        
		pList.setSize(300, 500);
        Box box = Box.createVerticalBox();
        pList.add(box);
		
		pEtape.setSize(300, 500);
        pEtape.setBackground(Color.gray);
        
               

        
		//Liste des points de livraison
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
