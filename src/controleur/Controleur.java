package controleur;

import java.text.ParseException;

import modele.Noeud;
import modele.ZoneGeographique;
import vue.Accueil;


/**
 * @author H4303 - 2014
 */
public class Controleur {
	

	private Invoker invoker;
	
	 private Accueil viewAccueil;
	 private ZoneGeographique modelZoneGeographique;
	  
	 /**
	 * Constructeur de Controleur
	 */
	public Controleur() {
		
		this.invoker = new Invoker();
		this.viewAccueil = new Accueil(this);
		
		 
	}
	 
	/**Charge un fichier contenant des livraisons
	 * @param chemin : chemin vers le fichier
	 * @return int
	 */
	public int chargerLivraison (String chemin ) {
				try {
					this.modelZoneGeographique.chargerLivraison(chemin);
					this.viewAccueil.MAJVueZoneGeographique();
					return 0;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 1;
				}	
	}
	/**Charger un fichier contenant les noeuds et troncons
	 * @param nomFic : nom du fichier
	 */
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
	
	/**Ajoute une livraison
	 * @return int : eventuel code d'erreur
	 */
	public int AjouterLivraison () {
		ConcreteCommandAjouterLivraison command = new ConcreteCommandAjouterLivraison();
		command.execute();
		this.invoker.addCommand(command);
		this.viewAccueil.MAJVueZoneGeographique();
		return 0;
	}
	/**Supprime une livraison
	 * @return int : eventuel code d'erreur
	 */
	public int SupprimerLivraison() {
		ConcreteCommandSupprimerLivraison command = new ConcreteCommandSupprimerLivraison(modelZoneGeographique , 0);
		command.execute();
		this.invoker.addCommand(command);
		this.viewAccueil.MAJVueZoneGeographique();
		return 0;
	}
	/**Annule la dernière commande
	 * @return
	 */
	public int undo(){
		int retour = invoker.undo();
		this.viewAccueil.MAJVueZoneGeographique();
		return retour;
	}
	/**Refais la dernière commande annule
	 * @return
	 */
	public int redo(){
		return this.invoker.redo();
	}
	/**Calcule l'itineraire
	 * @return int : eventuel code d'erreur
	 */
	public int CalculerItineraire () {
		return this.modelZoneGeographique.calculerItineraire();
	}
	
	/**Affiche les infos de la livraison cliquee
	 * @param n : Noeud clique
	 */
	public void infoLivraison(Noeud n){
		viewAccueil.MAJVueEtape(n);
	}
	
	/**Renvoie le modele ZoneGeoGraphique
	 * @return ZoneGeographique
	 */
	public ZoneGeographique getModelZoneGeographique() {
		return this.modelZoneGeographique;
	}
	
	public String exporterTournee(){
		String texte = "Bonjour William\n";
		for(int i=2; i<101; i++){
			texte+="Ceci est la "+i+"eme ligne.\n";
		}
		texte+="Au revoir !";
		return texte;
	}
	
	public static void main(String[] args) throws ParseException{
		 	
		 Controleur controler = new Controleur();
		
		 
			
		}

	
	 


}
