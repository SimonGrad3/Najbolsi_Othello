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
	
//	public static void main(String[] args) {		
//		printDrevo(drevo);
//	}

	protected LinkedList<Poteza> indeksDrevesa; //pot je seznam potez
	protected double[] vnosDrevesa; //[zmage, vsiPoskusi]
	protected static HashMap<LinkedList<Poteza>, double[]> drevo = new HashMap<LinkedList<splosno.Poteza>, double[]>();
	
	protected Igralec jaz;
	protected static Igra igra;
	protected int stPoskusov;	
	
	public MC(Igra igra, int st) {
		MC.igra = igra;
		this.jaz = igra.naPotezi;
		this.stPoskusov = st;
	}


//print............................................................................
	
	public static void printDrevo (HashMap<LinkedList<Poteza>, double[]> drevo) {
			System.out.println("Drevo: --------------------");
	         for (LinkedList<Poteza> poteza: drevo.keySet()) {
	             String key = poteza.toString();
	             double[] value = drevo.get(poteza);
	             double x = value[0];
	             double y = value[1];
	             //System.out.println("{ " + key + ", (" + x + ", " + y + ") }");
	         }
	         System.out.println("------------------------------");
	     }
	
//	public static void printPot(LinkedList<Poteza> pot) {
//		System.out.println("Printam pot: ");
//		for (int i = 0; i < pot.size(); ++i) {
//			System.out.println(pot.get(i).toString());
//		}
	
	
//	}
	
	public static void printPot(LinkedList<Poteza> pot) {
		System.out.print(pot);
	}

//glavno............................................................................... 
	
	public Poteza izberiPotezo() {
		//poglej kote 
		
		
		
		//drugace pa mc
		LinkedList<Poteza> pp = new LinkedList<Poteza>();
		pp.add(new Poteza(10, 10));
		
		double[] a = new double[2];
		a[0] = 0;
		a[1] = 0;
		
		
		drevo.put(pp, a);
		System.out.println("NEUMNOST----------------------------------------------------------------------");
		
		for (int i = 0; i < stPoskusov; ++i) {
			LinkedList<Poteza> praznaPot = new LinkedList<Poteza>();
			praznaPot.add(new Poteza(10, 10));
			odigraj(praznaPot);
			//printDrevo(drevo);
		}
		System.out.println("Koncal sem z pokskusi.");
		
		Poteza izbranaPoteza = RandomIgralec.izberiPotezo(igra);
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		double max = 0;
		
		for (Poteza p : moznePoteze) {
			LinkedList<Poteza> praznaPot = new LinkedList<Poteza>();
			praznaPot.add(new Poteza(10, 10));
			LinkedList<Poteza> pot = new LinkedList<Poteza>(praznaPot);
		
			pot.add(p);
			System.out.println("TRALALA -------------------");
			printPot(pot);
			double[] value = drevo.get(pot);
			double n = value[1];
			//System.out.println(n);
			
			if (n > max) izbranaPoteza = p;
		}
		
		//System.out.println("Izbrana poteza: " + izbranaPoteza.toString());
		return izbranaPoteza;
	}
	
	public void odigraj(LinkedList<Poteza> pot){
		printPot(pot);
		printDrevo(drevo);
		LinkedList<Poteza> praznaPot = najdiPraznoPot(pot);
		System.out.println("prazna pot");
		printPot(praznaPot);
		if (praznaPot != null) {
			System.out.println("Nasel sem prazno pot");
			printPot(praznaPot);
			pomoznaFunkcija(praznaPot);
		}
		else {
			System.out.println("Nisem nasel prazne poti");
			LinkedList<Poteza> novaPot = izberiVejo(pot);
			odigraj(novaPot);
		}
	}
	
	public void pomoznaFunkcija(LinkedList<Poteza> pot) {
		System.out.println("sem v pomozni funkciji");
		expansion(pot);
		Igralec zmagovalec = simulation(odigrajPoPoti(pot));
		if (zmagovalec == null) System.out.println("Zmagovalec random igre: N");
		else System.out.println("Zmagovalec random igre: " + zmagovalec.toString());
		popraviDrevo(pot, zmagovalec);
		System.out.println("Koncal sem v pomozni funkciji");
		
	}
	
	
//pomozne funkcije.............................................................
	
	public static Igra kopijaIgre(Igra igra) {
		Igra novaIgra = new Igra();
		//prepis plosce
		Polje[][] staraPlosca = igra.plosca;
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				novaIgra.plosca[x][y] = staraPlosca[x][y];
			}
		}
		//prepis igralca in stanja
		Igralec starIgralec = igra.naPotezi;
		Stanje staroStanje = igra.stanje;
		novaIgra.naPotezi = starIgralec;
		novaIgra.stanje = staroStanje;
		return novaIgra;
	}
	
	
	public static Igra odigrajPoPoti(LinkedList<Poteza> pot) {
		Igra novaIgra = kopijaIgre(igra);
		for (Poteza p : pot){
			novaIgra.odigraj(p);
		}
		return novaIgra;
	}
	
	public LinkedList<Poteza> najdiPraznoPot(LinkedList<Poteza> pot) {
		Igra novaIgra = odigrajPoPoti(pot);
		Poteza[] moznePoteze = Igra.edineMoznePoteze(novaIgra.naPotezi, novaIgra.plosca);
		

//		//System.out.println("Printam mozne poteze: ");
//		System.out.println(moznePoteze.length);
//		for(int i = 0; i < moznePoteze.length; ++i) {
//			System.out.println(moznePoteze[i].toString());
//		}
//		System.out.println("Konec moznih potez.");
		
		for (Poteza p : moznePoteze) {
			pot.add(p);
			if (!drevo.containsKey(pot)) {
//				System.out.println("To je izbrana prazna pot:");
//rintPot(pot);
				return pot;
			}
			else pot.removeLast();
		}
		return null;
	}
	
	
//1.selection.......................................................................
	
	public LinkedList<Poteza> izberiVejo(LinkedList<Poteza> pot){ 
		//printPot(pot);
		//vemo da so vse poteze ze bile odigane. po formuli izberemo tisto pa kateri bomo nadaljevali
		Igra igra = odigrajPoPoti(pot);
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		
		//printDrevo(drevo);
		double[] value = drevo.get(pot);
		double W = value[0];
		double N = value[1];
//		System.out.println(W);
//		System.out.println(N);
		
		Poteza izbranaPoteza = null;
		double max = 0;
		
		for (Poteza p : moznePoteze) {
			System.out.println(p.toString());
			LinkedList<Poteza> novaPot = new LinkedList<Poteza>(pot);
			novaPot.add(p);
			double vrednost = formula(novaPot, W, N);
			System.out.println("Vrednosti: ");
			System.out.println(vrednost);
			if (vrednost >= max) izbranaPoteza = p;
		}
		
		pot.add(izbranaPoteza);
		return pot;
	}
	
	public double formula(LinkedList<Poteza> pot, double W, double N) {
//		System.out.println("W: ");
//		System.out.println(W);
//		System.out.println("N: ");
//		System.out.println(N);
		double[] value = drevo.get(pot);
//		printPot(pot);
		double w = value[0];
//		System.out.println("w: ");
//		System.out.println(w);
		double n = value[1];
//		System.out.println("n: ");
//		System.out.println(n);
		double C = Math.sqrt(2);
		if (n == 0 || N <= 1) System.out.println(n + " " + N);
		return W/n + C * Math.sqrt( Math.log(N) / n);
	}
	
	
//2. expansion............................................................
	
	public void expansion(LinkedList<Poteza> pot) {
		double[] prazenVnos = new double[2];
		prazenVnos[0] = 0.0;
		prazenVnos[1] = 0.0;
		drevo.put(pot, prazenVnos);
	}
	
//3.simulation.............................................................
	
	//simulation se vedno zacne na tocki, ki se ni bila obiskana
	
	public static Igralec simulation(Igra igra) {
		Igralec z = OdigrajRandomIgro.odigraj(igra);
		return z; //vrne igralca, ki je zmagal igro. ce vrne null je izenaceno.
	}
	
//4.backpropagation...................................................................
	
	
	public void popraviDrevo (LinkedList<Poteza> pot, Igralec zmaga){
        int dolzina = pot.size();

        if(dolzina > 0) {

            double[] value = drevo.get(pot);
            double x = value[0];
            if(dolzina % 2 == 1 ) {
                if(zmaga == jaz) x += 1.0;
                else if (zmaga == null) {
                    x += 0.5;
                }
            }
            else {
                if(zmaga != jaz) x += 1.0;
                else if (zmaga == null) {
                    x += 0.5;
                }
            }
            value[0] = x;
            value[1] += 1.0;
          
            drevo.put(pot, value);
            
            LinkedList<Poteza> novaPot = new LinkedList<Poteza>(pot);
            novaPot.removeLast();
            popraviDrevo (novaPot, zmaga);
        }
        //printDrevo(drevo);

    }

}