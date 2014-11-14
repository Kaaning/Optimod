package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.Livraison;
import modele.Noeud;
import modele.Tournee;
import modele.ZoneGeographique;
import controleur.Controleur;

/**
 * @author H4303 - 2014
 */
public class VueEtape extends JPanel{
	private Controleur ctrl;

	
	private JButton add = new JButton("Ajouter une livraison");
	private JButton supp = new JButton("Supprimer une livraison");
	private JButton cancel = new JButton("Annuler");
	private JButton valider = new JButton("Valider");
	
	private Box info = Box.createVerticalBox();
	private Box Field = Box.createVerticalBox();
	private JLabel infoNoeud = new JLabel("Selectionner un Noeud");
	private JLabel infoLiv = new JLabel();
	private JPanel boutons = new JPanel();
	
	private JTextField id = new JTextField();
	private JTextField client = new JTextField();
	private JTextField idNoeud = new JTextField();
	
	
	private Livraison l;

	
	private JButton add = new JButton("Ajouter une livraison");
	private JButton supp = new JButton("Supprimer une livraison");
	
	private Box info = Box.createVerticalBox();
	private JLabel infoNoeud = new JLabel("Selectionner un Noeud");
	private JLabel infoLiv = new JLabel();
	private JPanel boutons = new JPanel();
	
	
	/**Constructeur de la VueEtape
	 * @param controleur : lien vers le controleur
	 */
	public VueEtape(Controleur controleur){
		ctrl = controleur;
		setSize(300, 500);
		
		GridLayout gl = new GridLayout();
		gl.setColumns(1);
		gl.setRows(2);
		gl.setHgap(20); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)

		
		Field.setVisible(false);
		Box bidNoeud = Box.createHorizontalBox();
		bidNoeud.add(new JLabel("   Adresse du noeud précédent : "));
		bidNoeud.add(idNoeud);
		Field.add(bidNoeud);
		Box bid = Box.createHorizontalBox();
		bid.add(new JLabel("  Id de la livraison : "));
		bid.add(id);
		Field.add(bid);
		Box bclient = Box.createHorizontalBox();
		bclient.add(new JLabel("       Id du client : "));
		bclient.add(client);
		Field.add(bclient);
		Box bbouton = Box.createHorizontalBox();
		bbouton.add(cancel);
		bbouton.add(valider);
		Field.add(bbouton);

		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Field.setVisible(true);
				Field.repaint();
				supp.setEnabled(false);
				add.setEnabled(false);
			}
		});
		
		supp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(l!=null){
					ctrl.SupprimerLivraison(l);
				}
				else {infoLiv.setText("Aucune livraison selectionnée !");}	
			}
		});
		
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Field.setVisible(false);
				supp.setEnabled(true);
				add.setEnabled(true);
				
				String IdClient = client.getText();
				String IdLiv = id.getText();
				
				try{
					if(l!=null){
						controleur.afficherMessageErreur("Déjà une livraison à cette adresse !");
					}
					else if(controleur.AjouterLivraison(Integer.parseInt(IdClient), Integer.parseInt(IdLiv))==-1){
						controleur.afficherMessageErreur("Selectionner un noeud où livrer !");
					}
				}
				catch(Exception e){
					controleur.afficherMessageErreur("Erreur de format - Les ids doivent être des entiers !");
				}
				
				id.setText("");
				idNoeud.setText("");
				client.setText("");
				
				Field.repaint();
				//ctrl.AjouterLivraison();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Field.setVisible(false);
				supp.setEnabled(true);
				add.setEnabled(true);
				Field.repaint();
				id.setText("");
				idNoeud.setText("");
				client.setText("");
				//ctrl.AjouterLivraison();
			}
		});
		
		
		GridLayout gl = new GridLayout();
		gl.setColumns(1);
		gl.setRows(3);
		gl.setHgap(30); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)

		gl.setVgap(20); //Cinq pixels d'espace entre les lignes (V comme Vertical)
        this.setLayout(gl);
        
        infoNoeud.setFont(new Font("Serif", Font.PLAIN, 20));
        infoLiv.setFont(new Font("Serif", Font.PLAIN, 18));

        
        Box b3 = Box.createHorizontalBox();
        
        b3.add(Box.createRigidArea(new Dimension(0,30)));
        b3.add(Box.createHorizontalGlue());
        b3.add(infoNoeud);
        b3.add(Box.createHorizontalGlue());
        
		
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		boutons.setLayout(new BoxLayout(boutons,BoxLayout.PAGE_AXIS));
		boutons.add(Box.createVerticalGlue());
		b1.add(Box.createHorizontalGlue());
		b1.add(add);
		b1.add(Box.createHorizontalGlue());
		boutons.add(b1);
		boutons.add(Box.createRigidArea(new Dimension(0,10)));
		b2.add(Box.createHorizontalGlue());
		b2.add(supp);
		b2.add(Box.createHorizontalGlue());
		boutons.add(b2);
		boutons.add(Box.createVerticalGlue());

		
		
		info.add(b3);
		info.add(infoLiv);
		
		this.add(info);


		this.add(Field);

		this.add(boutons);
        
	}
	
	/**Affiche les infos de la livraison
	 * Methode appelee apres un clic sur un noeud ayant une livraison
	 * @param l : livraison
	 */
	public void DisplayLivraison(Livraison l){
		infoLiv.setText("Livraison n°"+ l.getId() + " - idClient " + l.getClient() );
	}
	
	/**Affiche les infos du noeud
	 * Methode appelee apres un clic sur un noeud
	 * @param n : noeud
	 */
	public void DisplayNoeud(Noeud n){
		infoLiv.setText("");
		infoNoeud.setText("Noeud n°"+n.getId());
		l=null;	
    }

	public void DisplayLivraison(Livraison l){
		infoLiv.setText("Livraison n°"+ l.getId() + " - idClient " + l.getClient() );
		this.l=l;
	}
	
	

	

}
