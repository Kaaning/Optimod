package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import modele.Tron�on;

public class VueTron�on {

	private Color couleur = new Color(53,53,53);
	public Tron�on tron�on;
	double echelle;

	
	public VueTron�on(Tron�on tron�on, double echelle){
		super();
		this.tron�on = tron�on;
		this.echelle=echelle;
	}
	
	public int getXVue(String noeud){
		int xPlan=0;
		int xVue=0;
		if(noeud.equals("source")){
			xPlan = tron�on.getSource().getX();
			xVue = (int)(xPlan*echelle);
//			System.out.println(xPlan+" -> "+xVue+" a "+rayonAjuste);
		}
		else if(noeud.equals("cible")){
			xPlan = tron�on.getCible().getX();
			xVue = (int)(xPlan*echelle);
		}
		return xVue;
	}
	
	public int getYVue(String noeud){
		int yPlan=0;
		int yVue=0;
		if(noeud.equals("source")){
			yPlan = tron�on.getSource().getY();
			yVue = (int)(yPlan*echelle);
		}
		else if(noeud.equals("cible")){
			yPlan = tron�on.getCible().getY();
			yVue = (int)(yPlan*echelle);
		}
		return yVue;
	}
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
	
	public void dessiner(Graphics g, int[] origine, int[] deplacement){
		// turn on anti-alias mode
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		g.setColor(couleur);
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.drawLine(origine[0]+deplacement[0]+getXVue("source"), origine[1]+deplacement[1]+getYVue("source"), origine[0]+deplacement[0]+getXVue("cible"), origine[1]+deplacement[1]+getYVue("cible"));
//		System.out.println("Tron�on situ� en "+getXVue("source")+","+getYVue("source"));
	}
	
	public void changerEchelle(double echelle){
		this.echelle=echelle;
	}
}
