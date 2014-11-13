package controleur;


import modele.ZoneGeographique;

public class ConcreteCommandChargerZoneGeo  implements Command{

	private String chemin;
	private ZoneGeographique zoneGeo;
	
	
	public ConcreteCommandChargerZoneGeo(String chemin, ZoneGeographique zoneGeo) {
		super();
		this.chemin = chemin;
		this.zoneGeo = zoneGeo;
	}

	@Override
	public int execute() {
		return zoneGeo.lirePlanXML(chemin);
	}

	@Override
	public int unexecute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
