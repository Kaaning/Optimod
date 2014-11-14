package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.org.mozilla.javascript.internal.regexp.SubString;
import modele.Noeud;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;
import bibliothequesTiers.ExampleFileFilter;



/**
 * @author H4303 - 2014
 */
public class Accueil{
	private Controleur ctrl;
	private VueZoneGeo vueZoneGeo;
	private JFrame cadre;
	private JButton chargerLivraison;
	private JButton chargerPlan;
	private JButton calculerItineraire;
	private JButton annuler;
	private JButton refaire;
	private JButton exporter;
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	private JPanel west = new JPanel();
	private JPanel east = new JPanel();
	private JPanel north = new JPanel();
	int largeur = 1200;
	int hauteur = 600; 
	
	private JLabel message = new JLabel();
	private JPanel pPlan = new JPanel();
	
	/**Constructeur de la fenetre principale
	 * @param controleur : lien vers le controleur
	 */
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
		
		// Initialisation des boutons
		chargerPlan = creerBouton("Charger un plan", 180);
		chargerLivraison = creerBouton("Charger des livraisons", 180);
		chargerLivraison.setEnabled(false);
		calculerItineraire = creerBouton("Calculer itinéraire", 180);
		calculerItineraire.setEnabled(false);
		annuler = creerBouton("Annuler", 100);
		annuler.setEnabled(false);
		refaire = creerBouton("Refaire", 100);
		refaire.setEnabled(false);
		exporter = creerBouton("Exporter", 100);
		exporter.setEnabled(false);
		
		//SOUTH ----------------------------
		south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(chargerPlan);
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(chargerLivraison);
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(calculerItineraire);
		south.add(Box.createHorizontalGlue());
		south.add(annuler);
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(refaire);
		south.add(Box.createRigidArea(new Dimension(30,0)));
		south.add(exporter);
		south.add(Box.createRigidArea(new Dimension(30,0)));

		//NORTH ----------------------------
		north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
		north.add(Box.createHorizontalGlue());
		north.add(message);
		north.add(Box.createHorizontalGlue());

		//CENTER ----------------------------
		center.setLayout(null);
		center.add(vueZoneGeo);

		cadre.setVisible(true);
		
		//Ecouteurs -------------------------

		
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

		annuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		refaire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		exporter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String texte = ctrl.exporterTournee();
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Enregistrer la feuille de route");
				FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichier texte (.txt)", "txt", "TXT");
				fc.addChoosableFileFilter(ff);
				fc.setFileFilter(ff);
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					ExampleFileFilter filtre = new ExampleFileFilter("txt");
					File file = fc.getSelectedFile();
					if(filtre.accept(file)){}
					else if(file.getName().contains(".")){
						String newName = file.getName().replace(file.getName().substring(file.getName().indexOf(".")+1), "txt");
						file = new File(file.getParent()+"\\"+newName);
						JOptionPane.showMessageDialog(cadre, "L'extension a été remplacée par \".txt\"", "Attention", JOptionPane.WARNING_MESSAGE);
					}
					else{
						String newName = file.getName()+".txt";
						file = new File(file.getParent()+"\\"+newName);
					}
					try{
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
						pw.print(texte);
						pw.close();
					}catch(IOException e){
						JOptionPane.showMessageDialog(null,"Erreur lors de l'écriture : "+e.getMessage());
					}
					
				}
			}
		});
		
	
	}
	
	/**Cree un JButton correctement formate
	 * @param nom : texte affiche sur le bouton
	 * @return JButton
	 */
	public JButton creerBouton(String nom, int taille){
		JButton bouton = new JButton(nom);
		bouton.setPreferredSize(new Dimension(taille,30));
		return bouton;
	}

	
	/**Cree une VueZoneGeographique
	 * @param largeur
	 * @param hauteur
	 * @param zoneGeographique : lien vers le modele
	 */
	public void creerVueZoneGeographique(int largeur, int hauteur, ZoneGeographique zoneGeographique) {
		center.remove(vueZoneGeo);
		this.vueZoneGeo = new VueZoneGeo(1100, 500, zoneGeographique, ctrl);
		center.add(vueZoneGeo);
		cadre.repaint();
		chargerLivraison.setEnabled(true);
		System.out.println(zoneGeographique.getNoeuds().size());
	}
	
	/**Cree une VueTournee
	 * @param tournee : lien vers le modele
	 */
	public void creerVueTournee (Tournee tournee) {
		this.vueZoneGeo.creerVueTournee();
	}
	
	/**Ouvre une pop-up contenant un message d'erreur
	 * @param erreur : message a afficher
	 */
	public void afficherMessageErreur(String erreur){
		JOptionPane.showMessageDialog(cadre, erreur, "", JOptionPane.ERROR_MESSAGE);
	}
	
	/**Modifie le texte du haut de la fenetre
	 * @param message : message a afficher
	 */
	public void afficherMessage(String message){
		this.message.setText(message);
		this.message.setFont(new Font("Serif", Font.PLAIN, 16));
	}

	/**Met a jour la VueZoneGeographique
	 * 
	 */
	public void MAJVueZoneGeographique() {
		vueZoneGeo.MAJVueZoneGeographique();
	}


	

	public void CreerVueTournee() {
		vueZoneGeo.creerVueTournee();
		cadre.repaint();
	}
	
	public void MAJVueZoneGeographique() {
		vueZoneGeo.MAJVueTournee();
		cadre.repaint();
	}
	
	
	public void MAJVueEtape(Noeud n){
		vueZoneGeo.MAJVueEtape(n);
	}
	

	
	/**Met a jour la VueEtape
	 * @param n : noeud clique
	 */
	public void MAJVueEtape(Noeud n){
		vueZoneGeo.MAJVueEtape(n);
	}



	
	

}
