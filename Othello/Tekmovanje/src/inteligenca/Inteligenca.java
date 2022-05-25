package inteligenca;

import java.util.Random;

import logika.Igra;
import splosno.Poteza;

public class Inteligenca extends splosno.KdoIgra {
	
	

	public Inteligenca() {
		super("as");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		try {
			MC mc = new MC(igra, 100);
			return mc.izberiPotezo();
			}
		catch(Exception e) {
			  return RandomIgralec.izberiPotezo(igra);
		
		}
	}

}
