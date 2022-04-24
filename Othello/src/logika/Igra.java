package logika;

import splosno.Poteza;

public class Igra {

	public static void main(String[] args) {
		String[][] primerStevilke = {
				{"00", "01", "02", "03", "04", "05", "06", "07"},
				{"10", "11", "12", "13", "14", "15", "16", "17"},
				{"20", "21", "22", "23", "24", "25", "26", "27"},
				{"30", "31", "32", "33", "34", "35", "36", "37"},
				{"40", "41", "42", "43", "44", "45", "46", "47"},
				{"50", "51", "52", "53", "54", "55", "56", "57"},
				{"60", "61", "62", "63", "64", "65", "66", "67"},
				{"70", "71", "72", "73", "74", "75", "76", "77"},
				};
		
		String[][] mat = zarotirajLevo(zarotirajDesno(primerStevilke));
		
		String[] d = {"e", "e", "e", "e", "e", "e", "e", "e"};
		
		String[][] pl = vloziDiagonaloS(0, 6, d, primerStevilke);
		
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				System.out.println(pl[i][j]);;
			}
		}
		
		System.out.println("---");
		
		String[] z = getDiagonalaS(1, 5, primerStevilke);
		for(int k = 0; k < z.length; ++k) {
			System.out.println(z[k]);
		}
		
		System.out.println("---");
		
		//int m = mestoDiagonalaL(4,3,primerStevilke);
		
		//System.out.print(m);
	
	}
	protected String crn = "C";
	protected String bel = "B";
	protected String igralec;
	protected String[][] igra;
	protected String nasprotnik;		
	
	// x je vrstica 
	// y je stolpec 
	//najprej dol potem desno
	
	public Igra() {
		this.igra  = new String[8][8];
		igra[3][3] = bel;
		igra[4][4] = bel;
		igra[3][4] = crn;
		igra[4][3] = crn;
		this.igralec = crn;
		this.nasprotnik = nasprotnik();
	}
	
	public String nasprotnik() {
		if (this.igralec == bel) return crn;
		return bel;
	}
	
	//funkcije get ----------------------------------------------------------------------------------
	
	public static String[] getVrstica(int x, int y, String[][] plosca) {
			return plosca[x];
	}
	
	public static String[] getStolpec(int x, int y, String[][] plosca) {
		String[] stolpec = new String[8];
		for (int i = 0; i < 8; ++i) {
			stolpec[i] = plosca[i][y];
		}
		return stolpec;
	}
	
	//liha diagonala
	public static String[] getDiagonalaL(int x, int y, String[][] plosca) { 
		int st = x + y; //stevilka diagonale - imamo 15 razlicnih sodih diagonal
		int zacetekX = Math.max(0, st - 7); //prva (najmanjsa) vrstica ki se jo dotakne
		int zacetekY= Math.min(7, st); // zadnji (najvecji) stolpec ki je ga diagonala dotakne
		int dolzina = Math.min(st + 1, 15 - st);
		String[] diagonala = new String[dolzina];
		for (int i = 0; i <  dolzina; ++i) { //zanka cez vrstice ki se jih diagonala dotakne
			diagonala[i] = plosca[zacetekX + i][zacetekY - i];
		}
		return diagonala;
	}
	
	public static String[][] zarotirajLevo(String[][] star) { //zarotira matriko za 90 stopinj v levo
		String[][] nov = new String[8][8];
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				nov[x][y] = star[7 - y][x];			
				}
		}
		return nov;
	}
	
	public static String[][] zarotirajDesno(String[][] star) { //zarotira matriko za 90 stopinj v desno - inverzna zarotirajLevo
		String[][] nov = new String[8][8];
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				nov[x][y] = star[y][7 - x];			
				}
		}
		return nov;
	}
	
	//soda diagonala
	public static String[] getDiagonalaS(int x0, int y0, String[][] plosca) { 
		int x = 7 - x0;
		int y = y0;
		String[][] nova = zarotirajLevo(plosca);
		return getDiagonalaL(x, y, nova);
	}
	
	//mesto jajcka -----------------------------------------------------------------------------------------
	
	public static int mestoDiagonalaL(int x, int y, String[][] plosca) {
		String elt = plosca[x][y];
		String[] d = getDiagonalaL(x, y, plosca);
		for (int i = 0; i < d.length; ++i) {
			if (d[i] == elt) {
				return i;
			}
		}
		return -1;
	}
	
	
	public static int mestoDiagonalaS(int x, int y, String[][] plosca) {
		String elt = plosca[x][y];
		String[] d = getDiagonalaS(x, y, plosca);
		for (int i = 0; i < d.length; ++i) {
			if (d[i] == elt) {
				return i;
			}
		}
		return -1;
	}
	
	//------------------------------------------------------------------------------------------------------
	
	//preveri vzame seznam in lokacijo jajcka, barva igralca; preveri bele crne sosede
	
	public boolean preveri(String[] seznam, int i) { 
		// i - oznaèuje mesto poteze. 
		// Preverimo, da je vsaj en element enak nasprotnemu znakcu.
		if ((seznam[i + 1] != this.nasprotnik) && (seznam[i-1] != this.nasprotnik)) return false;
		else if (seznam[i + 1] == this.nasprotnik) {  //Èe je i+1 element ustrezen 
			for (int j = i + 1; j < seznam.length; j++) { 
				if (seznam[j] == this.nasprotnik) continue; 
				else if (seznam[j] == null) return false; 
				else return true; 
				} 
			return false;
			} 
		
		else if (seznam[i - 1] == this.nasprotnik) { 
			for (int j = i-1; j >= 0; j--) { 
				if (seznam[j] == this.nasprotnik) continue; 
				else if (seznam[j] == null) return false; 
				else return true; 
				} 
			return false;
			} 
		else return false;
		}
	
	
	
	//spremeni je funkcija, ki vrne stolpec spremeni(stolpec)
	
	//vlozi------------------------------------------------------------------------------------------------
	
	
	public static String[][] vloziVrstico(int x, int y, String[] vrstica, String[][] plosca) {
		plosca[x] = vrstica;
		return plosca;
	}
	
	public static String[][] vloziStolpec(int x, int y, String[] stolpec, String[][] plosca) {
		for (int i = 0; i < 8; ++i) {
			plosca[i][y] = stolpec[i];
		}
		return plosca;
	}

	
	public static String[][] vloziDiagonaloL(int x, int y, String[] diagonala, String[][] plosca) {
		int st = x + y;
		int zacetekX = Math.max(0, st - 7); 
		int zacetekY= Math.min(7, st); 
		int dolzina = Math.min(st + 1, 15 - st);
		for (int i = 0; i <  dolzina; ++i) {
			plosca[zacetekX + i][zacetekY - i] = diagonala[i];
		}
		return plosca;
	}
	
	public static String[][] vloziDiagonaloS(int x0, int y0, String[] diagonala, String[][] plosca) {
		int x = 7 - x0;
		int y = y0;
		plosca = zarotirajLevo(plosca);
		vloziDiagonaloL(x, y, diagonala, plosca);
		return zarotirajDesno(plosca);
	}
	
	//-------------------------------------------------------------------------------------------------------
	public boolean odigraj(Poteza poteza) {
		int x = poteza.getX(); //vrstica
		int y = poteza.getY(); //stolpec
		//ce je prazno mesto 
		if (igra[x][y] != null) return false;
		String[] stolpec = getStolpec(x, y, igra);
		String[] vrstica = getVrstica(x, y, igra);
		String[] diagonalaL = getDiagonalaL(x, y, igra);
		String[] diagonalaS = getDiagonalaS(x, y, igra);
		
		int mestoStolpec = x;
		int mestoVrstica = y;
		int mestoDiagonalaL = mestoDiagonalaL(x, y, igra);
		int mestoDiagonalaS = mestoDiagonalaS(x, y, igra);
		
		boolean s = preveri(stolpec, mestoStolpec, igra);
		boolean v = preveri(vrstica, mestoVrstica);
		boolean liha = preveri(diagonalaL, mestoDiagonalaL);
		boolean soda = preveri(diagonalaS, mestoDiagonalaS);
		
		if (!s && !v && !liha && !soda ) return false;
		
		if (s) vloziStolpec(spremeni(stolpec, mestoStolpec));
		if (v) vloziVrstico(spremeni(vrstica, mestoVrstica));
		if (liha) spremeni(vrstica);
		if (soda) spremeni(vrstica);
		
		igralec = nasprotnik();
		return true;
	}

}