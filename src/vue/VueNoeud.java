package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import modele.Noeud;

public class VueNoeud extends JComponent implements MouseListener{

	private Color couleur;
	private Noeud noeud;
	
	public VueNoeud(Noeud noeud){
		super();
		this.noeud = noeud;
		this.couleur = Color.red;
		enableInputMethods(true);   
		addMouseListener(this);
	}
	
	public int getXVue(){
		//Convertit le x plan en x vue
		int xPlan = noeud.getX();
		int xVue = xPlan/2;
		return xVue;
	}
	
	public int getYVue(){
		int yPlan = noeud.getY();
		int yVue = yPlan/2;
		return yVue;
	}
	
	public int getXplan(){
		return noeud.getX();
	}
	
	public int getYplan(){
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
		g.setColor(couleur);
        g.fillOval(getXVue(), getYVue(), 30, 30);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("pdpdpd");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
