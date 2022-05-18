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
	protected double[] vnosDrevesa; //[zmage, vsiPoskusi]
	protected HashMap<LinkedList<Poteza>, double[]> drevo = new HashMap<LinkedList<splosno.Poteza>, double[]>();
	
	protected Igralec jaz;
	protected int stPoskusov;	
	
	public MC(Igra igra, int st) {
		jaz = igra.naPotezi;
		this.stPoskusov = st;
	}
	
	
//glavno 
	
	public LinkedList<splosno.Poteza> izberiVejo(Poteza[] pot){ //vemo da so vse poteze ze bile odigane
		Igra igra = new Igra();
		for (Poteza p : pot){
			igra.odigraj(p);
		}
		Poteza[] poteze = igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
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
		return praznaPoteza;
	}
	
	
//1.selection.......................................................................
	
	public LinkedList<Poteza> izberiVejo(LinkedList<Poteza> pot){ //vemo da so vse poteze ze bile odigane
		Igra igra = new Igra();
		for (Poteza p : pot){
			igra.odigraj(p);
		}
		Poteza[] moznePoteze = igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		
		double[] value = drevo.get(pot);
		double W = value[0];
		double N = value[1];
		
		Poteza izbranaPoteza = null;
		double max = 0;
		
		for (Poteza p : moznePoteze) {
			pot.add(p);
			double vrednost = formula(pot, W, N);
			if (vrednost > max) izbranaPoteza = p;
			pot.removeLast();
		}
		
		pot.add(izbranaPoteza);
		return pot;
	}
	
	public double formula(LinkedList<Poteza> pot, double W, double N) {
		double[] value = drevo.get(pot);
		double w = value[0];
		double n = value[1];
		double C = Math.sqrt(2);
		return W/n + C * Math.sqrt( Math.log(N) / n);
	}
	
	
//2. expansion............................................................
	
	
//3.simulation.............................................................
	
	public static Igralec simulation(Igra igra) {
		return OdigrajRandomIgro.odigraj(igra);
	}
	
//4.back...................................................................
	
	public void popraviDrevo (LinkedList<Poteza> pot, Igralec zmaga){
	        int dolzina = pot.size();
	        while (dolzina > 0) {
	            double[] value = drevo.get(pot);
	            double x = value[0];
	            if(dolzina % 2 == 0 ) {
	                if(zmaga == jaz) x += 1;
	                else if (zmaga == null) {
	                    x += 0.5;   
	                }
	            }
	            else {
	                if(zmaga != jaz) x += 1;
	                else if (zmaga == null) {
	                    x += 0.5;   
	                }
	            }
	            value[0] = x;
	            value[1] += 1;
	            drevo.put(pot,value);
	           
	            int index = pot.size() - 1;
	            pot.remove(index);
	        }
	    }
}