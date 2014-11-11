/**
 * 
 */
package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

<<<<<<< HEAD


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

=======
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

>>>>>>> master
/**
 * Classe de Gestion des Zones Géographiques
 * @author MohamedRiadh
 *
 */

public class ZoneGeographique {
<<<<<<< HEAD
	private ArrayList<Noeud> noeuds = new ArrayList<Noeud>(); 
	private ArrayList<Tron�on> tron�ons = new ArrayList<Tron�on>();
=======
	private List<Noeud> noeuds = new ArrayList<Noeud>(); 
<<<<<<< HEAD
	private List<Tronçon> Tronçons = new ArrayList<Tronçon>();
=======
	private List<Tron�on> Tron�ons = new ArrayList<Tron�on>();
>>>>>>> master
>>>>>>> William-TestAffichage
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
<<<<<<< HEAD
	public ArrayList<Noeud> getNoeuds() {
=======
	public List<Noeud> getNoeuds() {
>>>>>>> master
		return this.noeuds;
	}
	
	/**
	 * Accesseur de l'Attribut tronçons
	 * @return List<Tronçon> Liste des Objets Tronçon contenus dans le plan à charger
	 *
	 */
<<<<<<< HEAD
	public List<Tronçon> getTronçons() {
		return this.Tronçons;
=======
<<<<<<< HEAD
	public ArrayList<Tron�on> getTron�ons() {
		return this.tron�ons;
=======
	public List<Tron�on> getTron�ons() {
		return this.Tron�ons;
>>>>>>> master
>>>>>>> William-TestAffichage
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
	 * Ajoute un Objet Tronçon à l'Attribut tronçons (qui représente la liste des tronçons du plan à charger)
	 * @param tronçon Objet Tronçon à rajouer à la liste 
	 *
	 */
<<<<<<< HEAD
	public void ajouterTronçon(Tronçon Tronçon) {
		this.Tronçons.add(Tronçon);
=======
	public void ajouterTron�on(Tron�on Tron�on) {
<<<<<<< HEAD
		this.tron�ons.add(Tron�on);
=======
		this.Tron�ons.add(Tron�on);
>>>>>>> master
>>>>>>> William-TestAffichage
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
	 * remplit les listes noeuds et tronçons
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
	    	List<Element> listeTronçons = new ArrayList<Element>();
	    	listeTronçons = listeNoeuds.get(cpt).getChildren("LeTronconSortant");
	    	Iterator<Element> it = listeTronçons.iterator();
		    while(it.hasNext())
			{
		       Element tronçonCourant = (Element)it.next();
	    	   Tronçon tronçon = new Tronçon(tronçonCourant.getAttributeValue("nomRue"), Double.parseDouble(tronçonCourant.getAttributeValue("vitesse").replace(',', '.')) ,Double.parseDouble(tronçonCourant.getAttributeValue("longueur").replace(',', '.')));
	    	   tronçon.setCible(this.findNoeudById(Integer.parseInt(tronçonCourant.getAttributeValue("idNoeudDestination"))));
	    	   tronçon.setSource(this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))));
	    	   this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))).ajouterTronçonSortant(tronçon);
	    	   this.findNoeudById(Integer.parseInt(tronçonCourant.getAttributeValue("idNoeudDestination"))).ajouterTronçonEntrants(tronçon);
	    	   ajouterTronçon(tronçon);
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
		for (int i=1; i<this.noeuds.size(); i++) {
			if (this.noeuds.get(i).getX() > temp) {
				temp = this.noeuds.get(i).getX();
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
		for (int i=1; i<this.noeuds.size(); i++) {
			if (this.noeuds.get(i).getY() > temp) {
				temp = this.noeuds.get(i).getY();
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
		for (int i=1; i<this.noeuds.size(); i++) {
			if (this.noeuds.get(i).getX() < temp) {
				temp = this.noeuds.get(i).getX();
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
		for (int i=1; i<this.noeuds.size(); i++) {
			if (this.noeuds.get(i).getY() < temp) {
				temp = this.noeuds.get(i).getY();
			}
		}
		return temp;		
	}
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> William-TestAffichage
	
	public boolean verifierNoeud(int id){
		for(Noeud n : noeuds){
			if(n.getId()==id){
				n.setVisite(true);
				return true;
			}
		}
		return false;
	}
<<<<<<< HEAD
=======
	
=======
>>>>>>> master
>>>>>>> William-TestAffichage
}
