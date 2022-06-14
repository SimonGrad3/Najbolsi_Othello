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
	protected static HashMap<LinkedList<Poteza>, double[]> drevo = new HashMap<LinkedList<splosno.Poteza>, double[]>();
	
	protected Igralec jaz; //igralec na potezi (barva racunalnika)
	protected static Igra igra;
	protected int stPoskusov;	
	
	public MC(Igra igra, int st) {
		MC.igra = igra;
		this.jaz = igra.naPotezi;
		this.stPoskusov = st;
	}


//pomozne funkcije - print............................................................................
	
	public static void printDrevo (HashMap<LinkedList<Poteza>, double[]> drevo) {
			System.out.println("Drevo: --------------------");
	         for (LinkedList<Poteza> poteza: drevo.keySet()) {
	             String key = poteza.toString();
	             double[] value = drevo.get(poteza);
	             double x = value[0];
	             double y = value[1];
	             System.out.println("{ " + key + ", (" + x + ", " + y + ") }");
	         }
	         System.out.println("------------------------------");
	     }
	
	public static void printPot(LinkedList<Poteza> pot) {
		System.out.print(pot);
	}

//GLAVNA FUNKCIJA.............................................................................. 
	
	public Poteza izberiPotezo() {
		//poglej ce je mozen kaksen kot
		Poteza[] moznePoteze = Igra.edineMoznePoteze(igra.naPotezi, igra.plosca);
		Poteza kot1 = new Poteza(0, 0);
		Poteza kot2 = new Poteza(0, 7);
		Poteza kot3 = new Poteza(7, 0);
		Poteza kot4 = new Poteza(7, 7);
		for (int i = 0; i < moznePoteze.length; ++i) {
			if (moznePoteze[i].equals(kot1) || moznePoteze[i].equals(kot2) || moznePoteze[i].equals(kot3) || moznePoteze[i].equals(kot4) ) return moznePoteze[i];
		}
		
		//drugace pa mc
		LinkedList<Poteza> pp = new LinkedList<Poteza>();
		pp.add(new Poteza(10, 10)); //zacetna pika v drevesu
		double[] a = new double[2];
		a[0] = 0;
		a[1] = 0;
		drevo.put(pp, a);
		
		//odigra igro stPoskusov-krat
		for (int i = 0; i < stPoskusov; ++i) {
			LinkedList<Poteza> praznaPot = new LinkedList<Poteza>();
			praznaPot.add(new Poteza(10, 10));
			odigraj(praznaPot);
		}
		
		//izbere najboljso potezo
		Poteza izbranaPoteza = RandomIgralec.izberiPotezo(igra);
		double max = 0;
		
		for (Poteza p : moznePoteze) {
			LinkedList<Poteza> praznaPot = new LinkedList<Poteza>();
			praznaPot.add(new Poteza(10, 10));
			LinkedList<Poteza> pot = new LinkedList<Poteza>(praznaPot);
		
			pot.add(p);
			double[] value = drevo.get(pot);
			double n = value[1];
			
			if (n > max) izbranaPoteza = p;
		}
		return izbranaPoteza;
	}
	
	//odigra igro od dolocenega vozlisca naprej
	public void odigraj(LinkedList<Poteza> pot){
		LinkedList<Poteza> praznaPot = najdiPraznoPot(pot); //poskusamo najti prazno pot
		if (praznaPot != null) {
			pomoznaFunkcija(praznaPot); // Ce najdemo prazno pot klicemo pomozno funkcijo
		}
		else {
			LinkedList<Poteza> novaPot = izberiVejo(pot); //Ce prazne poti ni, izberemo najboljse nadaljevanje poti
			odigraj(novaPot); // Rekurzivno odigra od novega vozlisca naprej
		}
	}
	
	
	public void pomoznaFunkcija(LinkedList<Poteza> pot) {
		expansion(pot);
		Igralec zmagovalec = simulation(odigrajPoPoti(pot));
		popraviDrevo(pot, zmagovalec);
		
	}
	
//POMOZNE FUNKCIJE .............................................................
	
	public static Igra kopijaIgre(Igra igra) {
		Igra novaIgra = new Igra();
		//prepis plosce na novo mesto 
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
		
		for (Poteza p : moznePoteze) {
			pot.add(p);
			if (!drevo.containsKey(pot)) {
				return pot;
			}
			else pot.removeLast();
		}
		return null;
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
			LinkedList<Poteza> novaPot = new LinkedList<Poteza>(pot);
			novaPot.add(p);
			double vrednost = formula(novaPot, W, N);
			if (vrednost >= max) izbranaPoteza = p;
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
	
	public void expansion(LinkedList<Poteza> pot) {
		double[] prazenVnos = new double[2];
		prazenVnos[0] = 0.0;
		prazenVnos[1] = 0.0;
		drevo.put(pot, prazenVnos);
	}
	
//3.simulation......................................................................
	
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
            if(dolzina % 2 == 1 ) { //Ce je dolzina soda je na vrsti jaz
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
    }

}