package modele;

import java.util.List;

public class Etape {
	List<Troncon> troncons;
	Livraison livraison;
	String heurePassage;
	
	
	
	
	
	public Etape(List<Troncon> troncons, String heurePassage) {
		super();
		this.troncons = troncons;
		this.heurePassage = heurePassage;
	}





	public Troncon obtainTroncon(Noeud nSource, Noeud nDest) {
		for(Troncon t : nSource.getTronconsSortant()) {
			if(t.getCible() == nDest)
				return t;
		}
		return null;
	}
}
