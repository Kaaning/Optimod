package modele;

import java.sql.Time;
import java.util.ArrayList;

public class PlageHoraire {
	
	private Time heureDebut;
	private Time heureFin;
	private ArrayList<Livraison> livraisons;
	
	public PlageHoraire(Time heureDebut, Time heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	

}
