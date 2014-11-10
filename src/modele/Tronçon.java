package modele;

public class Tronçon {
	
	private String nomRue;
	private float vitesse;
	private float longueur;
	
	public Noeud cible;
	public Noeud source;
	
	public Tronçon(String nomRue, float vitesse, float longueur){
		this.nomRue=nomRue;
		this.vitesse=vitesse;
		this.longueur=longueur;
	}
	
	public Tronçon(Noeud source, Noeud cible){
		this.source=source;
		this.cible=cible;
		nomRue="";
		vitesse=0;
		longueur=0;
	}

}
