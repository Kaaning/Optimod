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
	private Tournee tournee;
	
	private int Xmin;
	private int Xmax;
	private int Ymin;
	private int Ymax;
	
	
	
	
	/**
	 * Constructeur avec parametres de l'Objet ZoneGeographique
	 * @param nomFic String chemin relatif du fichier xml du plan a charger
	 * @throws remonte des Exceptions reliees a l'ouverture d'un fichier et l'utlisiation de JDOM pour parser le fichier XML
	 */
	public ZoneGeographique() {
	
	}
	
	public int chargerLivraison(String nomFic) {
		this.tournee = new Tournee(nomFic,this);
		return tournee.getErreur();
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
	 * @return List<Troncon> Liste des Objets Troncon contenus dans le plan a charger
	 *
	 */
	public List<Troncon> getTroncons() {
		return this.troncons;
	}
	
	/**
	 * Accesseur de l'Attribut Xmin
	 * @return int la valeur de l'absisse du noeud la plus petite du plan a charger
	 *
	 */
	public int getXMin() {
		return Xmin;
	}
	
	/**
	 * Accesseur de l'Attribut Xmax
	 * @return int la valeur de l'absisse du noeud la plus grande du plan a charger
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
	 * @return int la valeur de l'ordonnee du noeud la plus grande du plan a charger
	 *
	 */
	public int getYMax() {
		return Ymax;
	}
	
	public Tournee getTournee() {
		return tournee;
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
	 * Ajoute un Objet Troncon a l'Attribut troncons (qui represente la liste des troncons du plan a charger)
	 * @param troncon Objet Troncon a rajouer a la liste 
	 *
	 */
	public void ajouterTroncon(Troncon Troncon) {
		this.troncons.add(Troncon);
	}
	
	/**
	 * Recherche et retourne un Objet Noeud dans la liste des noeuds du plan a charger selon son Identifiant (Attribut Id de l'Objet Noeud)
	 * Modifie l'etat du Noeud trouve: il le met a 0 pour indiquer que le noeud a ete pris en compte lors du calcul de la tournee
	 * @param id int la valeur de l'Identifiant de l'Objet Noeud a rechercher
	 * @return Noeud dont l'Identifiant est egal au parametre id passe en parametre 
	 *
	 */
	public Noeud getNoeudById(int id) {
		for (Noeud n : noeuds) {
			if (n.getId()==id) {
				n.setEtat(0);
				return n;
			}
		}
		return null;
	}
	
	/**
	 * Recherche et retourne un Objet Noeud dans la liste des noeuds du plan a charger selon son Identifiant (Attribut Id de l'Objet Noeud)
	 * @param id int la valeur de l'Identifiant de l'Objet Noeud a rechercher
	 * @return Noeud dont l'Identifiant est egal au parametre id passe en parametre 
	 *
	 */
	public Noeud findNoeudById(int id) {
		for (Noeud n : noeuds) {
			if (n.getId()==id) {
				return n;
			}
		}
		return null;
	}
	
	/**
	 * Methode publique appelee dans le constructeur
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
						} else {
							Troncon troncon = new Troncon(tronconCourant.getAttributeValue("nomRue"), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.'))/Double.parseDouble(tronconCourant.getAttributeValue("vitesse").replace(',', '.')), Double.parseDouble(tronconCourant.getAttributeValue("longueur").replace(',', '.')));
							troncon.setCible(this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))));
							troncon.setSource(this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))));
							this.findNoeudById(Integer.parseInt(listeNoeuds.get(cpt).getAttributeValue("id"))).ajouterTronconSortant(troncon);
							this.findNoeudById(Integer.parseInt(tronconCourant.getAttributeValue("idNoeudDestination"))).ajouterTronconEntrant(troncon);
							ajouterTroncon(troncon);
						}
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
			System.out.println(e.getMessage());
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
	 * recherche la plus petite ordonnee des noeuds du plan a charger
	 * @return int la valeur de la plus petite ordonnee des noeuds a charger 
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
				return true;
			}
		}
		return false;
	}
	
	
	public int calculerItineraire() {
		return 0;
	}

	public int supprimerLivraison(Livraison supprime) {
		return 0;
		// TODO Auto-generated method stub
	}
	
	public int ajouterLivraison (Livraison aAjouter , Livraison precedente) {
		// mettre le bon appel et la bonne signature
		return 0;
	}
	
}
