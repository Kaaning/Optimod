package modele;

import java.util.ArrayList;

public class ZoneGeographique {

	private ArrayList<Noeud> noeuds;
	private ArrayList<Tron�on> tron�ons;
	
	public ZoneGeographique(){
		noeuds = new ArrayList<Noeud>();
		tron�ons = new ArrayList<Tron�on>();
		noeuds.add(new Noeud(1,23,23));
		noeuds.add(new Noeud(2,23,771));
		noeuds.add(new Noeud(4,775,771));
		noeuds.add(new Noeud(5,775,23));
		tron�ons.add(new Tron�on(noeuds.get(0), noeuds.get(1)));
		tron�ons.add(new Tron�on(noeuds.get(1), noeuds.get(2)));
		tron�ons.add(new Tron�on(noeuds.get(2), noeuds.get(3)));
		tron�ons.add(new Tron�on(noeuds.get(3), noeuds.get(0)));
	}
	
	public ArrayList<Noeud> getNoeuds(){
		return noeuds;
	}
	
	public ArrayList<Tron�on> getTron�ons(){
		return tron�ons;
	}
}
