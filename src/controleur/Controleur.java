package controleur;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

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
				this.modelZoneGeographique.chargerLivraison(chemin);
				return 0;
			} catch (ParseException e) {
				e.printStackTrace();
				return 1;
			}

	}
	
	/**
	 * Methode qui cree un Objet ZoneGeographique du modele a partir d'un fichier XML
	 * @param nomFic String chemin relatif du fichier xml du plan a charger
	 * @return int la valeur de l'erreur retournee par la methode lirePlanXML de la classe ZoneGeographique du modele
	 * 		0 si le chargement du fichier XML se déroule bien
	 * 		1 s'il y une erreur de conversion d'un des attributs d'un troncon (vitesse<0, longueur<0 ou id<0)
	 * 		-1 si le fichier n'est pas valide
	 * 		2 si une autre erreur lors de chargement du fichier XML se produit (pas de droit etc)
	 * @throws NumberFormatException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws JDOMException
	 * 
	 */
	public int ChargerZoneGeo (String nomFic) throws NumberFormatException, SAXException, IOException, ParserConfigurationException, JDOMException {
			this.modelZoneGeographique = new ZoneGeographique();
			int res = modelZoneGeographique.lirePlanXML(nomFic);
			if (res == 0) {
				viewAccueil.creerVueZoneGeographique(1100,500, this.modelZoneGeographique);
			}
			return res;
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
	
//	public void infoLivraison(int id){
//		List<Livraison> livraisons = plan.getTournee().getLivraison();
//		for(Livraison l : livraisons){
//			if(l.getNoeud()==id){
//				accueil.message(l.display());
//			}
//		}
//	}
	
	/**
	 * Accesseur de l'Attribut modelZoneGeographique 
	 * @return ZoneGeographique l'Objet aatribut de l'Objet ZoneGeographique
	 * 
	 */
	public ZoneGeographique getModelZoneGeographique() {
		return this.modelZoneGeographique;
	}

	
	public static void main(String[] args) throws ParseException{
		 	
		 Controleur controler = new Controleur();
		
		 
			
		}

	
	 


}
