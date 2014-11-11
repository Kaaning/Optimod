/**
 * 
 */
package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


/**
 * Classe de Gestion des Zones Géographiques
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
	
	/**
	 * Constructeur avec paramètres de l'Objet ZoneGeographique
	 * @param nomFic String chemin relatif du fichier xml du plan à charger
	 * @throws remonte des Exceptions reliées à l'ouverture d'un fichier et l'utlisiation de JDOM pour parser le fichier XML
	 */
	public ZoneGeographique(String nomFic) throws JDOMException, IOException {
		lirePlanXML(nomFic);
		this.Xmax = findXMax();
	    	this.Ymax = findYMax();
		this.Xmin = findXMin();
	    	this.Ymin = findYMin();
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
	 * @return int la valeur de l'ordonnée du noeud la plus petite du plan à charger
	 *
	 */
	public int getYMin() {
		return Ymin;
	}
	
	/**
	 * Accesseur de l'Attribut Ymax
	 * @return int la valeur de l'ordonnée du noeud la plus grande du plan à charger
	 *
	 */
	public int getYMax() {
		return Ymax;
	}
	
	/**
	 * Ajoute un Objet Noeud à l'Attribut noeuds (qui représente la liste des noeuds du plan à charger)
	 * @param noeud Objet Noeud à rajouer à la liste 
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
	 * Recherche et retourne un Objet Noeud dans la liste des noeuds du plan à charger selon son Identifiant (Attribut Id de l'Objet Noeud)
	 * @param id int la valeur de l'Identifiant de l'Objet Noeud à rechercher
	 * @return Noeud dont l'Identifiant est égal au paramètre id passé en paramètre 
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
	 * Méthode privée appelée dans le constructeur
	 * lit le fichier XML dans le chemin est passé en paramètre, utilise la librairie JDOM pour parser le fichier XML
	 * remplit les listes noeuds et troncons
	 * @param nomFic String chemin relatif du fichier xml du plan à charger 
	 *
	 */
	private void lirePlanXML(String nomFic){
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		Element racine;
		
		try {
			document = sxb.build(new File(nomFic));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	    	   Troncon troncon = new Troncon(tronconCourant.getAttributeValue("nomRue"), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.'))/Double.parseDouble(tronconCourant.getAttributeValue("vitesse").replace(',', '.')));
	    	   troncon.setCible(this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))));
	    	   troncon.setSource(this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))));
	    	   this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))).ajouterTronconSortant(troncon);
	    	   this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))).ajouterTronconEntrants(troncon);
	    	   ajouterTroncon(troncon);
		    }	
	    }
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
	 * Méthode privée appelée dans le constructeur
	 * recherche la plus grande ordonnée des noeuds du plan à charger
	 * @return int la valeur de la plus grande ordonnée des noeuds à charger 
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
	 * Méthode privée appelée dans le constructeur
	 * recherche la plus petite absisse des noeuds du plan à charger
	 * @return int la valeur de la plus petite absisse des noeuds à charger 
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
	 * Méthode privée appelée dans le constructeur
	 * recherche la plus petite ordonnée des noeuds du plan à charger
	 * @return int la valeur de la plus petite ordonnée des noeuds à charger 
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
	 * Méthode qui vérifie si le noeud dont l'Id est passé en paramètre a été visité dans la tournée ou pas
	 * @param id int Id du noeud à vérifier
	 * @return boolean true si le noeud passé en paramètre a été visité false sinon 
	 *
	 */
	public boolean verifierNoeud(int id) {
		for (Noeud n : noeuds) {
			if (n.getId()==id) {
				n.setVisite(true);
				return true;
			}
		}
		return false;
	}
}
