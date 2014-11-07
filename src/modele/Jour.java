package modele;

import java.io.File;
import java.text.ParseException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Jour {
	
	public void creerZoneGeographique(String nomFichier){
		
	}
	
	public void chargerLivraison(String nomFichier){
		
	}
	
	public boolean calculerItineraire(){
		return true;
	}
	
	public static void main(String[] args) throws ParseException{
		
		DemandesDeLivraison l = new DemandesDeLivraison("fic/livraison10x10-2.xml");
		
	}
	
}
