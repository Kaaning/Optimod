package modele;

import java.util.ArrayList;

public class ZoneGeographique {

	private ArrayList<Noeud> noeuds;
	private ArrayList<Tronçon> tronçons;
	
	public ZoneGeographique(){
		noeuds = new ArrayList<Noeud>();
		tronçons = new ArrayList<Tronçon>();
		noeuds.add(new Noeud(1,23,23));
		noeuds.add(new Noeud(2,23,771));
		noeuds.add(new Noeud(4,775,771));
		noeuds.add(new Noeud(5,775,23));
		tronçons.add(new Tronçon(noeuds.get(0), noeuds.get(1)));
		tronçons.add(new Tronçon(noeuds.get(1), noeuds.get(2)));
		tronçons.add(new Tronçon(noeuds.get(2), noeuds.get(3)));
		tronçons.add(new Tronçon(noeuds.get(3), noeuds.get(0)));
	}
	
	public ArrayList<Noeud> getNoeuds(){
		return noeuds;
	}
	
	public ArrayList<Tronçon> getTronçons(){
		return tronçons;
	}
}
