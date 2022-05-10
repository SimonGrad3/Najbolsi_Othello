package logika;

import splosno.Poteza;

public class Igra {
	
	public Igralec naPotezi;
	public Polje[][] plosca;
	protected Polje[][] zadnjaPlosca;
	protected boolean nazaj;
	public Stanje stanje;
			
	// x je vrstica 
	// y je stolpec 
	
	
	public Igra() {
		plosca = new Polje[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		
		naPotezi = Igralec.CRN;	
		stanje = Stanje.V_TEKU;
		plosca[3][3] = Polje.BEL;
		plosca[4][4] = Polje.BEL;
		plosca[3][4] = Polje.CRN;
		plosca[4][3] = Polje.CRN;
		zadnjaPlosca = praznaPlosca;
		nazaj = false;
	}	
	
	//--------------------------------------------------------------------------------
	
	Polje[][] praznaPlosca = {
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			{Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO, Polje.PRAZNO},
			
			};
	
	//-----------------------------------------------------------------------------------------------------
	//funkcije get ----------------------------------------------------------------------------------------
	
	public static Polje[] getVrstica(int x, int y, Polje[][] plosca) {
			return plosca[x];
	}
	
	public static Polje[] getStolpec(int x, int y, Polje[][] plosca) {
		Polje[] stolpec = new Polje[8];
		for (int i = 0; i < 8; ++i) {
			stolpec[i] = plosca[i][y];
		}
		return stolpec;
	}
	
	//liha diagonala
	public static Polje[] getDiagonalaL(int x, int y, Polje[][] plosca) { 
		int st = x + y; //stevilka diagonale - imamo 15 razlicnih sodih diagonal
		int zacetekX = Math.max(0, st - 7); //prva (najmanjsa) vrstica ki se jo dotakne
		int zacetekY= Math.min(7, st); // zadnji (najvecji) stolpec ki je ga diagonala dotakne
		int dolzina = Math.min(st + 1, 15 - st);
		Polje[] diagonala = new Polje[dolzina];
		for (int i = 0; i <  dolzina; ++i) { //zanka cez vrstice ki se jih diagonala dotakne
			diagonala[i] = plosca[zacetekX + i][zacetekY - i];
		}
		return diagonala;
	}
	
	//soda diagonala
	public static Polje[] getDiagonalaS(int x0, int y0, Polje[][] plosca) { 
		int x = 7 - x0;
		int y = y0;
		Polje[][] nova = zarotirajLevo(plosca);
		return getDiagonalaL(x, y, nova);
	}
	
	public static Polje[][] zarotirajLevo(Polje[][] star) { //zarotira matriko za 90 stopinj v levo
		Polje[][] nov = new Polje[8][8];
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				nov[x][y] = star[7 - y][x];			
				}
		}
		return nov;
	}
	
	public static Polje[][] zarotirajDesno(Polje[][] star) { //zarotira matriko za 90 stopinj v desno - inverzna zarotirajLevo
		Polje[][] nov = new Polje[8][8];
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				nov[x][y] = star[y][7 - x];			
				}
		}
		return nov;
	}
	
	//------------------------------------------------------------------------------------------------------
	//mesto jajcka -----------------------------------------------------------------------------------------
	
	public static int mestoDiagonalaL(int x, int y, Polje[][] plosca) {
		if (x == 0) return 0;
		
		else if (x == 1){
			if (y == 7) return 0;
			else return 1;
		}
		
		else if (x == 2) {
			if (y == 7) return 0;
			else if (y == 6) return 1;
			else return 2;
		}
		
		else if (x == 3){
			if (y == 7) return 0;
			else if (y == 6) return 1;
			else if (y == 5) return 2;
			else return 3;
		}
		
		else if (x == 4){
			if (y == 7) return 0;
			else if (y == 6) return 1;
			else if (y == 5) return 2;
			else if (y == 4) return 3;
			else return 4;
		}
		
		else if (x == 5){
			if (y == 7) return 0;
			else if (y == 6) return 1;
			else if (y == 5) return 2;
			else if (y == 4) return 3;
			else if (y == 3) return 4;
			else return 5;
		}
		
		else if (x == 6){
			if (y == 7) return 0;
			else if (y == 6) return 1;
			else if (y == 5) return 2;
			else if (y == 4) return 3;
			else if (y == 3) return 4;
			else if (y == 2) return 5;
			else return 6;
		}
		
		else if (x == 7){
			if (y == 7) return 0;
			else if (y == 6) return 1;
			else if (y == 5) return 2;
			else if (y == 4) return 3;
			else if (y == 3) return 4;
			else if (y == 2) return 5;
			else if (y == 1) return 6;
			else return 7;
		}
		
		else  return -1;
	}
	
	
	public static int mestoDiagonalaS(int x, int y, Polje[][] plosca) {
		if (x == 0) return 0;
		
		else if (x == 1){
			if (y == 0) return 0;
			else return 1;
		}
		
		else if (x == 2) {
			if (y == 0) return 0;
			else if (y == 1) return 1;
			else return 2;
		}
		
		else if (x == 3){
			if (y == 0) return 0;
			else if (y == 1) return 1;
			else if (y == 2) return 2;
			else return 3;
		}
		
		else if (x == 4){
			if (y == 0) return 0;
			else if (y == 1) return 1;
			else if (y == 2) return 2;
			else if (y == 3) return 3;
			else return 4;
		}
		
		else if (x == 5){
			if (y == 0) return 0;
			else if (y == 1) return 1;
			else if (y == 2) return 2;
			else if (y == 3) return 3;
			else if (y == 4) return 4;
			else return 5;
		}
		
		else if (x == 6){
			if (y == 0) return 0;
			else if (y == 1) return 1;
			else if (y == 2) return 2;
			else if (y == 3) return 3;
			else if (y == 4) return 4;
			else if (y == 5) return 5;
			else return 6;
		}
		
		else if (x == 7){
			if (y == 0) return 0;
			else if (y == 1) return 1;
			else if (y == 2) return 2;
			else if (y == 3) return 3;
			else if (y == 4) return 4;
			else if (y == 5) return 5;
			else if (y == 6) return 6;
			else return 7;
		}
		
		else  return -1;
	}
	
	
	//------------------------------------------------------------------------------------------------------
	//preveri in spremeni--------------------------------------------------------------------------------------------
	
	//preveri vzame seznam in lokacijo jajcka, barva igralca; preveri bele crne sosede
	public static boolean preveri(Polje[] seznam, int i, Igralec igralec) { 
		Igralec nasprotnik = igralec.nasprotnik();
		// i - oznaèuje mesto poteze. 
		// Preverimo, da je vsaj en element enak nasprotnemu znakcu.
		int d = seznam.length;
		
		if (i + 1 < d) {
			if (seznam[i + 1].toString() == nasprotnik.toString()) {  //Èe je i+1 element ustrezen 
				for (int j = i + 1; j < seznam.length; j++) { 
					if (seznam[j].toString() == nasprotnik.toString()) continue; 
					else if (seznam[j] == Polje.PRAZNO) return false; 
					else return true; 
					} 
				return false;
				} 
		}
		if (i > 0 && i <= d) {
			if (seznam[i - 1].toString() == nasprotnik.toString()) { 
				for (int j = i-1; j >= 0; j--) { 
					if (seznam[j].toString() == nasprotnik.toString()) continue; 
					else if (seznam[j] == Polje.PRAZNO) return false; 
					else return true; 
					} 
				return false;
				} 
		}
		return false;
		}
	
	
	public static Polje spremeniPolje(Igralec igralec) {
		String i = igralec.toString();
		if (i == "B") return Polje.BEL;
		else if (i == "C") return Polje.CRN;
		else return Polje.PRAZNO;
	}
	
	public static Polje[] spremeni(Polje[] seznam, int i,Igralec igralec) { 
		Igralec nasprotnik = igralec.nasprotnik();
		// i - oznaèuje mesto poteze. 
		// Preverimo, da je vsaj en element enak nasprotnemu znakcu.
		int d = seznam.length;
		
		Polje polje = spremeniPolje(igralec);
		
		if (i + 1 < d) {
			if (seznam[i + 1].toString() == nasprotnik.toString()) {  //Èe je i+1 element ustrezen 
				seznam[i] = polje;
				for (int j = i + 1; j < d; j++) { 
					if (seznam[j].toString() == nasprotnik.toString()) seznam[j] = polje; 
					else return seznam;
					}
				return seznam;
			}
		}
		if (i > 0) {
			if (seznam[i - 1].toString() == nasprotnik.toString()) { 
				seznam[i] = polje;
				for (int j = i-1; j >= 0; j--) { 
					if (seznam[j].toString() == nasprotnik.toString()) seznam[j] = polje;
					else return seznam;
					} 
				return seznam;
				} 
		}
		return seznam;
		}
	
	
	//-----------------------------------------------------------------------------------------------------
	//vlozi------------------------------------------------------------------------------------------------
	
	public static Polje[][] vloziVrstico(int x, int y, Polje[] vrstica, Polje[][] plosca) {
		plosca[x] = vrstica;
		return plosca;
	}
	
	public static Polje[][] vloziStolpec(int x, int y, Polje[] stolpec, Polje[][] plosca) {
		for (int i = 0; i < 8; ++i) {
			plosca[i][y] = stolpec[i];
		}
		return plosca;
	}

	
	public static Polje[][] vloziDiagonaloL(int x, int y, Polje[] diagonala, Polje[][] plosca) {
		int st = x + y;
		int zacetekX = Math.max(0, st - 7); 
		int zacetekY= Math.min(7, st); 
		int dolzina = Math.min(st + 1, 15 - st);
		for (int i = 0; i <  dolzina; ++i) {
			plosca[zacetekX + i][zacetekY - i] = diagonala[i];
		}
		return plosca;
	}
	
	public static Polje[][] vloziDiagonaloS(int x0, int y0, Polje[] diagonala, Polje[][] plosca) {
		int x = 7 - x0;
		int y = y0;
		plosca = zarotirajLevo(plosca);
		vloziDiagonaloL(x, y, diagonala, plosca);
		return zarotirajDesno(plosca);
	}
	
	//-------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------
	public Polje[][] kopijaPlosce(Polje[][] plosca) {
		Polje[][] kopija = new Polje[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				kopija[i][j] = plosca[i][j];
			}
		}
		return kopija;
	}
	
	public boolean odigraj(Poteza poteza) {
		int x = poteza.getX(); //vrstica
		int y = poteza.getY(); //stolpec
		
		//ce je polje ze zasedeno
		if (plosca[x][y] != Polje.PRAZNO) return false;
		
		//ce je polje prosto 
		Polje[] stolpec = getStolpec(x, y, plosca);
		Polje[] vrstica = getVrstica(x, y, plosca);
		Polje[] diagonalaL = getDiagonalaL(x, y, plosca);
		Polje[] diagonalaS = getDiagonalaS(x, y, plosca);
		
		int mestoStolpec = x;
		int mestoVrstica = y;
		int mestoDiagonalaL = mestoDiagonalaL(x, y, plosca);
		int mestoDiagonalaS = mestoDiagonalaS(x, y, plosca);
		
		boolean s = preveri(stolpec, mestoStolpec, naPotezi);
		boolean v = preveri(vrstica, mestoVrstica, naPotezi);
		boolean liha = preveri(diagonalaL, mestoDiagonalaL, naPotezi);
		boolean soda = preveri(diagonalaS, mestoDiagonalaS, naPotezi);
		
		if (!s && !v && !liha && !soda) return false;
		
		zadnjaPlosca = kopijaPlosce(plosca);
		nazaj = true;
		
		if (s) plosca = vloziStolpec(x, y, spremeni(stolpec, mestoStolpec, naPotezi), plosca);
		if (v) plosca =  vloziVrstico(x, y, spremeni(vrstica, mestoVrstica, naPotezi), plosca);
		if (liha) plosca =  vloziDiagonaloL(x, y, spremeni(diagonalaL, mestoDiagonalaL, naPotezi), plosca);
		if (soda) plosca =  vloziDiagonaloS(x, y, spremeni(diagonalaS, mestoDiagonalaS, naPotezi), plosca);
		
		naPotezi = naPotezi.nasprotnik();
		
		return true; //true pomeni, da je odrigral potezo
		
		
	}
	
//-----------------------------------------------------------------------------------------------------
//MOZNE POTEZE----------------------------------------------------------------------------------------------
	
	//vsa mozna mesta za potezo 
	public Poteza[] moznePoteze() {
		Poteza[] poteze = new Poteza[steviloPraznihMest()];
		int stevec = 0;
		for (int y = 0; y < 8; ++y) {
			for (int x = 0; x < 8; ++x) {
				if (plosca[x][y] == Polje.PRAZNO) {
					Polje[] stolpec = getStolpec(x, y, plosca);
					Polje[] vrstica = getVrstica(x, y, plosca);
					Polje[] diagonalaL = getDiagonalaL(x, y, plosca);
					Polje[] diagonalaS = getDiagonalaS(x, y, plosca);
					
					int mestoStolpec = x;
					int mestoVrstica = y;
					int mestoDiagonalaL = mestoDiagonalaL(x, y, plosca);
					int mestoDiagonalaS = mestoDiagonalaS(x, y, plosca);
					
					boolean s = preveri(stolpec, mestoStolpec, naPotezi);
					boolean v = preveri(vrstica, mestoVrstica, naPotezi);
					boolean liha = preveri(diagonalaL, mestoDiagonalaL, naPotezi);
					boolean soda = preveri(diagonalaS, mestoDiagonalaS, naPotezi);
					
					if (s || v || soda || liha) {
						poteze[stevec] = new Poteza(x, y);
						++stevec;
					}
				}
			}
		}
		return poteze;
	}
	
	public int steviloMoznihPotez() {
		Poteza[] moznePoteze = moznePoteze();
		for (int i = 0; i < moznePoteze.length; ++i) {
			if (moznePoteze[i] == null) return i;
		}
		return moznePoteze.length;
	}
	
	
//-----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//razveljavi zadnjo potezo

	public boolean razveljaviPotezo() {
		if (nazaj) {
			plosca = kopijaPlosce(zadnjaPlosca);
			zadnjaPlosca = praznaPlosca;
			naPotezi = naPotezi.nasprotnik();
			nazaj = false;
			return true;
		}
		return false;
	}
	
//-----------------------------------------------------------------------------------------------------
//STANJE-----------------------------------------------------------------------------------------------
//preverjanje stanja : ali je konec igre, kdo je zmagal, stetje zetonov
	
	public int[] stanjeZetonov(){
		int crni = 0;
		int beli = 0;
		int[] stanje = new int[2];
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				Polje element = plosca[x][y];
				String elt = element.toString();
				if (elt == "C") ++crni;
				if(elt == "B") ++beli;
			}
		}
		stanje[0] = crni;
		stanje[1] = beli;
		return stanje;
	}
	
	public int steviloPraznihMest () {
		int[] zetoni = stanjeZetonov();
		int beli = zetoni[1];
		int crni = zetoni[0];
		return (64 - beli - crni);
	}
	
	public Igralec vodilniIgralec() {
		int[] zetoni = stanjeZetonov();
		int beli = zetoni[1];
		int crni = zetoni[0];
		if (beli > crni) return Igralec.BEL;
		if(crni > beli) return Igralec.CRN;
		else return null;
	}
	
	
	public Stanje stanjeIgre() {
		//pogledamo ce ima igralec ki je na vrsti kaksno mozno potezo
		int i = steviloMoznihPotez();
		int n = steviloMoznihPotez();
		Igralec vodilni = vodilniIgralec();
		if ((n == 0 && i == 0) || steviloPraznihMest() == 0){ // ce noben igralec nima vec moznih potez ALI ni vec praznih mest je konec
				if (vodilni == Igralec.BEL) return Stanje.ZMAGA_BEL;
				else if (vodilni == Igralec.CRN) return Stanje.ZMAGA_CRN;
				else if (vodilni == null) return Stanje.NEODLOCENO;
			}
		//ce igra se ni koncana
		return Stanje.V_TEKU;
	}
}

