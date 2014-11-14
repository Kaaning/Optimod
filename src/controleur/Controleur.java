package controleur;

import java.text.ParseException;

import modele.Noeud;
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
	 
	public void chargerLivraison (String nomFic) {
		int res = this.modelZoneGeographique.chargerLivraison(nomFic);
		System.out.println("Livraison erreur: " + res);
		if (res == 0) {
			this.viewAccueil.MAJVueZoneGeographique();
			viewAccueil.afficherMessage("Chargement des livraisons réussi. Vous pouvez maintenant calculer un itinéraire.");
		} else if (res == -1) {
			String messageErreur = "Le fichier XML n'est pas valide !";
			viewAccueil.afficherMessageErreur(messageErreur);
		} else if (res == 1) {
			String messageErreur = "Erreur de conversion dans le fichier XML: adresse ou identifiant negatif";
			viewAccueil.afficherMessageErreur(messageErreur);
		} else if (res == 2) {
			String messageErreur = "Erreur dans le fichier XML !";
			viewAccueil.afficherMessageErreur(messageErreur);
		}	
	}
	
	public void ChargerZoneGeo (String nomFic) {
		this.modelZoneGeographique = new ZoneGeographique();
		int res = modelZoneGeographique.lirePlanXML(nomFic);
		if (res == 0) {
			viewAccueil.creerVueZoneGeographique(1100,500, this.modelZoneGeographique);
			viewAccueil.afficherMessage("Chargement du plan réussi. Veuillez maintenant charger des livraisons.");
		}
		else if (res == -1) {
			String messageErreur = "Le fichier XML n'est pas valide !";
			viewAccueil.afficherMessageErreur(messageErreur);
		} else if (res == 1) {
			String messageErreur = "Erreur de conversion dans le fichier XML: vitesse, longueur ou identifiant negatif";
			viewAccueil.afficherMessageErreur(messageErreur);
		} else if (res == 2){
			String messageErreur = "Erreur dans le fichier XML !";
			viewAccueil.afficherMessageErreur(messageErreur);
		}
	}
	
	public int AjouterLivraison () {
		ConcreteCommandAjouterLivraison command = new ConcreteCommandAjouterLivraison();
		command.execute();
		this.invoker.addCommand(command);
		this.viewAccueil.MAJVueZoneGeographique();
		return 0;
	}
	public int SupprimerLivraison() {
		ConcreteCommandSupprimerLivraison command = new ConcreteCommandSupprimerLivraison(modelZoneGeographique , 0);
		command.execute();
		this.invoker.addCommand(command);
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
	}
	
	public ZoneGeographique getModelZoneGeographique() {
		return this.modelZoneGeographique;
	}
	
	public static void main(String[] args) throws ParseException{
		 	
		 Controleur controler = new Controleur();
		
		 
			
		}

	
	 


}
