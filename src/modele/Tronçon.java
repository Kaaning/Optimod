package modele;

public class Tron�on {
	
	private String nomRue;
	private float vitesse;
	private float longueur;
	
	public Noeud cible;
	public Noeud source;
	
	public Tron�on(String nomRue, float vitesse, float longueur){
		this.nomRue=nomRue;
		this.vitesse=vitesse;
		this.longueur=longueur;
	}
	
	public Tron�on(Noeud source, Noeud cible){
		this.source=source;
		this.cible=cible;
		nomRue="";
		vitesse=0;
		longueur=0;
	}

}
