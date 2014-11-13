package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import modele.Livraison;
import modele.PlageHoraire;
import modele.Tournee;
import modele.ZoneGeographique;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import controleur.Controleur;
import bibliothequesTiers.ExampleFileFilter;



public class Accueil{
	private ZoneGeographique zoneGeo;
	private Controleur ctrl;
	private VueZoneGeo vueZoneGeo;

	private JFrame cadre;
	private JButton chargerLivraison = new JButton("Charger une demande de livraison");
	private JButton chargerPlan = new JButton("Charger un plan");
	private JButton calculerItineraire = new JButton("Calculer l'itinéraire");
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	private JPanel west = new JPanel();
	private JPanel east = new JPanel();
	private JPanel north = new JPanel();
	int largeur = 1100;
	int hauteur = 600; 
	
	private JTextArea message;
	

	private JTextArea tPlan;
	private JTextArea tLivraison;

	private JPanel pPlan = new JPanel();
	private JPanel pList = new JPanel();
	
	public void message(String mess){
		message.setText(mess);
	}
	
	public Accueil(Controleur controleur){
		ctrl = controleur;
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
		
		vueZoneGeo = new VueZoneGeo(1100,500);
		
		// Impossible d'appuyer sur ces boutons au départ
		chargerLivraison.setEnabled(false);
		calculerItineraire.setEnabled(false);
		
		chargerLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chemin="";
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Charger une livraison");
				fc.addChoosableFileFilter(new FileNameExtensionFilter("Fichier .xml", "xml", "XML"));
				int retval = fc.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					ExampleFileFilter filtre = new ExampleFileFilter("xml");
					if (filtre.accept(fc.getSelectedFile())) {
						chemin = fc.getSelectedFile().getAbsolutePath();
						chemin = chemin.replace("\\", "/");
						try {
							zoneGeo.chargerLivraison(chemin);
							vueZoneGeo.creerVueTournee(zoneGeo.getTournee());
							vueZoneGeo.changerCouleur(zoneGeo.getTournee().getEntrepot());
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
						
						
					} else {
						JOptionPane.showMessageDialog(cadre, "Format non pris en compte !", "Erreur !", JOptionPane.ERROR_MESSAGE);
					}
				}
				calculerItineraire.setEnabled(true);
			}
		});
		
		chargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chemin="";
				chemin="fic/plan20x20.xml";
				//JFileChooser fc = new JFileChooser();
				//fc.setDialogTitle("Charger un plan");
				//FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers .xml", "xml", "XML");
				//fc.addChoosableFileFilter(ff);
				//fc.setFileFilter(ff);
				//int retval = fc.showOpenDialog(null);
				//if (retval == JFileChooser.APPROVE_OPTION) {
					ExampleFileFilter filtre = new ExampleFileFilter("xml");
					/*if (filtre.accept(fc.getSelectedFile())) {
						chemin = fc.getSelectedFile().getAbsolutePath();
						chemin = chemin.replace("\\", "/");*/
						try {
							//XMLValidateur.validerXML(chemin, "res\\plan.xsd");
							zoneGeo = new ZoneGeographique(chemin);
							//if (!plan.getReussi()) {
							//JOptionPane.showMessageDialog(cadre, "Erreur dans le ficher XML !", "Erreur !", JOptionPane.ERROR_MESSAGE);	
							//} else {
							center.remove(vueZoneGeo);
							vueZoneGeo = new VueZoneGeo(1100, 500, zoneGeo, ctrl);
							center.add(vueZoneGeo);
							cadre.repaint();
							chargerLivraison.setEnabled(true);
							System.out.println(zoneGeo.getNoeuds().size());
							//}

						} catch (JDOMException | IOException | HeadlessException /*| ParserConfigurationException | SAXException*/ e) {
							//e.printStackTrace();
							System.out.println(e.getMessage());
						} catch (NumberFormatException e) {
							String messageErreur = "Erreur dans le ficher XML: " + e.getMessage();
							JOptionPane.showMessageDialog(cadre, messageErreur, "Erreur !", JOptionPane.ERROR_MESSAGE);	
						}
					/*} else {
						JOptionPane.showMessageDialog(cadre, "Format non pris en compte !", "Erreur !", JOptionPane.ERROR_MESSAGE);
					}*/
				//}
			}
		});
		

		//SOUTH ----------------------------
		GridLayout gl = new GridLayout();
		gl.setColumns(3);
		gl.setRows(1);
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
		south.setLayout(gl);
		
		
		south.add(calculerItineraire);
		south.add(chargerPlan);
		south.add(chargerLivraison);
		
		//NORTH ----------------------------
		north.setLayout(gl);
		message = new JTextArea("Bonjour");
		message.setFont(new Font("Serif", Font.PLAIN, 16));
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		
		north.add(message);
		
		//CENTER ----------------------------
//		GridLayout g2 = new GridLayout();
//		g2.setColumns(2);
//		g2.setRows(2);
//		g2.setHgap(10); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
//		g2.setVgap(10); //Cinq pixels d'espace entre les lignes (V comme Vertical)
//		center.setLayout(gl);

		//Liste des points de livraison
//		list = new JList(listModel); //data has type Object[]
//		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		list.setVisibleRowCount(-1);
//		JScrollPane listScroller = new JScrollPane(list);
//		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		listScroller.setPreferredSize(new Dimension(250, 500));
//		pList.add(listScroller);
//		center.add(pPlan);
//		center.add(pList);
		center.setLayout(null);
		center.add(vueZoneGeo);
				

		cadre.setVisible(true);
		
	}
	
	

}
