package inteligenca;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import logika.Igra;
import logika.Polje;
import splosno.Poteza;

public class MC {

	protected List<splosno.Poteza> indeksDrevesa; //pot je seznam potez
	protected double[] vnosDrevesa; //[zmage, vsiPoskusi
	protected HashMap<List<Poteza>, double[]> drevo = new HashMap<List<splosno.Poteza>, double[]>();
	
	protected int steviloPoskusov = 10;	
	
	public List<splosno.Poteza> izbireriVejo(Igra igra){
		Poteza[] poteze = igra.moznePoteze(igra.naPotezi, igra.plosca);
		List<splosno.Poteza>[] moznePoti  = new LinkedList<splosno.Poteza>[poteze.length]; 
		if (najdiPraznoPotezo != null) {
			pomožnaFunkcija 
		}
		else {
			
			
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
	
	
	
	
}
