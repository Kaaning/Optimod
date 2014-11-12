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

import modele.Noeud;

public class VueNoeud extends JComponent implements MouseListener{

	private Color couleur;
	public Noeud noeud;
	private int rayon = 12;
	private double echelle;
	private int rayonAjuste;
	private int centrage;
	private Dimension size;
	private Color fond = new Color(53,53,53);
	private Color surbrillance = new Color(93,93,93);
	
	public VueNoeud(Noeud noeud, double echelle){
		super();
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
	
	public int getXVue(){
		//Convertit le x plan en x vue
		int xPlan = noeud.getX();
		int xVue = (int)((xPlan-centrage)*echelle);
//		System.out.print(xPlan+" -> "+xVue+" a "+rayonAjuste);
		return xVue;
	}
	
	public int getYVue(){
		int yPlan = noeud.getY();
		int yVue = (int)((yPlan-centrage)*echelle);
//		System.out.println("     "+yPlan+" -> "+yVue);
		return yVue;
	}
	
	public int getXPlan(){
		return noeud.getX();
	}
	
	public int getYPlan(){
		return noeud.getY();
	}
	
	public Color getCouleur(){
		return couleur;
	}
	
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
	
	public void changerEchelle(double echelle){
		this.echelle=echelle;
        rayonAjuste = (int)(rayon*echelle);
        size = new Dimension(rayonAjuste,rayonAjuste);
        setSize(size.width, size.height);
	}
	
	public void changerCouleur(int entrepot){
		if(noeud.getId()==entrepot){
			fond = new Color(72,72,255);
			surbrillance = new Color(119,119,255);
		}
		else if(noeud.getEtat()==0){
			fond = new Color(202,202,0);
			surbrillance = new Color(255,255,23);
		}
		else{
			fond = new Color(53,53,53);
			surbrillance = new Color(93,93,93);
		}
		couleur = fond;
	}
}
