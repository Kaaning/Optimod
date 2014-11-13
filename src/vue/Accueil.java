package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import modele.Noeud;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;
import bibliothequesTiers.ExampleFileFilter;



public class Accueil{
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
	int largeur = 1200;
	int hauteur = 600; 
	
	private JLabel message = new JLabel();
	private JPanel pPlan = new JPanel();
	
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
		pPlan.setLayout(null);
		
		// Impossible d'appuyer sur ces boutons au départ
		chargerLivraison.setEnabled(false);
		calculerItineraire.setEnabled(false);
		
		/*chargerLivraison.addActionListener(new ActionListener() {
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
						
						ctrl.chargerLivraison(chemin);
						MAJModele();
						
						Vector<String> livraisons = new Vector<String>();
						ddl = zoneGeo.getTournee();
						for(int i = 0 ; i<ddl.getPlages().size();i++){
							PlageHoraire ph = ddl.getPlages().get(i); 
							for(int j = 0 ; j<ph.getLivraisons().size() ; j++){
								Livraison l = ph.getLivraisons().get(j);
								(listModel).addElement("Livraison n°"+l.getId()+" chez "+l.getClient() + " à l'adresse "+l.getNoeud());
							}
						}
						//						geo.changerCouleur(ddl.getEntrepot());
						//						geo.repaint();
					} else {
						JOptionPane.showMessageDialog(cadre, "Format non pris en compte !", "Erreur !", JOptionPane.ERROR_MESSAGE);
					}
				}
				calculerItineraire.setEnabled(true);
			}
		});*/
		
		chargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chemin="";
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Charger un plan");
				FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers .xml", "xml", "XML");
				fc.addChoosableFileFilter(ff);
				fc.setFileFilter(ff);
				int retval = fc.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					ExampleFileFilter filtre = new ExampleFileFilter("xml");
					if (filtre.accept(fc.getSelectedFile())) {
						chemin = fc.getSelectedFile().getAbsolutePath();
						chemin = chemin.replace("\\", "/");
						ctrl.ChargerZoneGeo(chemin);
					} else {
						String messageErreur = "Format non pris en compte !";
						afficherMessageErreur(messageErreur);
					}
				}		
			}
		});
		
		chargerLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   String chemin="";
		       JFileChooser fc = new JFileChooser();
		                int retval = fc.showOpenDialog(null);
		                if (retval == JFileChooser.APPROVE_OPTION) {
		                    chemin = fc.getSelectedFile().getAbsolutePath();
		                    chemin = chemin.replace("\\", "/");
		                    
		                }
		                ctrl.chargerLivraison(chemin);
			  }
		});
		

		//SOUTH ----------------------------
		south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
		south.add(Box.createHorizontalGlue());
		south.add(chargerPlan);
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(chargerLivraison);
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(calculerItineraire);
		south.add(Box.createHorizontalGlue());
		
		//NORTH ----------------------------
		north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
		north.add(Box.createHorizontalGlue());
		north.add(message);
		north.add(Box.createHorizontalGlue());
		
		//CENTER ----------------------------
		center.setLayout(null);
		center.add(vueZoneGeo);
				

		cadre.setVisible(true);
		
	}

	
	public void creerVueZoneGeographique(int largeur, int hauteur, ZoneGeographique zoneGeographique) {
		center.remove(vueZoneGeo);
		this.vueZoneGeo = new VueZoneGeo(1100, 500, zoneGeographique, ctrl);
		center.add(vueZoneGeo);
		cadre.repaint();
		chargerLivraison.setEnabled(true);
		System.out.println(zoneGeographique.getNoeuds().size());
	}
	
	public void creerVueTournee (Tournee tournee) {
		this.vueZoneGeo.creerVueTournee();
	}
	
	public void afficherMessageErreur(String erreur){
		JOptionPane.showMessageDialog(cadre, erreur, "", JOptionPane.ERROR_MESSAGE);
	}
	
	public void afficherMessage(String message){
		this.message.setText(message);
		this.message.setFont(new Font("Serif", Font.PLAIN, 16));
	}

	public void MAJVueZoneGeographique() {
		vueZoneGeo.MAJVueZoneGeographique();
	}
	
	public void MAJVueEtape(Noeud n){
		vueZoneGeo.MAJVueEtape(n);
	}



	
	

}
