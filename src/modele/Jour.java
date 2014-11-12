package modele;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import vue.*;

public class Jour {
	
	private DemandesDeLivraison demandes;
	private ZoneGeographique plan;
	
	public void creerZoneGeographique(String nomFichier) throws JDOMException, IOException{
		plan = new ZoneGeographique(nomFichier);
	}
	
	public void chargerLivraison(String nomFichier) throws ParseException{
		demandes = new DemandesDeLivraison(nomFichier, this);
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
	
	public ZoneGeographique getPlan(){
		return plan;
	}
	
	public static void main(String[] args) throws ParseException, JDOMException, IOException{
		Jour j = new Jour();
		//j.creerZoneGeographique("fic/plan10x10.xml");
		//j.chargerLivraison("fic/livraison20x20-2.xml");
		Accueil a = new Accueil(j);
		
	}
	
}
