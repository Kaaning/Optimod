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

import bibliothequesTiers.XMLValidateur;

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
	private Tournee tournee;
	
	
	/**
	 * Constructeur sans parametres de l'Objet ZoneGeographique
	 *
	 */
	public ZoneGeographique() {	
	}
	
	/**
	 * Methode qui cahrge les livraison a partir d'un fichier XML et construit avec l'Objet Tournee, attribut de l'Objet ZoneGeographique
	 * @param nomFic String adresse du fichier XML qui contient les livraisons a charger dans la tournee
	 * @throws ParseException lie a lrouverture du fichier XML
	 */
	public void chargerLivraison(String nomFic) throws ParseException{
		tournee = new Tournee(nomFic,this);
	}
	
	/**
	 * Accesseur de l'Attribut noeuds
	 * @return List<Noeud> Liste des Objets Noeuds contenus dans le plan à charger
	 *
	 */
	public List<Noeud> getNoeuds() {
		return this.noeuds;
	}
	
	/**
	 * Accesseur de l'Attribut troncons
	 * @return List<Troncon> Liste des Objets Troncon contenus dans le plan à charger
	 *
	 */
	public List<Troncon> getTroncons() {
		return this.troncons;
	}
	
	/**
	 * Accesseur de l'Attribut Xmin
	 * @return int la valeur de l'absisse du noeud la plus petite du plan à charger
	 *
	 */
	public int getXMin() {
		return Xmin;
	}
	
	/**
	 * Accesseur de l'Attribut Xmax
	 * @return int la valeur de l'absisse du noeud la plus grande du plan à charger
	 *
	 */
	public int getXMax() {
		return Xmax;
	}
	
	/**
	 * Accesseur de l'Attribut Ymin
	 * @return int la valeur de l'ordonnee du noeud la plus petite du plan a charger
	 *
	 */
	public int getYMin() {
		return Ymin;
	}
	
	/**
	 * Accesseur de l'Attribut Ymax
	 * @return int la valeur de l'ordonnée du noeud la plus grande du plan a charger
	 *
	 */
	public int getYMax() {
		return Ymax;
	}
	
	/**
	 * Accesseur de l'Attribut Tournee
	 * @return Tournee attribut tournee de l'Objet ZoneGeographique
	 *
	 */
	public Tournee getTournee() {
		return tournee;
	}
	
	/**
	 * Ajoute un Objet Noeud a l'Attribut noeuds (qui represente la liste des noeuds du plan a charger)
	 * @param noeud Objet Noeud à rajouer a la liste 
	 *
	 */
	public void ajouterNoeud(Noeud noeud) {
		this.noeuds.add(noeud);
	}
	
	/**
	 * Ajoute un Objet Troncon à l'Attribut troncons (qui représente la liste des troncons du plan à charger)
	 * @param troncon Objet Troncon à rajouer à la liste 
	 *
	 */
	public void ajouterTroncon(Troncon Troncon) {
		this.troncons.add(Troncon);
	}
	
	/**
	 * Recherche et retourne un Objet Noeud dans la liste des noeuds du plan a charger selon son Identifiant (Attribut Id de l'Objet Noeud)
	 * @param id int la valeur de l'Identifiant de l'Objet Noeud à rechercher
	 * @return Noeud dont l'Identifiant est egal au parametre id passee en parametre 
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
	 * lit le fichier XML dans le chemin est passe en parametre, utilise la librairie JDOM pour parser le fichier XML apres verification par le validateur de fichier XML de la methode XMLValidateur.validerXML(fichierXML, fichierXSD)
	 * remplit les listes noeuds et troncons
	 * @param nomFic String chemin relatif du fichier xml du plan a charger
	 * @return int la valeur de l'erreur retournee par la methode lirePlanXML de la classe ZoneGeographique du modele
	 * @return 0 si le chargement du fichier XML se deroule bien
	 * @return 1 s'il y une erreur de conversion d'un des attributs d'un troncon (vitesse<0, longueur<0 ou id<0)
	 * @return -1 si le fichier n'est pas valide
	 * @return 2 si une autre erreur lors de chargement du fichier XML se produit (pas de droit etc)
	 *
	 */
	public int lirePlanXML(String nomFic) {
		int erreur = 0;
		try {
			if (XMLValidateur.validerXML(nomFic, "res\\plan.xsd")) {		
				SAXBuilder sxb = new SAXBuilder();
				Document document = null;
				Element racine;
				
				document = sxb.build(new File(nomFic));
				
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
				    	   erreur = 1;
				       }
			    	   Troncon troncon = new Troncon(tronconCourant.getAttributeValue("nomRue"), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.'))/Double.parseDouble(tronconCourant.getAttributeValue("vitesse").replace(',', '.')), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.')));
			    	   troncon.setCible(this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))));
			    	   troncon.setSource(this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))));
			    	   this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))).ajouterTronconSortant(troncon);
			    	   this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))).ajouterTronconEntrant(troncon);
			    	   ajouterTroncon(troncon);
				    }	
			    }
			    this.Xmax = findXMax();
				this.Ymax = findYMax();
				this.Xmin = findXMin();
				this.Ymin = findYMin();
			} else {
				erreur = -1;
			}
		} catch (NullPointerException | NumberFormatException | SAXException | IOException| ParserConfigurationException | JDOMException e) {
			System.out.println("bpu");
			if (erreur == 1) {
				erreur = 1;
			} else {
				erreur = 2;
			}
			
		}
		System.out.println("erreur :" + erreur);
    	return erreur;
	}
	
	/**
	 * Méthode privée appelée dans le constructeur
	 * recherche la plus grande absisse des noeuds du plan à charger
	 * @return int la valeur de la plus grande absisse des noeuds à charger 
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
	 * @return int la valeur de la plus grande ordonnée des noeuds a charger 
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
	 * recherche la plus petite ordonnee des noeuds du plan a charger
	 * @return int la valeur de la plus petite ordonnée des noeuds a charger 
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
	 * Methode qui verifie si le noeud dont l'Id est passe en parametre a ete visitee dans la tournee ou pas
	 * @param id int Id du noeud à verifier
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
	
	public int calculerItineraire() {
		return 0;
	}
	

	public void supprimerLivraison(Livraison livraison) {	
	}
	
	public void ajouterLivraison(Livraison livraison, Livraison liv) {	
	}
	
}