package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import modele.*;

import org.jdom2.JDOMException;

import vue.Accueil;

public class Command {
	
	private static ZoneGeographique plan;
	private static Accueil accueil;
	
	public int execute(){
		return 0;
	}
	
	public int unexecute(){
		return 0;
	}
	
	public static ZoneGeographique creerZoneGeo(String nomFic) throws JDOMException, IOException{
		plan = new ZoneGeographique(nomFic);
		return plan;
	}
	
	public void infoLivraison(int id){
		List<Livraison> livraisons = plan.getDemandes().getLivraison();
		for(Livraison l : livraisons){
			if(l.getNoeud()==id){
				accueil.message(l.display());
			}
		}
	}
	
	public static void main(String[] args) throws ParseException, JDOMException, IOException{
		Command cmd = new Command();
		//j.creerZoneGeographique("fic/plan10x10.xml");
		//j.chargerLivraison("fic/livraison20x20-2.xml");
		accueil = new Accueil(cmd);
		
	}
}


