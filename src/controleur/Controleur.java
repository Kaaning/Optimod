package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import modele.Livraison;
import modele.ZoneGeographique;

import org.jdom2.JDOMException;

import vue.Accueil;

public class Controleur {

	private static ZoneGeographique plan;
	private static Accueil accueil;
	
	Controleur(){}
	
	public static ZoneGeographique creerZoneGeo(String nomFic) throws JDOMException, IOException{
		plan = new ZoneGeographique(nomFic);
		return plan;
	}
	
	public void infoLivraison(int id){
		List<Livraison> livraisons = plan.getTournee().getLivraison();
		for(Livraison l : livraisons){
			if(l.getNoeud()==id){
				accueil.message(l.display());
			}
		}
	}
	
	
	public static void main(String[] args) throws ParseException, JDOMException, IOException{
		Controleur ctrl = new Controleur();
		//j.creerZoneGeographique("fic/plan10x10.xml");
		//j.chargerLivraison("fic/livraison20x20-2.xml");
		accueil = new Accueil(ctrl);
		
	}
}
