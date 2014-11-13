/**
 * 
 */
package modele;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import vue.Accueil;
import bibliothequesTiers.*;


/**
 * Classe de Gestion des Zones Geographiques
 * @author MohamedRiadh
 *
 */

public class ZoneGeographique {
	private List<Noeud> noeuds = new ArrayList<Noeud>(); 
	private List<Troncon> troncons = new ArrayList<Troncon>();
	private int Xmin;
	private int Xmax;
	private int Ymin;
	private int Ymax;
	private boolean reussi = true;
	
	private Tournee demandes;
	
	/**
	 * Constructeur avec parametres de l'Objet ZoneGeographique
	 * @param nomFic String chemin relatif du fichier xml du plan a charger
	 * @throws remonte des Exceptions reliees a  l'ouverture d'un fichier et l'utlisiation de JDOM pour parser le fichier XML
	 */
	public ZoneGeographique(String nomFic) throws JDOMException, IOException, ParserConfigurationException, NumberFormatException, SAXException {
		lirePlanXML(nomFic);
		this.Xmax = findXMax();
	    this.Ymax = findYMax();
		this.Xmin = findXMin();
	    this.Ymin = findYMin();
	}
	
	
	/**
	 * Accesseur de l'Attribut noeuds
	 * @return List<Noeud> Liste des Objets Noeuds contenus dans le plan a charger
	 *
	 */
	public List<Noeud> getNoeuds() {
		return this.noeuds;
	}
	
	/**
	 * Accesseur de l'Attribut troncons
	 * @return List<Troncon> Liste des Objets Troncon contenus dans le plan a  charger
	 *
	 */
	public List<Troncon> getTroncons() {
		return this.troncons;
	}
	
	/**
	 * Accesseur de l'Attribut Xmin
	 * @return int la valeur de l'absisse du noeud la plus petite du plan a  charger
	 *
	 */
	public int getXMin() {
		return Xmin;
	}
	
	/**
	 * Accesseur de l'Attribut Xmax
	 * @return int la valeur de l'absisse du noeud la plus grande du plan a  charger
	 *
	 */
	public int getXMax() {
		return Xmax;
	}
	
	/**
	 * Accesseur de l'Attribut Ymin
	 * @return int la valeur de l'ordonna©e du noeud la plus petite du plan a  charger
	 *
	 */
	public int getYMin() {
		return Ymin;
	}
	
	/**
	 * Accesseur de l'Attribut Ymax
	 * @return int la valeur de l'ordonna©e du noeud la plus grande du plan a  charger
	 *
	 */
	public int getYMax() {
		return Ymax;
	}
	
	/**
	 * Accesseur de l'Attribut reussi
	 * @return boolean true si la lecture du fichier XML qui contient le plan a charger a reussi false sinon
	 *
	 */
	public boolean getReussi() {
		return reussi;
	}
	
	public Tournee getDemandes() {
		return demandes;
	}
	
	/**
	 * Ajoute un Objet Noeud a l'Attribut noeuds (qui represente la liste des noeuds du plan a charger)
	 * @param noeud Objet Noeud a rajouer a la liste 
	 *
	 */
	public void ajouterNoeud(Noeud noeud) {
		this.noeuds.add(noeud);
	}
	
	/**
	 * Ajoute un Objet Troncon a  l'Attribut troncons (qui repra©sente la liste des troncons du plan a  charger)
	 * @param troncon Objet Troncon a  rajouer a  la liste 
	 *
	 */
	public void ajouterTroncon(Troncon Troncon) {
		this.troncons.add(Troncon);
	}
	
	/**
	 * Recherche et retourne un Objet Noeud dans la liste des noeuds du plan a  charger selon son Identifiant (Attribut Id de l'Objet Noeud)
	 * @param id int la valeur de l'Identifiant de l'Objet Noeud a  rechercher
	 * @return Noeud dont l'Identifiant est a©gal au parama¨tre id passa© en parama¨tre 
	 *
	 */
	public Noeud findNoeudById(int id) {
		for (int i=0; i<this.noeuds.size(); i++) {
			if (this.noeuds.get(i).getId() == id) {
				return this.noeuds.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Methode privee appelee dans le constructeur
	 * lit le fichier XML dans le chemin est passe en parametre, utilise la librairie JDOM pour parser le fichier XML
	 * remplit les listes noeuds et troncons
	 * @param nomFic String chemin relatif du fichier xml du plan a charger
	 *
	 */
	private void lirePlanXML(String nomFic) throws JDOMException, IOException, ParserConfigurationException, SAXException {
		
		try {
			XMLValidateur.validerXML(nomFic, "res\\plan.xsd");
		} catch (SAXException | IOException | ParserConfigurationException e1) {
			System.out.println(e1.getMessage());
			this.reussi = false;
		}
		
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		Element racine;			
		
		try {
			document = sxb.build(new File(nomFic));
		} catch (JDOMException | IOException e) {
			System.out.println(e.getMessage());
			this.reussi = false;
		}
	   
		racine = document.getRootElement();
	    List<Element> listeNoeuds= new ArrayList<Element>();
	    listeNoeuds = racine.getChildren("Noeud");
	    
	    Iterator<Element> i = listeNoeuds.iterator();
	    
	    while(i.hasNext())
	    {
	       Element courant = (Element)i.next();
	       Noeud noeud = new Noeud(Integer.parseInt(courant.getAttributeValue("id")), Integer.parseInt(courant.getAttributeValue("x")) ,Integer.parseInt(courant.getAttributeValue("y")));  
	       ajouterNoeud(noeud);
	    }
	    
	    for (int cpt=0; cpt<listeNoeuds.size(); cpt++) {
	    	List<Element> listeTroncons = new ArrayList<Element>();
	    	listeTroncons = listeNoeuds.get(cpt).getChildren("LeTronconSortant");
	    	Iterator<Element> it = listeTroncons.iterator();
		    while(it.hasNext())
			{
		       Element tronconCourant = (Element)it.next();
		       if (Double.parseDouble(tronconCourant.getAttributeValue("vitesse").replace(',', '.')) < 0 || Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.')) < 0 || Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("x")) < 0 || Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("y")) < 0) {
		    	   this.reussi = false;
		       }
	    	   Troncon troncon = new Troncon(tronconCourant.getAttributeValue("nomRue"), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.'))/Double.parseDouble(tronconCourant.getAttributeValue("vitesse").replace(',', '.')), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.')));
	    	   troncon.setCible(this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))));
	    	   troncon.setSource(this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))));
	    	   this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))).ajouterTronconSortant(troncon);
	    	   this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))).ajouterTronconEntrant(troncon);
	    	   ajouterTroncon(troncon);
		    }	
	    }
	}
	
	/**
	 * Methode privee appelee dans le constructeur
	 * recherche la plus grande absisse des noeuds du plan a charger
	 * @return int la valeur de la plus grande absisse des noeuds a charger 
	 *
	 */
	private int findXMax() {
		int temp = this.noeuds.get(0).getX();
		for (Noeud n : noeuds) {
			if (n.getX() > temp) {
				temp = n.getX();
			}
		}
		return temp;
	}
	
	/**
	 * Methode privee appelee dans le constructeur
	 * recherche la plus grande ordonnee des noeuds du plan a charger
	 * @return int la valeur de la plus grande ordonnee des noeuds a charger 
	 *
	 */
	private int findYMax() {
		int temp = this.noeuds.get(0).getY();
		for (Noeud n : noeuds) {
			if (n.getY() > temp) {
				temp = n.getY();
			}
		}
		return temp;		
	}
	
	/**
	 * Methode privee appelee dans le constructeur
	 * recherche la plus petite absisse des noeuds du plan a charger
	 * @return int la valeur de la plus petite absisse des noeuds a charger 
	 *
	 */
	private int findXMin() {
		int temp = this.noeuds.get(0).getX();
		for (Noeud n : noeuds) {
			if (n.getX() < temp) {
				temp = n.getX();
			}
		}
		return temp;	
	}
	
	/**
	 * Methode privee appelee dans le constructeur
	 * recherche la plus petite ordonna©e des noeuds du plan a charger
	 * @return int la valeur de la plus petite ordonna©e des noeuds a charger 
	 *
	 */
	private int findYMin() {
		int temp = this.noeuds.get(0).getY();
		for (Noeud n : noeuds) {
			if (n.getY() < temp) {
				temp = n.getY();
			}
		}
		return temp;		
	}
	
	/**
	 * Methode qui verifie si le noeud dont l'Id est passe en parametre a ete visitae dans la tournee ou pas
	 * @param id int Id du noeud a verifier
	 * @return boolean true si le noeud passe en parametre a ete visite false sinon 
	 *
	 */
	public boolean verifierNoeud(int id) {
		for (Noeud n : noeuds) {
			if (n.getId()==id) {
				n.setEtat(0);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Construit une tournee et l'affecte à l'Attribut demandes de l'Objet ZoneGeographique
	 * @param nomFic String nom du fichier XML qui contient la liste des livraisons a charger 
	 *
	 */
	public void chargerLivraison(String nomFic) throws ParseException{
		demandes = new Tournee(nomFic,this);
	}
	
	//Cree un objet de type Acceuil du package vue qui represente la vue principale de l'application
	public static void main(String[] args) throws ParseException, JDOMException, IOException {
		//j.creerZoneGeographique("fic/plan10x10.xml");
		//j.chargerLivraison("fic/livraison20x20-2.xml");
		Accueil a = new Accueil();	
	}
}
