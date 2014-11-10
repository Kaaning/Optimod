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

import modele.Tron�on;
import modele.Tron�on;

public class VueTron�on {

	private Color couleur = new Color(53,53,53);
	public Tron�on tron�on;
	int rayon=8;
	double echelle = 600.0/800.0;

	
	public VueTron�on(Tron�on tron�on){
		super();
		this.tron�on = tron�on;
	}
	
//	public int getXVue(){
//		//Convertit le x plan en x vue
//		int xPlan = tron�on.getX();
//		int xVue = xPlan;
//		return xVue;
//	}
//	
//	public int getYVue(){
//		int yPlan = tron�on.getY();
//		int yVue = yPlan;
//		return yVue;
//	}
//	
//	public int getXPlan(){
//		return tron�on.getX();
//	}
//	
//	public int getYPlan(){
//		return tron�on.getY();
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
		g.drawLine(tron�on.source.getX()-rayon, tron�on.source.getY()-rayon, tron�on.cible.getX()-rayon, tron�on.cible.getY()-rayon);
	}
	
}
