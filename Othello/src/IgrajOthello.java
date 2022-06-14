import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import inteligenca.Inteligenca;
import inteligenca.RandomIgralec;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Stanje;
import splosno.Poteza;

public class IgrajOthello {

	public static void main(String[] args) {		
		ClovekRacunalnik();
	}
	
	static Igra igra = new Igra();
	public static Inteligenca racunalnikovaInteligenca = new Inteligenca(); 
	//-----------------------------------------------------------------------------------------------------

	//RACUNALNIK PROTI RACUNALNIKU
	public static void RacunalnikRacunalnik() {
		izpisiPlosco1();
	}
	
	public static void izpisiPlosco1() {
		Polje[][] plosca = igra.plosca;
		Igralec igralec = igra.naPotezi;
		
		//izpis stanja igre
		System.out.println("#########################################");
		izpisiStanjeIgre(igra.naPotezi);
		System.out.println();
		
		//izpis plosce
		System.out.println("Trenutna plosca:");
		System.out.print("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		for (int i = 0; i < 8; ++i) {
			System.out.println();
			System.out.println("------------------------------------");
			System.out.print(" " + i + " | ");
			for (int j = 0; j < 8; ++j) {
				if (plosca[i][j] == Polje.PRAZNO) {
					System.out.print(" "+ " | ");;
				}
				else {
					System.out.print(plosca[i][j]+ " | ");;
				}
			}
		}
		System.out.println();
		izpisiStanjeZetonov();
		//izpis moznih potez
		Poteza[] p = igra.moznePoteze(igralec, igra.plosca);
		int d = igra.steviloMoznihPotez(igralec, igra.plosca);
		System.out.println();
		System.out.println("Mozne poteze za " + igralec.toString() + ": ");
		if (d == 0) { //ce igralec ki je na vrsti nima moznih potez
			System.out.println("Ni možnih potez za " + igralec.toString() + ".");
			System.out.println("Nimaš možnih potez, zato bo odigral tvoj nasprotnik.");
			igra.zamenjajIgralca();
			if (Stanje.V_TEKU == igra.stanjeIgre()) odigraj1();
			else System.out.print("IGRE JE KONEC!");
		}
		else {//ce so mozne poteze jih izpise in igra naprej
			//izpis
			for (int i = 0; i < d; ++i) {
				System.out.println("- (" + p[i].getX() + ", " + p[i].getY() + ")");
			}
			//nadaljevanje igre
			if (Stanje.V_TEKU == igra.stanjeIgre()) odigraj1();
			else System.out.print("IGRE JE KONEC!");
		}
		System.out.println();
		
	}
	
	public static void odigraj1() {
	    Poteza p = racunalnikovaInteligenca.izberiPotezo(igra);
	    System.out.println("Tvoja poteza: (" + p.getX() + ", " + p.getY() + ")");
	    boolean i = igra.odigraj(p);
	    if (!i) System.out.println("Odigrana poteza ni veljavna! Poskusi ponovno.");
		izpisiPlosco1();
	}
	//---------------------------
	
	

	//CLOVEK PRORI RACUNALNIKU ----------------------------------------------------------------------------------
	
	public static void ClovekRacunalnik() {
		izpisiPlosco2();
	}
	
	public static void izpisiPlosco2() {
		Polje[][] plosca = igra.plosca;
		Igralec igralec = igra.naPotezi;
		
		//izpis stanja igre
		System.out.println("#########################################");
		izpisiStanjeIgre(igra.naPotezi);
		System.out.println();
		
		//izpis plosce
		System.out.println("Trenutna plosca:");
		System.out.print("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		for (int i = 0; i < 8; ++i) {
			System.out.println();
			System.out.println("------------------------------------");
			System.out.print(" " + i + " | ");
			for (int j = 0; j < 8; ++j) {
				if (plosca[i][j] == Polje.PRAZNO) {
					System.out.print(" "+ " | ");;
				}
				else {
					System.out.print(plosca[i][j]+ " | ");;
				}
			}
		}
		System.out.println();
		
		izpisiStanjeZetonov();
		
		//izpis moznih potez
		Poteza[] p = igra.moznePoteze(igralec, igra.plosca);
		int d = igra.steviloMoznihPotez(igralec, igra.plosca);
		System.out.println();
		System.out.println("Mozne poteze za " + igralec.toString() + ": ");
		if (d == 0) { //ce igralec ki je na vrsti nima moznih potez
			System.out.println("Ni možnih potez za " + igralec.toString() + ".");
			System.out.println("Nimaš možnih potez, zato bo odigral tvoj nasprotnik.");
			igra.zamenjajIgralca();
			if (Stanje.V_TEKU == igra.stanjeIgre()) odigraj2();
			else System.out.print("IGRE JE KONEC!");
		}
		else {//ce bo mozne poteze jih izpise in igra naprej
			//izpis
			for (int i = 0; i < d; ++i) {
				System.out.println("- (" + p[i].getX() + ", " + p[i].getY() + ")");
			}
			//nadaljevanje igre
			if (Stanje.V_TEKU == igra.stanjeIgre()) odigraj2();
			else System.out.print("IGRE JE KONEC!");
		}
		System.out.println();
		
	}
	
	
	
	
	public static void odigraj2() {
		//razveljavi potezo:
		Scanner myObj = new Scanner(System.in); 
		System.out.println("Razveljavi zadnjo potezo? Vpisi 'da' ali pusti prazno: ");
	    String r  = myObj.nextLine();
	    
	    if (r.equals("da")) {
	    	if (!igra.razveljaviPotezo()) {
	    		System.out.println("Poteze ni mogoče razveljaviti!");
	    		System.out.println();
	    	}
	    	igra.razveljaviPotezo();
	    }
	    
	    else {
		
	    	if (igra.naPotezi == Igralec.CRN) { //odigra clovek
			    System.out.println("Vnesi vrstico: ");
			    int x  = myObj.nextInt(); 
			    System.out.println("Vnesi stolpec: ");
			    int y  = myObj.nextInt(); 
			    System.out.println("Tvoja poteza: (" + x + ", " + y + ")");
			    Poteza p = new Poteza(x, y);
			    boolean i = igra.odigraj(p);
			    if (!i) System.out.println("Odigrana poteza ni veljavna! Poskusi ponovno.");
	    	}
	    	else { //igra racunalnik
	    		System.out.println("Na vrsti je racunalnik. Barva: " + igra.naPotezi.toString());
	    		Poteza p = racunalnikovaInteligenca.izberiPotezo(igra);
	    		System.out.println("Tvoja poteza: (" + p.getX() + ", " + p.getY() + ")");
	    		boolean i = igra.odigraj(p);
			    if (!i) System.out.println("Odigrana poteza ni veljavna! Poskusi ponovno.");
	    	}
		
//	    }
		izpisiPlosco2(); //izpise in na koncu klice odigraj z novimi podatki
	    }
	}

	
	//------------------------------------------------------------------------------------------------
	//pomozne funkicje za izpis----------------------------------------------------------------------
	
	public static void izpisiStanjeIgre(Igralec igralec) {
		Stanje stanje = igra.stanjeIgre();
		System.out.println();
		if (stanje == stanje.V_TEKU) System.out.println("Igra še ni končana. Na vrsti je " + igralec + ". ");
		else if (stanje == Stanje.NEODLOCENO) System.out.println("IGRA JE KONCANA. Igralca sta izenačena.");
		else if (stanje == Stanje.ZMAGA_CRN) System.out.println("IGRA JE KONCANA. Zmagal je črni.");
		else if (stanje == Stanje.ZMAGA_BEL) System.out.println("IGRA JE KONCANA. Zmagal je beli.");
	}
	
	
	public static void izpisiStanjeZetonov() {
		int[] t = igra.stanjeZetonov(igra.plosca);
		System.out.println();
		System.out.println("Crni: " + t[0]);
		System.out.println("Beli: " + t[1]);
	}
	
	public static void izpisiMoznePoteze(Igralec igralec) {
		Poteza[] p = igra.moznePoteze(igralec, igra.plosca);
		int d = igra.steviloMoznihPotez(igralec, igra.plosca);
		System.out.println();
		System.out.println("Mozne poteze za " + igralec.toString() + ": ");
		if (d == 0) System.out.println("Ni možnih potez za " + igralec.toString() + ".");
		else {
			for (int i = 0; i < d; ++i) {
				System.out.println("- (" + p[i].getX() + ", " + p[i].getY() + ")");
			}
		}
	}
}
