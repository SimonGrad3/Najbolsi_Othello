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
	
	
//glavno............................................................................... 
	
	public Poteza izberiPotezo(Igra igra) {
		for (int i = 0; i < stPoskusov; ++i) {
			odigraj();
		}
		Poteza izbranaPoteza = null;
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		double max = 0;
		
		for (Poteza p : moznePoteze) {
			LinkedList<Poteza> pot = new LinkedList<Poteza>();
			pot.add(p);
			double[] value = drevo.get(pot);
			double n = value[1];
			if (n > max) izbranaPoteza = p;
		}
		return izbranaPoteza;
	}
	
	public void odigraj() {
		LinkedList<Poteza> pot = new LinkedList<Poteza>();
		LinkedList<Poteza> novaPot = izberiVejo(pot);
		odigraj(novaPot);
	}
	
	
	public void odigraj(LinkedList<Poteza> pot){
		if (obstajaPraznaPot(pot)) {
			LinkedList<Poteza> praznaPot = najdiPraznoPot(pot);
			pomoznaFunkcija(praznaPot);
		}
		else {
			LinkedList<Poteza> novaPot = izberiVejo(pot);
			odigraj(novaPot);
		}
	}
	
	public void pomoznaFunkcija(LinkedList<Poteza> pot) {
		//expansion
		//simulation
		//back
	}
	
	
	
//pomozne funkcije.............................................................
	
	public Igra odigrajPoPoti(LinkedList<Poteza> pot) {
		Igra igra = new Igra();
		for (Poteza p : pot){
			igra.odigraj(p);
		}
		return igra;
	}
	
	public LinkedList<Poteza> najdiPraznoPot(LinkedList<Poteza> pot) {
		Igra igra = odigrajPoPoti(pot);
		LinkedList<Poteza> praznaPot = null;
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		
		for (Poteza p : moznePoteze) {
			pot.add(p);
			if (!drevo.containsKey(pot)) {
				praznaPot = pot;
				break;
			}
			pot.removeLast();
		}
		return praznaPot;
	}
	
	public boolean obstajaPraznaPot(LinkedList<Poteza> pot) {
		if (najdiPraznoPot(pot) != null) return true;
		else return false;
	}
	
	
//1.selection.......................................................................
	
	public LinkedList<Poteza> izberiVejo(LinkedList<Poteza> pot){ 
		//vemo da so vse poteze ze bile odigane. po formuli izberemo tisto pa kateri bomo nadaljevali
		Igra igra = odigrajPoPoti(pot);
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		
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
	
	public void expansion(Igra igra) {
		
	}
	
	
	
//3.simulation.............................................................
	
	//simulation se vedno zacne na tocki, ki se ni bila obiskana
	
	public static Igralec simulation(Igra igra) {
		return OdigrajRandomIgro.odigraj(igra); //vrne igralca, ki je zmagal igro. ce vrne null je izenaceno.
	}
	
//4.backpropagation...................................................................
	
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