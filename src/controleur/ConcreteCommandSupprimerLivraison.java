package controleur;

import modele.Livraison;
import modele.ZoneGeographique;

public class ConcreteCommandSupprimerLivraison implements Command {
	
	private ZoneGeographique zoneGeo;
	private Livraison supprime;
	private Livraison precedente;
	
	
	
public ConcreteCommandSupprimerLivraison(ZoneGeographique zoneGeo , Livraison l) {
		supprime = l;
		this.zoneGeo = zoneGeo;
	}

	@Override
	public int execute() {
		zoneGeo.supprimerLivraison(supprime);
		return 0;
	}

	@Override
	public int unexecute() {
		zoneGeo.ajouterLivraison(supprime , precedente);
		return 0;
	}

}
