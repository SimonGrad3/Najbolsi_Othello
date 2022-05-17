package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Stanje;
import splosno.Poteza;

public class OdigrajRandomIgro {

	public static void main(String[] args) {
		Igra igra = new Igra();
		System.out.print(odigraj(igra));

	}
	
	public static Poteza izberiRandomPotezo(Igra igra) {
		return RandomIgralec.izberiPotezo(igra);
	}
	
	public static Igralec odigraj(Igra igra) {
		Poteza p = izberiRandomPotezo(igra);
		if (p == null) igra.zamenjajIgralca(); //igralec na vrsti nima mozne poteze
		if (Stanje.V_TEKU == igra.stanjeIgre()) odigraj(igra);
		else return igra.vodilniIgralec();
		return igra.vodilniIgralec();
	}

}
