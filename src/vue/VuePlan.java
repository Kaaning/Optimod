package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import controleur.Controleur;
import modele.Noeud;
import modele.Troncon;
import modele.ZoneGeographique;

/**
 * @author H4303 - 2014
 *
 */
/**
 * @author William
 *
 */
public class VuePlan extends JPanel{
	
	private Controleur ctrl;
	private ZoneGeographique zoneGeo;
	private ArrayList<VueNoeud> vueNoeuds;
	private ArrayList<VueTroncon> vueTroncons;
	private Color couleurArrierePlan = new Color(193,193,193);
	private int largeur;
	private int hauteur;
	private int rayon=12;
	private double echelle;
	private int rayonAjuste;
	private int[] origine = {0,0};
	private int[] clic = {0,0};
	private int[] deplacement = {0,0};
	
	/**Constructeur de l'objet VuePlan
	 * @param zoneGeo une zone geographique associe au plan
	 * @param ctrl le controleur en lien avec le modele
	 */
	public VuePlan (ZoneGeographique zoneGeo, Controleur ctrl) {
    	// Creation d'un panneau pour dessiner les boules
        this.largeur = 500;
        this.hauteur = 500;
        this.zoneGeo = zoneGeo;
        this.echelle = 500.0/800.0;
        rayonAjuste = (int)(rayon/2*echelle);
        vueNoeuds = new ArrayList<VueNoeud>();
        vueTroncons = new ArrayList<VueTroncon>();
        this.ctrl=ctrl;
        
        setSize(largeur,hauteur);
//		setLocation(0,0);
		setLayout(null);
		creerVueTroncons();
		creerVueNoeuds();
		
		/* ----- Gestion du zoom via la molette ----- */
		addMouseWheelListener(new MouseAdapter() {
        	public void mouseWheelMoved(MouseWheelEvent e) {
        		double sens = e.getWheelRotation();
        		double echelle = getEchelle();
        		if(sens==1.0 && echelle>0.630){
                	echelle-=0.1;
                }
                else if(sens==-1.0){
                	echelle+=0.1;
                }
                else if(echelle<0.630){
        			origine[0]=0;
        			origine[1]=0;
        			deplacement[0]=0;
        			deplacement[1]=0;
        		}
                System.out.println(echelle);
                changerEchelle(echelle);
                repaint();
        	  }
		});
		/* ----- Gestion du déplacement via clic&glisse ----- */
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int[] coord = {e.getX(), e.getY()};
				clic = coord;
				System.out.println("Cliqué en "+clic[0]+","+clic[1]);
			}
			public void mouseReleased(MouseEvent e){
				int[] coord = {origine[0]+deplacement[0], origine[1]+deplacement[1]};
				deplacement[0]=0;
				deplacement[1]=0;
				origine = coord;
				System.out.println("Origine en "+origine[0]+","+origine[1]);
			}
		});
		addMouseMotionListener(new MouseAdapter(){
        	public void mouseDragged(MouseEvent e){
        		double echelle = getEchelle();
        		int[] coord = {e.getX()-clic[0], e.getY()-clic[1]};
        		if(origine[0]+coord[0]<0 && origine[0]+coord[0]>500-800*echelle){
	        		deplacement[0] = coord[0];
        		deplacerPlan();
        		}
        		if(origine[1]+coord[1]<0 && origine[1]+coord[1]>500-800*echelle){
        			deplacement[1] = coord[1];
        			deplacerPlan();
        		}
        	}
        });
/*-------------------------- Test ----------------------------*/
		//changerCouleur(30);
     }
	
	/**Creer une VueTroncon pour chaque Troncon
	 * 
	 */
	public void creerVueTroncons(){
		List<Troncon> troncons = zoneGeo.getTroncons();
		for(Troncon troncon : troncons){
			VueTroncon vn = new VueTroncon(troncon, echelle, ctrl);
			vueTroncons.add(vn);
		}
	}
	
	/**Creer une VueNoeud pour chaque Noeud
	 * 
	 */
	public void creerVueNoeuds(){
		List<Noeud> noeuds = zoneGeo.getNoeuds();
		for(Noeud noeud : noeuds){
			VueNoeud vn = new VueNoeud(noeud, echelle, ctrl);
			vueNoeuds.add(vn);
			add(vn);
			vn.setLocation(origine[0]+deplacement[0]+vn.getXVue(), origine[1]+deplacement[1]+vn.getYVue());
//			vn.setLocation(origine[0]+vn.getXVue()-rayonAjuste+deplacement[0], origine[1]+vn.getYVue()-rayonAjuste+deplacement[1]);
//			//System.out.println("Noeud situé en "+(vn.getXVue())+","+(vn.getYVue()));
		}
		repaint();
	}
	
	@Override
    public void paint(Graphics g) {
        // methode appelee a chaque fois que le dessin doit etre redessine
        super.paint(g);
        setBackground(couleurArrierePlan);
        // Dessin des troncons
        for (VueTroncon vt : vueTroncons){
        	vt.dessiner(g, origine, deplacement);
        }
        // Dessin des noeuds
        super.paintChildren(g);
    }
	
	public void changerEchelle(double echelle){
		double ancienneLongueur = 800*this.echelle;
		this.echelle=echelle;
        rayonAjuste = (int)(rayon/2*echelle);
        if(origine[0]<0){
        	System.out.println("Old : "+ancienneLongueur+" New : "+800*echelle);
        	origine[0]+=(int)(ancienneLongueur-800*echelle);
        	if(origine[0]>0){
        		origine[0]=0;
        	}
        }
        if(origine[1]<0){
        	origine[1]+=(int)(ancienneLongueur-800*echelle);
        	if(origine[1]>0){
        		origine[1]=0;
        	}
        }
        for(VueNoeud vn : vueNoeuds){
        	vn.changerEchelle(echelle);
        	vn.setLocation(origine[0]+deplacement[0]+vn.getXVue(), origine[1]+deplacement[1]+vn.getYVue());
        }
        for(VueTroncon vt : vueTroncons){
        	vt.changerEchelle(echelle);
        }
	}
	
	/**Translate le plan
	 * 
	 */
	public void deplacerPlan(){
		
		for(VueNoeud vn : vueNoeuds){
//			vn.setDeplacement(deplacement);
			vn.setLocation(origine[0]+deplacement[0]+vn.getXVue(), origine[1]+deplacement[1]+vn.getYVue());
			//        	vn.setLocation(origine[0]+vn.getXVue()-rayonAjuste+deplacement[0], origine[1]+vn.getYVue()-rayonAjuste+deplacement[1]);
        }
	}
	
	/**Renvoie l'echelle
	 * @return double
	 */
	public double getEchelle(){
		return echelle;
	}
	
	/**Rafraichit les couleurs des noeuds et troncons
	 * @param n : numero d'identification de l'entrepot
	 */
	public void changerCouleur(int n){
		for(VueNoeud vn : vueNoeuds){
			vn.changerCouleur(n);
		}
		for(VueTroncon vt : vueTroncons){
			vt.changerCouleur();
		}
	
	}


}
