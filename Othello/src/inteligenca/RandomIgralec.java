package inteligenca;

import java.util.Random;

import logika.Igra;
import splosno.Poteza;

public class RandomIgralec {

	public static Poteza izberiPotezo(Igra igra) {
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		if (moznePoteze.length < 1) return null;
		
		Random random = new Random();
		int mesto = random.nextInt(moznePoteze.length);
		Poteza poteza = moznePoteze[mesto];
		return poteza;
	}
}
