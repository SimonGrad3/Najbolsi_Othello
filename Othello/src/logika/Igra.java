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
		String[][] tr = transponiraj(primerStevilke);
		
		//for (int i = 0; i < 8; ++i) {
		//	for (int j = 0; j < 8; ++j) {
		//		System.out.println(tr[i][j]);;
		//	}
		//}
		
		
		String[] z = getDiagonalaL(1, 1, primerStevilke);
		for(int k = 0; k < 8; ++k) {
			System.out.print(z[k]);
		}
			
		
		
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
	
	public String[] getVrstica(int x, int y) {
			return igra[x];
	}
	
	public String[] getStolpec(int x, int y) {
		String[] stolpec = new String[8];
		for (int i = 0; i < 8; ++i) {
			stolpec[i] = igra[i][y];
		}
		return stolpec;
	}
	
	public static String[] getDiagonalaS(int x, int y, String[][] plosca) { //soda diagonala
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
	
	public static String[][] transponiraj(String[][] a) {
			String[][] b = new String[8][8];
			for (int i = 0; i < 8; ++i) {
				for (int j = 0; j < 8; ++j) {
					b[j][i] = a[i][j];
				}
			}
			return b;
	}
	
	
	//popravi!
	public static String[] getDiagonalaL(int x0, int y0, String[][] plosca ) { //liha diagonala
		int x = y0;
		int y = 7 - x0;
		String[][] tr = transponiraj(plosca);
		return getDiagonalaS(x, y, tr);
	}
	
	
	
	//mesto jajcka:
	//vrstica
	//stolpec
	//liha
	//soda 
	
	public String nasprotnik() {
		if (this.igralec == bel) return crn;
		return bel;
	}
	
	//preveri vzame seznam in lokacijo jajcka, barva igralca; preveri bele crne sosede
	
	//spremeni je funkcija, ki vrne stolpec spremeni(stolpec)
	
	//vlozi stolpec, vrstico,...
	
	public boolean odigraj(Poteza poteza) {
		int x = poteza.getX(); //vrstica
		int y = poteza.getY(); //stolpec
		//ce je prazno mesto 
		if (igra[x][y] != null) return false;
		String[] stolpec = getStolpec(x, y);
		String[] vrstica = getVrstica(x, y);
		String[] diagonalaL = getDiagonalaL(x, y);
		String[] diagonalaS = getDiagonalaS(x, y);
		
		int mestoStolpec = x;
		int mestoVrstica = y;
		int mestoDiagonalaL = mestoDiagonalaL(x, y);
		int mestoDiagonalaD = mestoDiagonalaS(x, y);
		
		
		boolean s = preveri(stolpec, mestoStolpec);
		boolean v = preveri(stolpec, mestoStolpec);
		boolean liha = preveri(stolpec, mestoStolpec);
		boolean soda = preveri(stolpec, mestoStolpec);
		
		if (!s && !v && !liha && !soda ) return false;
		
		if (s) vloziStolpec(spremeni(stolpec, mestoStolpec));
		if (v) vloziVrstico(spremeni(vrstica, mestoVrstica));
		if (liha) spremeni(vrstica);
		if (soda) spremeni(vrstica);
		
		igralec = nasprotnik();
		return true;
	}

}
