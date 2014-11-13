package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import modele.PlageHoraire;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

public class VueTournee extends JPanel{

	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private Tournee tournee;
	private List<VuePlage> vuePlages = new ArrayList<VuePlage>();
	private VueEtape vueEtape;
	
	private JPanel pList = new JPanel();
	private JPanel pEtape = new JPanel();
	
	private JList jlist;
	private DefaultListModel listModel = new DefaultListModel();

	public VueTournee (Tournee tournee, Controleur ctrl) {
    	// Creation d'un panneau pour dessiner les boules
		this.ctrl = ctrl;
		this.zoneGeo = tournee.getPlan();
		this.tournee = tournee;
		setSize(600, 500);
		setLocation(500, 0);
		
		GridLayout gl = new GridLayout();
		gl.setColumns(2);
		gl.setRows(1);
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
        this.setLayout(gl);
        pList.setLocation(300, 0);
		pList.setSize(300, 500);
        pList.setBackground(Color.green);
		
		pEtape.setSize(300, 500);
        pEtape.setBackground(Color.MAGENTA);
        
        
        
        
		//Liste des points de livraison
		jlist = new JList(listModel); //data has type Object[]
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		jlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		jlist.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(jlist);
		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(250, 250));
		pList.add(listScroller);
        
		if(tournee.getPlages()!=null){
	        for (PlageHoraire pl : tournee.getPlages()){
	        	VuePlage vuePl = new VuePlage(ctrl, pl);
	        	vuePlages.add(vuePl);
	        	pList.add(vuePl);
	        } 
		}
		
		this.add(pList);
        this.add(pEtape);
	}
}
