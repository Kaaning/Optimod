package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import controleur.Controleur;
import modele.Troncon;

/**
 * @author H4303 - 2014
 */
public class VueTroncon {

	private Color couleur = new Color(123,123,123);
	public Troncon troncon;
	double echelle;
	private Controleur ctrl;

	
	/**Constructeur de VueTroncon
	 * @param troncon : lien vers le modele
	 * @param echelle : echelle d'affichage en fonction du zoom actuel
	 * @param ctrl : lien vers le controleur
	 */
	public VueTroncon(Troncon troncon, double echelle, Controleur ctrl){
		super();
		this.troncon = troncon;
		this.echelle=echelle;
		this.ctrl=ctrl;
	}
	
	/**Renvoie la coordonnee x convertie pour l'affichage
	 * @param noeud : "source" ou "cible"
	 * @return int
	 */
	/**
	 * @param noeud
	 * @return
	 */
	public int getXVue(String noeud){
		int xPlan=0;
		int xVue=0;
		if(noeud.equals("source")){
			xPlan = troncon.getSource().getX();
			xVue = (int)(xPlan*echelle);
//			System.out.println(xPlan+" -> "+xVue+" a "+rayonAjuste);
		}
		else if(noeud.equals("cible")){
			xPlan = troncon.getCible().getX();
			xVue = (int)(xPlan*echelle);
		}
		return xVue;
	}
	/**Renvoie la coordonnee y convertie pour l'affichage
	 * @param noeud : "source" ou "cible"
	 * @return int
	 */
	public int getYVue(String noeud){
		int yPlan=0;
		int yVue=0;
		if(noeud.equals("source")){
			yPlan = troncon.getSource().getY();
			yVue = (int)(yPlan*echelle);
		}
		else if(noeud.equals("cible")){
			yPlan = troncon.getCible().getY();
			yVue = (int)(yPlan*echelle);
		}
		return yVue;
	}
	
	/**Renvoie la couleur d'affichage du troncon
	 * @return Color
	 */
	public Color getCouleur(){
		return couleur;
	}
	
	/**Modifie la couleur d'affichage du troncon
	 * @param couleur
	 */
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
	
	/**Methode pour dessiner un tron�on
	 * @param g
	 * @param origine
	 * @param deplacement
	 */
	public void dessiner(Graphics g, int[] origine, int[] deplacement){
		// turn on anti-alias mode
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		g.setColor(couleur);
		((Graphics2D) g).setStroke(new BasicStroke((float) (3*echelle)));
		g.drawLine(origine[0]+deplacement[0]+getXVue("source"), origine[1]+deplacement[1]+getYVue("source"), origine[0]+deplacement[0]+getXVue("cible"), origine[1]+deplacement[1]+getYVue("cible"));
//		System.out.println("Troncon situ� en "+getXVue("source")+","+getYVue("source"));
	}
	
	/**Modifie l'echelle en fonction du zoom actuel
	 * @param echelle
	 */
	public void changerEchelle(double echelle){
		this.echelle=echelle;
	}
	
	/**Modifie la couleur du tron�on en fonction du nombre de passage (0, 1 , 2 ou 3+)
	 * 
	 */
	public void changerCouleur(){
		if(troncon.getNbPassage()==1){
			couleur = new Color(0,173,0);
		}
		else{
			couleur = new Color(123,123,123);
		}
	}
}
