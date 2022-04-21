package logika;

import splosno.Poteza;

public class Igra {

	public static void main(String[] args) {
		String[][] i = new String[8][8]; 
		System.out.println(i[0][0]);
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
	
	//vse vzamejo koordiante x, y od poteze 
	//get stolpec
	//get vrstica
	//get diagonala liha 14
	// soda 14 
	
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
		String[] diagonalaD = getDiagonalaD(x, y);
		
		int mestoStolpec = x;
		int mestoVrstica = y;
		int mestoDiagonalaL = mestoDiagonalaL(x, y);
		int mestoDiagonalaD = mestoDiagonalaD(x, y);
		
		
		boolean s = preveri(stolpec, mestoStolpec);
		boolean v = preveri(stolpec, mestoStolpec);
		boolean l = preveri(stolpec, mestoStolpec);
		boolean d = preveri(stolpec, mestoStolpec);
		
		if (!s && !v && !l && !d ) return false;
		
		if (s) vloziStolpec(spremeni(stolpec, mestoStolpec));
		if (v) vloziVrstico(spremeni(vrstica, mestoVrstica));
		if (v) spremeni(vrstica);
		if (v) spremeni(vrstica);
		
		igralec = nasprotnik();
		return true;
	}

}
