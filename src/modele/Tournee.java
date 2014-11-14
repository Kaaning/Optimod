package modele;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Adler32;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import bibliothequesTiers.XMLValidateur;


public class Tournee {
	
	private int entrepot;
	private List<PlageHoraire> plages; 
	private ZoneGeographique zg;
	private int erreur;
	
	private int lireFichierXML(String nomFic, ZoneGeographique unZg) {
		int erreur = 0;
		try {
			if (XMLValidateur.validerXML(nomFic, "res\\livraison.xsd")) {
				plages = new ArrayList<PlageHoraire>();
				
				Element JourneeType = getRacine(nomFic);
				
				//récupération de l'adresse de l'entrepot
				entrepot = Integer.parseInt(JourneeType.getChild("Entrepot").getAttributeValue("adresse"));
				
				//récupération de la liste des plages horaire
				List<Element> listPlage = JourneeType.getChildren("PlagesHoraires").get(0).getChildren("Plage");
				
			    Iterator<Element> i = listPlage.iterator();
	
			    //On parcours la liste grâce à un iterator
			     while(i.hasNext())
			     {
			        Element currentPlage = (Element)i.next();
			        String heureD = currentPlage.getAttributeValue("heureDebut");
			        String heureF = currentPlage.getAttributeValue("heureFin");
			        
			        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
			        Date hd;
					
					hd = formatter.parse(heureD);
					Date hf = formatter.parse(heureF);
			        PlageHoraire plageHoraire = new PlageHoraire(hd, hf); 
			        plages.add(plageHoraire);
			        
			        List<Element> listeLivraisons = currentPlage.getChildren("Livraisons").get(0).getChildren("Livraison");
			        Iterator<Element> j = listeLivraisons.iterator();
			        
			        while(j.hasNext())
			        {
			        	Element currentLivraison = (Element)j.next();
			        	if (Integer.parseInt(currentLivraison.getAttributeValue("id")) < 0 || Integer.parseInt(currentLivraison.getAttributeValue("adresse")) < 0) {
			        		erreur = 1;
			        	} else {
				        	int id = Integer.parseInt(currentLivraison.getAttributeValue("id"));
				        	int client = Integer.parseInt(currentLivraison.getAttributeValue("client"));
				        	int adresse = Integer.parseInt(currentLivraison.getAttributeValue("adresse"));
				        	Noeud noeud = new Noeud();
				        	noeud = unZg.getNoeudById(adresse);
				        	if(noeud!=null){
					        	plageHoraire.ajouterLivraison(id, client, noeud);
				        	}
			        	}
			        }
			        
			      }
				} else {
					erreur = -1;
				}
			} catch (NullPointerException | ParseException | NumberFormatException | SAXException | IOException | ParserConfigurationException e) {
				System.out.println(e.getMessage());
				erreur = 2;
			}
	     return erreur;
	}
	
	public Tournee(String nomFic, ZoneGeographique unZg) {
		this.erreur = lireFichierXML(nomFic, unZg);
	}
	
	public int getErreur() {
		return this.erreur;
	}
	public void supprimerLivraison(Noeud adresse){
		Iterator<PlageHoraire> i = plages.iterator();
		
		while(i.hasNext()){
			PlageHoraire currentPlage = i.next();
			Iterator<Livraison> j = currentPlage.getLivraisons().iterator();
			
			while (j.hasNext()){
				Livraison currentLivraison = j.next();
				if (currentLivraison.getNoeud()==adresse){
					j.remove();
				}
			}
			
		}
	}
	
	private static Element getRacine(String nomFic){
		
		org.jdom2.Document document = new Document();
		//On crée une instance de SAXBuilder
	    SAXBuilder sxb = new SAXBuilder();
	    try
	    {
	       //On crée un nouveau document JDOM avec en argument le fichier XML
	       //Le parsing est terminé ;)
	       document = sxb.build(new File(nomFic));
	    }
	    catch(Exception e){
	    	document = null;
	    }
	
	    //On initialise un nouvel élément racine avec l'élément racine du document.
	    
	    return document.getRootElement();
	}
	
	public List<PlageHoraire> getPlages(){
		return plages;
	}
	
	public int getEntrepot(){
		return entrepot;
	}
	
	public void display(){
		for(int i =0; i < plages.size() ; i++){
			System.out.println("Plage : " + plages.get(i).getHeureDebut() + "  "+ plages.get(i).getHeureFin());
			for(int j =0 ; j < plages.get(i).getLivraisons().size() ; j++){
				System.out.println("Livraisons : " + plages.get(i).getLivraisons().get(j).getNoeud());
			}	
		}
	}

	public List<Livraison> getLivraison(){
		List<Livraison> livraisons = new ArrayList<Livraison>();
		for (int i = 0 ; i < plages.size() ; i++){
			for (int j = 0 ; j<plages.get(i).getLivraisons().size() ; j++){
				livraisons.add(plages.get(i).getLivraisons().get(j));
			}
		}
		return livraisons;
	}
	
	public int calculerItineraire() {
		// mettre la vrai fonction
		return 0;
	}
	
}
