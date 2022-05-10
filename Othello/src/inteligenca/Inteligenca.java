package inteligenca;

import logika.Igra;
import splosno.Poteza;

public class Inteligenca extends splosno.KdoIgra {

	public Inteligenca() {
		super("as");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		//radnom igralec, ki igra prvo potezo na seznamu moznih
		Poteza poteza = igra.moznePoteze()[0];
		return poteza;
	}

}
