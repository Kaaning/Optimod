package controleur;

import java.util.ArrayList;

public class Invoker {
	
	private ArrayList<Command> commands;
	private int currentCommandIndex;
	
	
	public Invoker () {
		this.currentCommandIndex = -1;
		this.commands = new ArrayList<Command>();
	}
	
	/**
	 * Defait la dernière commande faite .
	 * @return 0 si l'oppration réussi ou 1 s'il n'y a pas de command à desexecuter
	 */
	public int undo(){
		if (currentCommandIndex != -1) {
			this.commands.get(currentCommandIndex).unexecute();
			this.currentCommandIndex --;
			return 0;
		}
		else {
			return 1;
		}
	}
	
	/**
	 * Refait la dernière commande defaite .
	 * @return 0 si l'oppration réussi ou 1 s'il n'y a pas de command à desexecuter
	 */
	public int redo(){
		if ( this.currentCommandIndex < this.commands.size() -1 ) {
			this.currentCommandIndex ++;
			this.commands.get(currentCommandIndex).execute();
			return 0;
		}
		else {
			return 1;
		}
	}
	
	
	public int addCommand(Command command) {
		if (this.currentCommandIndex != this.commands.size() -1 ) {
			this.commands = new ArrayList<Command>(this.commands.subList(0, this.currentCommandIndex +1));
		}
		this.commands.add(command);
		currentCommandIndex ++;
		
		return 0;
	}
}
