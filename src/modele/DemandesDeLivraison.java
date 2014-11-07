package modele;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class DemandesDeLivraison {
	
	private int entrepot;
	private List<PlageHoraire> plages; 
	
	public DemandesDeLivraison(String nomFic) throws ParseException{
		
		plages = new ArrayList<PlageHoraire>();
		
		try{
		Element JourneeType = getRacine(nomFic);
		
		//r�cup�ration de l'adresse de l'entrepot
		entrepot = Integer.parseInt(JourneeType.getChild("Entrepot").getAttributeValue("adresse"));
		
		//r�cup�ration de la liste des plages horaire
		List<Element> listPlage = JourneeType.getChildren("PlagesHoraires").get(0).getChildren("Plage");
		
		
	    Iterator<Element> i = listPlage.iterator();
	    //On parcours la liste gr�ce � un iterator
	     while(i.hasNext())
	     {
	        Element currentPlage = (Element)i.next();
	        String heureD = currentPlage.getAttributeValue("heureDebut");
	        String heureF = currentPlage.getAttributeValue("heureFin");
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
	        Date hd = formatter.parse(heureD);
	        Date hf = formatter.parse(heureF);			        
	        
	        PlageHoraire plageHoraire = new PlageHoraire(hd, hf); 
	        plages.add(plageHoraire);
	        
	        List<Element> listeLivraisons = currentPlage.getChildren("Livraisons").get(0).getChildren("Livraison");
	        Iterator<Element> j = listeLivraisons.iterator();
	        
	        while(j.hasNext())
	        {
	        	Element currentLivraison = (Element)j.next();
	        	int id = Integer.parseInt(currentLivraison.getAttributeValue("id"));
	        	int client = Integer.parseInt(currentLivraison.getAttributeValue("client"));
	        	int adresse = Integer.parseInt(currentLivraison.getAttributeValue("adresse"));
	        	plageHoraire.ajouterLivraison(id, client, adresse); 
	        	
	        }
	        
	      }
		}
		catch(Exception e){
		}
		
		
		
	}
	
	public static Element getRacine(String nomFic){
		
		org.jdom2.Document document = new Document();
		//On cr�e une instance de SAXBuilder
	    SAXBuilder sxb = new SAXBuilder();
	    try
	    {
	       //On cr�e un nouveau document JDOM avec en argument le fichier XML
	       //Le parsing est termin� ;)
	       document = sxb.build(new File(nomFic));
	    }
	    catch(Exception e){}
	
	    //On initialise un nouvel �l�ment racine avec l'�l�ment racine du document.
	    
	    return document.getRootElement();
	}
	

}