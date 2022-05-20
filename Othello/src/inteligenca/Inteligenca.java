package inteligenca;

import java.util.Random;

import logika.Igra;
import splosno.Poteza;

public class Inteligenca extends splosno.KdoIgra {
	
	

	public Inteligenca() {
		super("as");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		MC mc = new MC(igra, 5);
		return mc.izberiPotezo();
	}

}
