package controleur;

import java.text.ParseException;

import modele.Livraison;
import modele.Noeud;
import modele.ZoneGeographique;
import vue.Accueil;


public class Controleur {
	

	private Invoker invoker;
	
	 private Accueil viewAccueil;
	 private ZoneGeographique modelZoneGeographique;
	 private Noeud noeudClicked;
	  
	 public Controleur() {
		
		this.invoker = new Invoker();
		this.viewAccueil = new Accueil(this);
		
		 
	}
	 
	public int chargerLivraison (String chemin ) {
				try {
					this.modelZoneGeographique.chargerLivraison(chemin);
					this.viewAccueil.CreerVueTournee();
					return 0;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
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
	public int AjouterLivraison (int client, int id) {
		if(noeudClicked==null){
			return -1;
		}
		ConcreteCommandAjouterLivraison command = new ConcreteCommandAjouterLivraison(client, id, noeudClicked);
		command.execute();
		this.invoker.addCommand(command);
		this.viewAccueil.MAJVueZoneGeographique();
		return 0;
	}
	public int SupprimerLivraison(Livraison l) {
		ConcreteCommandSupprimerLivraison command = new ConcreteCommandSupprimerLivraison(modelZoneGeographique , l);
		command.execute();
		//this.invoker.addCommand(command);
		this.viewAccueil.MAJVueZoneGeographique();
		return 0;
	}
	public int undo(){
		int retour = invoker.undo();
		this.viewAccueil.MAJVueZoneGeographique();
		return retour;
	}
	public int redo(){
		return this.invoker.redo();
	}
	public int CalculerItineraire () {
		return this.modelZoneGeographique.calculerItineraire();
	}
	
	public void infoLivraison(Noeud n){
		viewAccueil.MAJVueEtape(n);
		noeudClicked = n;
	}
	
	public ZoneGeographique getModelZoneGeographique() {
		return this.modelZoneGeographique;
	}
	
	public void afficherMessageErreur(String mess){
		viewAccueil.afficherMessageErreur(mess);
	}
	
	
	public static void main(String[] args) throws ParseException{
		 	
		 Controleur controler = new Controleur();
		
		 
			
		}

	
	 


}
