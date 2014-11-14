package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import controleur.Controleur;
import modele.Noeud;

/**
 * @author H4303 - 2014
 *
 */
public class VueNoeud extends JComponent implements MouseListener{
	
	private Color couleur;
	public Noeud noeud;
	private int rayon = 12;
	private double echelle;
	private int rayonAjuste;
	private int centrage;
	private Dimension size;
	private Color fond = new Color(73,73,73);
	private Color surbrillance = new Color(93,93,93);
	private Controleur ctrl;
	
	/**Constructeur de VueNoeud
	 * @param noeud
	 * @param echelle
	 * @param ctrl
	 */
	public VueNoeud(Noeud noeud, double echelle, Controleur ctrl){
		super();
		this.ctrl = ctrl; 
		this.noeud = noeud;
		this.couleur = fond;
		this.echelle=echelle;
		rayonAjuste = (int)(rayon*echelle);
		centrage=rayon/2;
		size = new Dimension(rayonAjuste,rayonAjuste);
		enableInputMethods(true);   
		addMouseListener(this);
		setSize(size.width, size.height);
        setFocusable(true);
	}
	
	/**Renvoie la coordonnee x convertie pour l'affichage
	 * @return int
	 */
	public int getXVue(){
		//Convertit le x plan en x vue
		int xPlan = noeud.getX();
		int xVue = (int)((xPlan-centrage)*echelle);
		return xVue;
	}
	
	/**Renvoie la coordonnee y convertie pour l'affichage
	 * @return int
	 */
	public int getYVue(){
		int yPlan = noeud.getY();
		int yVue = (int)((yPlan-centrage)*echelle);
//		System.out.println("     "+yPlan+" -> "+yVue);
		return yVue;
	}
	
	/**Renvoie la coordonnee x originale
	 * @return int
	 */
	public int getXPlan(){
		return noeud.getX();
	}
	
	/**Renvoie la coordonnee y originale
	 * @return int
	 */
	public int getYPlan(){
		return noeud.getY();
	}
	
	/**Renvoie la couleur d'affichage du noeud
	 * @return Color
	 */
	public Color getCouleur(){
		return couleur;
	}
	
	/**Modifie la couleur d'affichage du noeud
	 * @param couleur
	 */
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// turn on anti-alias mode
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(getCouleur());
        g.fillOval(0, 0, rayonAjuste, rayonAjuste);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("A cliqué en "+getXPlan()+","+getYPlan());
		ctrl.infoLivraison(noeud);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		couleur = surbrillance;
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		couleur = fond;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Dimension getPreferredSize()
    {
        return new Dimension(getWidth(), getHeight());
    }
	@Override
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }
	@Override
    public Dimension getMaximumSize()
    {
        return getPreferredSize();
    }
	
	/**Modifie l'echelle en fonction du zoom actuel
	 * @param echelle
	 */
	public void changerEchelle(double echelle){
		this.echelle=echelle;
        rayonAjuste = (int)(rayon*echelle);
        size = new Dimension(rayonAjuste,rayonAjuste);
        setSize(size.width, size.height);
	}
	
	/**Modifie la couleur en fonction de l'etat du noeud
	 * @param entrepot : numero d'identification de l'entrepot
	 */
	public void changerCouleur(int entrepot){
		if(noeud.getId()==entrepot){
			fond = new Color(0,91,183);
			surbrillance = new Color(0,116,232);
		}
		else if(noeud.getEtat()==0){ // Noeud ayant une livraison, tournée pas encore chargée
			fond = new Color(202,202,0);
			surbrillance = new Color(255,255,23);
		}
		else if(noeud.getEtat()==1){ // Noeud intégré dans la tournée
			fond = new Color(0,138,0);
			surbrillance = new Color(0,173,0);
		}
		else if(noeud.getEtat()==2){ // Noeud non intégré dans la tournée
			fond = new Color(193,0,0);
			surbrillance = new Color(233,0,0);
		}
		else{
			fond = new Color(73,73,73);
			surbrillance = new Color(93,93,93);
		}
		couleur = fond;
	}
}
