package modele;

public class Noeud {
	
	private int id;
	private int x;
	private int y;
	
	public Noeud(int id, int x, int y){
		this.id=id;
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
