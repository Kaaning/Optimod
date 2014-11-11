package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import modele.*;

public class acceuil{
	private JFrame cadre;
	private JButton chargerLivraison = new JButton("Charger une demande de livraison");
	private JButton calculerItineraire = new JButton("Calculer l'itinéraire");
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	private JPanel west = new JPanel();
	private JPanel east = new JPanel();
	private JPanel north = new JPanel();
	int largeur = 1200;
	int hauteur = 600; 
	
	private JTextArea plan;
	private JTextArea livraison;
	
	private JList list;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	
	public acceuil(Jour j){
		cadre = new JFrame();
		cadre.setLayout(null);
		cadre.setSize(largeur, hauteur);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.setLocationRelativeTo(null);
		
		cadre.setLayout(new BorderLayout());
		cadre.getContentPane().add(south, BorderLayout.SOUTH);
		cadre.getContentPane().add(north, BorderLayout.NORTH);
		cadre.getContentPane().add(west, BorderLayout.WEST);
		cadre.getContentPane().add(east, BorderLayout.EAST);
		cadre.getContentPane().add(center, BorderLayout.CENTER);
		
		chargerLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   String chemin="";
		       JFileChooser fc = new JFileChooser();
		                int retval = fc.showOpenDialog(null);
		                if (retval == JFileChooser.APPROVE_OPTION) {
		                    chemin = fc.getSelectedFile().getAbsolutePath();
		                    chemin = chemin.replace("\\", "/");
		                    
		                }
		        
		                plan.setText(chemin);
		                try {
							j.chargerLivraison(chemin);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    
		                Vector<String> livraisons = new Vector<String>();
	                    DemandesDeLivraison ddl = j.getDemandes();
	            		for(int i = 0 ; i<ddl.getPlages().size();i++){
	            			PlageHoraire ph = ddl.getPlages().get(i); 
	            			for(int j = 0 ; j<ph.getLivraisons().size() ; j++){
	            				Livraison l = ph.getLivraisons().get(j);
	            				(listModel).addElement("Livraison n°"+l.getId()+" chez "+l.getClient() + " à l'adresse "+l.getNoeud());
	            			}
	            		}
			  }
		});
		
		//SOUTH ----------------------------
		GridLayout gl = new GridLayout();
		gl.setColumns(2);
		gl.setRows(1);
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
		south.setLayout(gl);
		
		
		south.add(calculerItineraire);
		south.add(chargerLivraison);
		
		//NORTH ----------------------------
		/*north.setLayout(gl);
		JTextArea Bonjour = new JTextArea("");
		plan.setFont(new Font("Serif", Font.PLAIN, 16));
		plan.setLineWrap(true);
		plan.setWrapStyleWord(true);
		JTextArea livraison = new JTextArea("Points de livraison");
		livraison.setFont(new Font("Serif", Font.PLAIN, 16));
		livraison.setLineWrap(true);
		livraison.setWrapStyleWord(true);
		north.add(plan);
		north.add(livraison);*/
		
		//CENTER ----------------------------
		GridLayout g2 = new GridLayout();
		g2.setColumns(2);
		g2.setRows(2);
		g2.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		g2.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
		center.setLayout(gl);
		
		plan = new JTextArea("Plan de zone");
		plan.setFont(new Font("Serif", Font.PLAIN, 16));
		plan.setLineWrap(true);
		plan.setWrapStyleWord(true);
		livraison = new JTextArea("Points de livraison");
		livraison.setFont(new Font("Serif", Font.PLAIN, 16));
		livraison.setLineWrap(true);
		livraison.setWrapStyleWord(true);
		
		
		
		JPanel pPlan = new JPanel();
		pPlan.add(plan);
		
		
		
		JPanel pList = new JPanel();
		
		list = new JList(listModel); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(250, 500));

		
		pList.add(listScroller);
		
		/*center.add(plan);
		center.add(livraison);*/
		center.add(pPlan);
		center.add(pList);
				

		cadre.setVisible(true);
		
	}
	
	

}
