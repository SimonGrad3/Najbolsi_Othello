import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Stanje;
import splosno.Poteza;

public class IgrajOthello {

	public static void main(String[] args) {
		izpisiPlosco();
	}
	
	static Igra igra = new Igra();
	
	
	//inteligenca......................................................................................
	public static Inteligenca racunalnikovaInteligenca = new Inteligenca(); 
	
	//-----------------------------------------------------------------------------------------------------
	//funkcije za izpis ----------------------------------------------------------------------------------
	
	public static void izpisiPlosco() {
		Polje[][] plosca = igra.plosca;
		
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
		izpisiMoznePoteze(igra.naPotezi);
		System.out.println();
		
		System.out.println("--------");
		
		if (igra.stanje == Stanje.V_TEKU) odigraj();
	}
	
	public static void odigraj() {
//razveljavi potezo:
		Scanner myObj = new Scanner(System.in); 
//		System.out.println("Razveljavi zadnjo potezo? Vpisi 'da' ali pusti prazno: ");
//	    String r  = myObj.nextLine();
	    
//	    if (r.equals("da")) {
//	    	if (!igra.razveljaviPotezo()) {
//	    		System.out.println("Poteze ni mogoče razveljaviti!");
//	    		System.out.println();
//	    	}
//	    	igra.razveljaviPotezo();
//	    }
	    
//	    else {
	    	
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
	    		Poteza p = racunalnikovaInteligenca.izberiPotezo(igra);
	    		boolean i = igra.odigraj(p);
			    if (!i) System.out.println("Odigrana poteza ni veljavna! Poskusi ponovno.");
	    	}
//	    }
	    
	    izpisiPlosco();
	}

	//------------------------------------------------------------------------------------------------
	//pomozne funkicje za izpis----------------------------------------------------------------------
	
	public static void izpisiStanjeIgre(Igralec igralec) {
		Stanje stanje = igra.stanjeIgre();
		System.out.println();
		if (stanje == stanje.V_TEKU) System.out.println("Igra še ni končana. Na vrsti je " + igralec + ". ");
		else if (stanje == Stanje.NEODLOCENO) System.out.println("Igra je koncana. Igralca sta izenačena.");
		else if (stanje == Stanje.ZMAGA_CRN) System.out.println("Igra je koncana. Zmagal je črni.");
		else if (stanje == Stanje.ZMAGA_BEL) System.out.println("Igra je koncana. Zmagal je beli.");
	}
	
	
	public static void izpisiStanjeZetonov() {
		int[] t = igra.stanjeZetonov();
		System.out.println();
		System.out.println("Crni: " + t[0]);
		System.out.println("Beli: " + t[1]);
	}
	
	public static void izpisiMoznePoteze(Igralec igralec) {
		Poteza[] p = igra.moznePoteze();
		int d = igra.steviloMoznihPotez();
		System.out.println();
		System.out.println("Mozne poteze za " + igralec.toString() + ": ");
		if (d == 0) System.out.println("Ni možnih potez. Igre je konec.");
		else {
			for (int i = 0; i < d; ++i) {
				System.out.println("- (" + p[i].getX() + ", " + p[i].getY() + ")");
			}
		}
	}
}
