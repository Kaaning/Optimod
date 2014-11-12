package modele;

import java.util.List;

public class Etape {
	List<Troncon> troncons;
	Livraison livraison;
	
	/*public Etape(int[]) {
		// TODO : Gerer l'entrepot en debut et fin de parcours
		for(int i = 0; i < next.length; i++) {
			
		}
	}*/
	
	public Troncon obtainTroncon(Noeud nSource, Noeud nDest) {
		for(Troncon t : nSource.getTronconsSortant()) {
			if(t.getCible() == nDest)
				return t;
		}
		return null;
	}
}
