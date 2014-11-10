package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import modele.Tronçon;
import modele.Tronçon;

public class VueTronçon {

	private Color couleur = new Color(53,53,53);
	public Tronçon tronçon;
	int rayon=8;
	double echelle = 600.0/800.0;

	
	public VueTronçon(Tronçon tronçon){
		super();
		this.tronçon = tronçon;
	}
	
//	public int getXVue(){
//		//Convertit le x plan en x vue
//		int xPlan = tronçon.getX();
//		int xVue = xPlan;
//		return xVue;
//	}
//	
//	public int getYVue(){
//		int yPlan = tronçon.getY();
//		int yVue = yPlan;
//		return yVue;
//	}
//	
//	public int getXPlan(){
//		return tronçon.getX();
//	}
//	
//	public int getYPlan(){
//		return tronçon.getY();
//	}
	
	public Color getCouleur(){
		return couleur;
	}
	
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
	
	public void dessiner(Graphics g){
		// turn on anti-alias mode
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		g.setColor(couleur);
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.drawLine(tronçon.source.getX()-rayon, tronçon.source.getY()-rayon, tronçon.cible.getX()-rayon, tronçon.cible.getY()-rayon);
	}
	
}
