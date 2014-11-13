package controleur;

import java.io.IOException;
import java.text.ParseException;

import org.jdom2.JDOMException;

import modele.ZoneGeographique;
import vue.Accueil;


public class Controler {
	

	private Invoker invoker;
	
	 private Accueil viewAcceuil;
	 private ZoneGeographique modelZoneGeographique;
	  
	 public Controler() {
		
		this.invoker = new Invoker();
		this.viewAcceuil = new Accueil(this);
		
		 
	}
	 
	public int chargerLivraison (String chemin ) {
		
			try {
				modelZoneGeographique.chargerLivraison(chemin);
				return 0;
			} catch (ParseException e) {
				e.printStackTrace();
				return 1;
			}

	}
	
	public int ChargerZoneGeo (String chemin) {
			this.modelZoneGeographique = new ZoneGeographique();
			ConcreteCommandChargerZoneGeo command = new ConcreteCommandChargerZoneGeo(chemin , modelZoneGeographique);
			int retour = command.execute();
			invoker.addCommand(command);
			viewAcceuil.creerVueZoneGeographique(0,0,500,500, 500.0/800.0, this.modelZoneGeographique);
			return 0;
	}
	
	public ZoneGeographique getModelZoneGeographique() {
		return this.modelZoneGeographique;
	}

	
	public static void main(String[] args) throws ParseException{
		 	
		 Controler controler = new Controler();
			
		}

	
	 


}
