package controleur;

public class ConcreteCommandChargerLivraisons implements Command {
	
	private String path;
	
	
	
	
	public ConcreteCommandChargerLivraisons(String path) {
		this.path = path;
	}

	@Override
	public int execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unexecute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
