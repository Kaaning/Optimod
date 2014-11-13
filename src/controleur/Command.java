package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import modele.*;

import org.jdom2.JDOMException;

import vue.Accueil;

public interface Command {
	
	public int execute();
	
	public int unexecute();
}


