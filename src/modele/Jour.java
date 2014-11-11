package modele;

import java.io.File;
import java.text.ParseException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import vue.acceuil;

public class Jour {
	
	private static DemandesDeLivraison demandes;
	
	public void creerZoneGeographique(String nomFichier){
		
	}
	
	public void chargerLivraison(String nomFichier) throws ParseException{
		demandes = new DemandesDeLivraison(nomFichier);
	}
	
	public void supprimerLivraison(int noeud){
		demandes.supprimerLivraison(noeud);
	}
	
	public void ajouterLivraison(PlageHoraire plage, int id, int idClient, int idNoeud, int nextNoeud){
		plage.ajouterLivraison(id, idClient, idNoeud);
	}
	
	public boolean calculerItineraire(){
		return true;
	}
	
	public DemandesDeLivraison getDemandes() {
		return demandes;
	}
	
	public static void main(String[] args) throws ParseException{
		Jour j = new Jour();
		acceuil a = new acceuil(j);
		
	}
	
	
}
