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
 * Classe de Gestion des Zones G�ographiques
 * @author MohamedRiadh
 *
 */

public class ZoneGeographique {
	private ArrayList<Noeud> noeuds = new ArrayList<Noeud>(); 
	private ArrayList<Tron�on> tron�ons = new ArrayList<Tron�on>();
	private int Xmin;
	private int Xmax;
	private int Ymin;
	private int Ymax;
	
	/**
	 * Constructeur avec param�tres de l'Objet ZoneGeographique
	 * @param nomFic String chemin relatif du fichier xml du plan � charger
	 * @throws remonte des Exceptions reli�es � l'ouverture d'un fichier et l'utlisiation de JDOM pour parser le fichier XML
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
	 * @return List<Noeud> Liste des Objets Noeuds contenus dans le plan � charger
	 *
	 */
	public ArrayList<Noeud> getNoeuds() {
		return this.noeuds;
	}
	
	/**
	 * Accesseur de l'Attribut tron�ons
	 * @return List<Tron�on> Liste des Objets Tron�on contenus dans le plan � charger
	 *
	 */
	public ArrayList<Tron�on> getTron�ons() {
		return this.tron�ons;
	}
	
	/**
	 * Accesseur de l'Attribut Xmin
	 * @return int la valeur de l'absisse du noeud la plus petite du plan � charger
	 *
	 */
	public int getXMin() {
		return Xmin;
	}
	
	/**
	 * Accesseur de l'Attribut Xmax
	 * @return int la valeur de l'absisse du noeud la plus grande du plan � charger
	 *
	 */
	public int getXMax() {
		return Xmax;
	}
	
	/**
	 * Accesseur de l'Attribut Ymin
	 * @return int la valeur de l'ordonn�e du noeud la plus petite du plan � charger
	 *
	 */
	public int getYMin() {
		return Ymin;
	}
	
	/**
	 * Accesseur de l'Attribut Ymax
	 * @return int la valeur de l'ordonn�e du noeud la plus grande du plan � charger
	 *
	 */
	public int getYMax() {
		return Ymax;
	}
	
	/**
	 * Ajoute un Objet Noeud � l'Attribut noeuds (qui repr�sente la liste des noeuds du plan � charger)
	 * @param noeud Objet Noeud � rajouer � la liste 
	 *
	 */
	public void ajouterNoeud(Noeud noeud) {
		this.noeuds.add(noeud);
	}
	
	/**
	 * Ajoute un Objet Tron�on � l'Attribut tron�ons (qui repr�sente la liste des tron�ons du plan � charger)
	 * @param tron�on Objet Tron�on � rajouer � la liste 
	 *
	 */
	public void ajouterTron�on(Tron�on Tron�on) {
		this.tron�ons.add(Tron�on);
	}
	
	/**
	 * Recherche et retourne un Objet Noeud dans la liste des noeuds du plan � charger selon son Identifiant (Attribut Id de l'Objet Noeud)
	 * @param id int la valeur de l'Identifiant de l'Objet Noeud � rechercher
	 * @return Noeud dont l'Identifiant est �gal au param�tre id pass� en param�tre 
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
	 * M�thode priv�e appel�e dans le constructeur
	 * lit le fichier XML dans le chemin est pass� en param�tre, utilise la librairie JDOM pour parser le fichier XML
	 * remplit les listes noeuds et tron�ons
	 * @param nomFic String chemin relatif du fichier xml du plan � charger 
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
	    	List<Element> listeTron�ons = new ArrayList<Element>();
	    	listeTron�ons = listeNoeuds.get(cpt).getChildren("LeTronconSortant");
	    	Iterator<Element> it = listeTron�ons.iterator();
		    while(it.hasNext())
			{
		       Element tron�onCourant = (Element)it.next();
	    	   Tron�on tron�on = new Tron�on(tron�onCourant.getAttributeValue("nomRue"), Double.parseDouble(tron�onCourant.getAttributeValue("vitesse").replace(',', '.')) ,Double.parseDouble(tron�onCourant.getAttributeValue("longueur").replace(',', '.')));
	    	   tron�on.setCible(this.findNoeudById(Integer.parseInt(tron�onCourant.getAttributeValue("idNoeudDestination"))));
	    	   tron�on.setSource(this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))));
	    	   this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))).ajouterTron�onSortant(tron�on);
	    	   this.findNoeudById(Integer.parseInt(tron�onCourant.getAttributeValue("idNoeudDestination"))).ajouterTron�onEntrants(tron�on);
	    	   ajouterTron�on(tron�on);
		    }	
	    }
	}
	
	/**
	 * M�thode priv�e appel�e dans le constructeur
	 * recherche la plus grande absisse des noeuds du plan � charger
	 * @return int la valeur de la plus grande absisse des noeuds � charger 
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
	 * M�thode priv�e appel�e dans le constructeur
	 * recherche la plus grande ordonn�e des noeuds du plan � charger
	 * @return int la valeur de la plus grande ordonn�e des noeuds � charger 
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
	 * M�thode priv�e appel�e dans le constructeur
	 * recherche la plus petite absisse des noeuds du plan � charger
	 * @return int la valeur de la plus petite absisse des noeuds � charger 
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
	 * M�thode priv�e appel�e dans le constructeur
	 * recherche la plus petite ordonn�e des noeuds du plan � charger
	 * @return int la valeur de la plus petite ordonn�e des noeuds � charger 
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
}
