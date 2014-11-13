package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.jdom2.JDOMException;

import modele.Livraison;
import modele.ZoneGeographique;
import vue.Accueil;


public class Controleur {
	

	private Invoker invoker;
	
	 private Accueil viewAccueil;
	 private ZoneGeographique modelZoneGeographique;
	  
	 public Controleur() {
		
		this.invoker = new Invoker();
		this.viewAccueil = new Accueil(this);
		
		 
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
			modelZoneGeographique.lirePlanXML(chemin);
			viewAccueil.creerVueZoneGeographique(1100,500, this.modelZoneGeographique);
			return 0;
	}
	
	public ZoneGeographique getModelZoneGeographique() {
		return this.modelZoneGeographique;
	}
	
//	public void infoLivraison(int id){
//		List<Livraison> livraisons = plan.getTournee().getLivraison();
//		for(Livraison l : livraisons){
//			if(l.getNoeud()==id){
//				accueil.message(l.display());
//			}
//		}
//	}

	
	public static void main(String[] args) throws ParseException{
		 	
		 Controleur controler = new Controleur();
			
		}

	
	 


}
