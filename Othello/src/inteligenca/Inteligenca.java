package inteligenca;

import logika.Igra;
import splosno.Poteza;

public class Inteligenca extends splosno.KdoIgra {

	public Inteligenca() {
		super("as");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		//radnom igralec, ki igra prvo potezo na seznamu moznih
		if (igra.moznePoteze(igra.naPotezi, igra.plosca).length == 0) return null;
		Poteza poteza = igra.moznePoteze(igra.naPotezi, igra.plosca)[0];
		return poteza;
	}

}
