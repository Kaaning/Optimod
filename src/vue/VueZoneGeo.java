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

import controleur.Command;
import modele.Noeud;
import modele.Troncon;
import modele.ZoneGeographique;

public class VueZoneGeo extends JPanel{
	
	private Command cmd;
	private ZoneGeographique zoneGeo;
	private ArrayList<VueNoeud> vueNoeuds;
	private ArrayList<VueTroncon> vueTroncons;
	private Color couleurArrierePlan = Color.gray;
	private int largeur;
	private int hauteur;
	private int rayon=12;
	private double echelle;
	private int rayonAjuste;
	private int[] origine = {0,0};
	private int[] clic = {0,0};
	private int[] deplacement = {0,0};
	
	public VueZoneGeo (int x, int y, int largeur, int hauteur, double echelle, ZoneGeographique zoneGeo, Command command) {
    	// Creation d'un panneau pour dessiner les boules
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.zoneGeo = zoneGeo;
        this.echelle=echelle;
        rayonAjuste = (int)(rayon/2*echelle);
        vueNoeuds = new ArrayList<VueNoeud>();
        vueTroncons = new ArrayList<VueTroncon>();
        
        setSize(largeur,hauteur);
		setLocation(x,y);
		setLayout(null);
		creerVueTroncons();
		creerVueNoeuds();
		
		/* ----- Gestion du zoom via la molette ----- */
		addMouseWheelListener(new MouseAdapter() {
        	public void mouseWheelMoved(MouseWheelEvent e) {
        		double sens = e.getPreciseWheelRotation();
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
//        		int[] coord = {(int)((e.getX()-clic[0])/echelle), (int)((e.getY()-clic[1])/echelle)};
        		int[] coord = {e.getX()-clic[0], e.getY()-clic[1]};
        		deplacement = coord;
        		deplacerPlan();
        	}
        });
		/*----- Test -----*/
//		add(vt);
//		vt.setLocation(vt.troncon.source.getX(),vt.troncon.source.getY());
		
     }
	
	public void creerVueTroncons(){
		List<Troncon> troncons = zoneGeo.getTroncons();
		for(Troncon troncon : troncons){
			VueTroncon vn = new VueTroncon(troncon, echelle);
			vueTroncons.add(vn);
		}
	}
	
	public void creerVueNoeuds(){
		List<Noeud> noeuds = zoneGeo.getNoeuds();
		for(Noeud noeud : noeuds){
			VueNoeud vn = new VueNoeud(noeud, echelle, cmd);
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
		this.echelle=echelle;
        rayonAjuste = (int)(rayon/2*echelle);
        for(VueNoeud vn : vueNoeuds){
        	vn.changerEchelle(echelle);
        	vn.setLocation(origine[0]+deplacement[0]+vn.getXVue(), origine[1]+deplacement[1]+vn.getYVue());
        	//        	vn.setLocation(origine[0]+vn.getXVue()-rayonAjuste+deplacement[0], origine[1]+vn.getYVue()-rayonAjuste+deplacement[1]);
        }
        for(VueTroncon vt : vueTroncons){
        	vt.changerEchelle(echelle);
        }
	}
	
	public void deplacerPlan(){
		
		for(VueNoeud vn : vueNoeuds){
//			vn.setDeplacement(deplacement);
			vn.setLocation(origine[0]+deplacement[0]+vn.getXVue(), origine[1]+deplacement[1]+vn.getYVue());
			//        	vn.setLocation(origine[0]+vn.getXVue()-rayonAjuste+deplacement[0], origine[1]+vn.getYVue()-rayonAjuste+deplacement[1]);
        }
	}
	
	public double getEchelle(){
		return echelle;
	}
	
	public void changerCouleur(int n){
		for(VueNoeud vn : vueNoeuds){
			//vn.noeud.getId()=
			vn.changerCouleur(n);
		}
	
	}


}
