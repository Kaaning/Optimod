/**
 * 
 */
package modele;

import java.io.IOException;

import org.jdom2.JDOMException;


/**
 * @author MohamedRiadh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "plan10x10.xml";
		try {
			ZoneGeographique zone = new ZoneGeographique(test);
			
			//Affichage de la liste des noeuds (V�rification)
		    for (int j=0; j<zone.getNoeuds().size(); j++) {
		    	System.out.println("Noeud " + j + ":");
		    	System.out.println(zone.getNoeuds().get(j).getId());
		    	System.out.println(zone.getNoeuds().get(j).getX());
		    	System.out.println(zone.getNoeuds().get(j).getY());
		    	for (int i=0; i<zone.getNoeuds().get(j).getTron�onsEntrants().size();i++){
		    		System.out.println("Tron�on Entrants " + i + ":");
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsEntrants().get(i).getNomRue());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsEntrants().get(i).getLongueur());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsEntrants().get(i).getVitesse());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsEntrants().get(i).getCible().getId());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsEntrants().get(i).getSource().getId());
		    	}
		    	
		    	for (int i=0; i<zone.getNoeuds().get(j).getTron�onsSortants().size();i++){
		    		System.out.println("Tron�on Sortant " + i + ":");
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsSortants().get(i).getNomRue());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsSortants().get(i).getLongueur());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsSortants().get(i).getVitesse());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsSortants().get(i).getCible().getId());
		    		System.out.println(zone.getNoeuds().get(j).getTron�onsSortants().get(i).getSource().getId());
		    	} 	
		    }
		    
		    System.out.println("XMax = " + zone.getXMax());
		    System.out.println("YMax = " + zone.getYMax());
		    System.out.println("XMin = " + zone.getXMin());
		    System.out.println("YMin = " + zone.getYMin());
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
