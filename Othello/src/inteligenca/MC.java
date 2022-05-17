package inteligenca;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Stanje;
import splosno.Poteza;

public class MC {

	protected LinkedList<Poteza> indeksDrevesa; //pot je seznam potez
	protected double[] vnosDrevesa; //[zmage, vsiPoskusi
	protected HashMap<LinkedList<Poteza>, double[]> drevo = new HashMap<LinkedList<splosno.Poteza>, double[]>();
	
	protected int steviloPoskusov = 10;	
	
	public List<splosno.Poteza> izbireriVejo(Igra igra){
		Poteza[] poteze = igra.moznePoteze(igra.naPotezi, igra.plosca);
		//List<splosno.Poteza>[ moznePoti  = new LinkedList<splosno.Poteza>[poteze.length]; 
		if (najdiPraznoPotezo != null) {
			pomožnaFunkcija 
		}
		else {
		
		//fukcija formula izbere potezo
		//izberi vejo...
		}
		return indeksDrevesa;
	}
	
	
	public Poteza najdiPraznoPotezo(Poteza[] poteze) {
		Poteza praznaPoteza = null;
		for (Poteza p : poteze) {
			if (!drevo.containsKey(p)) {
				praznaPoteza = p ;
				break;
			}
		}
	}
	
	//RANDOM IGRA...............................................................................
		
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
