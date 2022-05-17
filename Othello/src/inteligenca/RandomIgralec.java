package inteligenca;

import java.util.Random;

import logika.Igra;
import splosno.Poteza;

public class RandomIgralec {

	public static Poteza izberiPotezo(Igra igra) {
		Poteza[] moznePoteze = igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		if (moznePoteze.length == 0) return null;
		
		Random random = new Random();
		int mesto = random.nextInt(moznePoteze.length);
		Poteza poteza = moznePoteze[mesto];
		return poteza;
	}
}
