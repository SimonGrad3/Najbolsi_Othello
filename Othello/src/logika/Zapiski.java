package logika;

public class Zapiski {

	
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
			
			String[][] p = {
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, "B", "C", null, null, null},
					{null, null, null, "C", "B", null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					};
			
			izpisiPlosco(p, "B");

			p = akcija(3, 2, "C", p);
			p = akcija(2, 4, "B", p);		
			p = akcija(2, 5, "C", p);
//			p = akcija(1, 1, "B", p);
//			p = akcija(4, 5, "C", p);
//			p = akcija(5, 5, "B", p);
//			p = akcija(5, 4, "C", p);
//			p = akcija(3, 5, "B", p);
//			p = akcija(3, 6, "C", p);
//			p = akcija(2, 5, "B", p);
//			p = akcija(0, 0, "C", p);
//			p = akcija(3, 1, "B", p);
//			p = akcija(3, 0, "C", p);
//			p = akcija(4, 2, "B", p);
//			p = akcija(6, 5, "C", p);
//			p = akcija(0, 2, "B", p);
//			p = akcija(5, 3, "C", p);
//			p = akcija(3, 7, "B", p);
//			p = akcija(1, 5, "C", p);
//			p = akcija(5, 2, "B", p);
//			p = akcija(6, 1, "C", p);
//			p = akcija(1, 0, "B", p);
//			p = akcija(2, 0, "C", p);
//			p = akcija(4, 0, "B", p);
//			p = akcija(5, 0, "C", p);
//			p = akcija(6, 2, "B", p);
//			p = akcija(2, 1, "C", p);
//			p = akcija(6, 0, "B", p);
//			p = akcija(7, 0, "C", p);
//			p = akcija(6, 3, "B", p);
//			p = akcija(0, 1, "C", p);
//			p = akcija(4, 1, "B", p);
//			p = akcija(5, 1, "C", p);
//			p = akcija(2, 3, "B", p);
//			p = akcija(7, 1, "C", p);
//			p = akcija(7, 2, "B", p);
//			p = akcija(0, 3, "C", p);
//			p = akcija(1, 4, "B", p);
//			p = akcija(1, 3, "C", p);
//			p = akcija(2, 4, "B", p);
//			p = akcija(7, 3, "C", p);
//			p = akcija(0, 4, "B", p);
//			p = akcija(0, 5, "C", p);
//			p = akcija(6, 4, "B", p);
//			p = akcija(7, 4, "C", p);
//			p = akcija(7, 5, "B", p);
//			p = akcija(1, 6, "C", p);
//			p = akcija(0, 6, "B", p);
//			p = akcija(4, 6, "C", p);
//			p = akcija(2, 6, "B", p);
//			p = akcija(5, 6, "C", p);
//			p = akcija(6, 6, "B", p);
//			p = akcija(7, 6, "C", p);
			
		}
		
		protected String crn;
		protected String bel;
		protected String igralec;
		protected String[][] igra;
		protected String nasprotnik;
		protected String stanje;
		protected String[][] zadnjaPlosca;
				
		// x je vrstica 
		// y je stolpec 
		
		public Igra() {
			this.igra  = new String[8][8];
			this.stanje = null;
			this.crn = "C";
			this.bel = "B";
			igra[3][3] = bel;
			igra[4][4] = bel;
			igra[3][4] = crn;
			igra[4][3] = crn;
			this.igralec = crn;
			//this.nasprotnik = nasprotnik();
			this.zadnjaPlosca = null;
		}
		
		public static String nasprotnik(String igralec) {
			if (igralec == "B") return "C";
			return "B";
		}
		
		//-----------------------------------------------------------------------------------------------------
		//funkcije za izpis ----------------------------------------------------------------------------------
		
		public static void izpisiPlosco(String[][] plosca, String igralec) {
			String nasprotnik = nasprotnik(igralec);
			
			System.out.println();
			izpisiStanjeIgre(plosca, igralec);
			System.out.println();
			System.out.print("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
			for (int i = 0; i < 8; ++i) {
				System.out.println();
				System.out.print("------------------------------------");
				System.out.println();
				System.out.print(" " + i + " | ");
				for (int j = 0; j < 8; ++j) {
					if (plosca[i][j] == null) {
						System.out.print(" "+ " | ");;
					}
					else {
						System.out.print(plosca[i][j]+ " | ");;
					}
				}
			}
			System.out.println();
			
			izpisiStanjeZetonov(plosca);
			izpisiMoznePoteze(plosca, nasprotnik);
			System.out.println();
			System.out.println("#########################################");
		}
		
		public static void izpisiSeznam(String[] t) {
			System.out.println();
			for (int i = 0; i < t.length; ++i) {
				System.out.print(t[i]);
			}
			System.out.println();
		}
		
		public static void izpisiStanjeIgre(String[][] plosca, String igralec) {
			String nasprotnik = nasprotnik(igralec);
			String stanje = stanjeIgre(plosca, igralec);
			System.out.println();
			System.out.println("Odigral je " + igralec +  ". ");
			
			if (stanje == null) System.out.println("Na vrsti je " + nasprotnik + ". ");
			else if (stanje.equals("Z")) System.out.println("Igra je koncana. Igralca sta izenačena.");
			else if (stanje.equals("ZC")) System.out.println("Igra je koncana. Zmagal je črni.");
			else if (stanje.equals("ZB")) System.out.println("Igra je koncana. Zmagal je beli.");
		}
		
		
		public static void izpisiStanjeZetonov(String[][] plosca) {
			int[] t = stanjeZetonov(plosca);
			System.out.println();
			System.out.println("Crni: " + t[0]);
			System.out.println("Beli: " + t[1]);
		}
		
		public static void izpisiMoznePoteze(String[][] plosca, String igralec) {
			Poteza[] p = moznePoteze(plosca, igralec);
			int d = steviloMoznihPotez(plosca, igralec);
			System.out.println();
			System.out.println("Mozne poteze za " + igralec + ": ");
			if (d == 0) System.out.println("Ni možnih potez. Igre je konec.");
			else {
				for (int i = 0; i < d; ++i) {
					System.out.println("- (" + p[i].getX() + ", " + p[i].getY() + ")");
				}
			}
		}

		//odigra potezo v main
		public static String[][] akcija(int x, int y, String igralec, String[][] plosca) {
			Poteza p = new Poteza(x, y);
			String[][] plosca1 = odigraj(p, plosca, igralec);
			izpisiPlosco(plosca1, igralec);
			return plosca1;
		}
		
		
		//-----------------------------------------------------------------------------------------------------
		//funkcije get ----------------------------------------------------------------------------------------
		
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
		
		//soda diagonala
		public static String[] getDiagonalaS(int x0, int y0, String[][] plosca) { 
			int x = 7 - x0;
			int y = y0;
			String[][] nova = zarotirajLevo(plosca);
			return getDiagonalaL(x, y, nova);
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
		
		//------------------------------------------------------------------------------------------------------
		//mesto jajcka -----------------------------------------------------------------------------------------
		
		public static int mestoDiagonalaL(int x, int y, String[][] plosca) {
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
		
		
		public static int mestoDiagonalaS(int x, int y, String[][] plosca) {
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
		public static boolean preveri(String[] seznam, int i, String igralec) { 
			String nasprotnik = nasprotnik(igralec);
			// i - oznaèuje mesto poteze. 
			// Preverimo, da je vsaj en element enak nasprotnemu znakcu.
			int d = seznam.length;
			
			//ce ima seznam na vseh mestih null
			if (seznam == new String[d]) return false;
			
			if (i + 1 < d) {
				if (seznam[i + 1] == nasprotnik) {  //Èe je i+1 element ustrezen 
					for (int j = i + 1; j < seznam.length; j++) { 
						if (seznam[j] == nasprotnik) continue; 
						else if (seznam[j] == null) return false; 
						else return true; 
						} 
					return false;
					} 
			}
			if (i > 0 && i <= d) {
				if (seznam[i - 1] == nasprotnik) { 
					for (int j = i-1; j >= 0; j--) { 
						if (seznam[j] == nasprotnik) continue; 
						else if (seznam[j] == null) return false; 
						else return true; 
						} 
					return false;
					} 
			}
			return false;
			}
		
		public static String[] spremeni(String[] seznam, int i,String igralec) { 
			String nasprotnik = nasprotnik(igralec);
			// i - oznaèuje mesto poteze. 
			// Preverimo, da je vsaj en element enak nasprotnemu znakcu.
			int d = seznam.length;
			
			//ce ima seznam na vseh mestih null
			if (seznam == new String[d]) return seznam;
			
			if (i + 1 < d) {
				if (seznam[i + 1] == nasprotnik) {  //Èe je i+1 element ustrezen 
					seznam[i] = igralec;
					for (int j = i + 1; j < d; j++) { 
						if (seznam[j] == nasprotnik) seznam[j] = igralec; 
						else return seznam;
						}
					return seznam;
				}
			}
			if (i > 0) {
				if (seznam[i - 1] == nasprotnik) { 
					seznam[i] = igralec;
					for (int j = i-1; j >= 0; j--) { 
						if (seznam[j] == nasprotnik) seznam[j] = igralec;
						else return seznam;
						} 
					return seznam;
					} 
			}
			return seznam;
			}
		
		
		//-----------------------------------------------------------------------------------------------------
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
		//-------------------------------------------------------------------------------------------------------
		public static String[][] odigraj(Poteza poteza, String[][] igra, String igralec) {
			//String nasprotnik = nasprotnik(igralec);
			
			int x = poteza.getX(); //vrstica
			int y = poteza.getY(); //stolpec
			
			//ce je prazno mesto 
			if (igra[x][y] != null) return igra; //return false
			String[] stolpec = getStolpec(x, y, igra);
			String[] vrstica = getVrstica(x, y, igra);
			String[] diagonalaL = getDiagonalaL(x, y, igra);
			String[] diagonalaS = getDiagonalaS(x, y, igra);
			
			int mestoStolpec = x;
			int mestoVrstica = y;
			int mestoDiagonalaL = mestoDiagonalaL(x, y, igra);
			int mestoDiagonalaS = mestoDiagonalaS(x, y, igra);
			
			boolean s = preveri(stolpec, mestoStolpec, igralec);
			boolean v = preveri(vrstica, mestoVrstica, igralec);
			boolean liha = preveri(diagonalaL, mestoDiagonalaL, igralec);
			boolean soda = preveri(diagonalaS, mestoDiagonalaS, igralec);
			
			if (!s && !v && !liha && !soda ) return igra; // return false
			
			//this.zadnjaPlosca = igra;
			
			if (s) igra = vloziStolpec(x, y, spremeni(stolpec, mestoStolpec, igralec), igra);
			if (v) igra =  vloziVrstico(x, y, spremeni(vrstica, mestoVrstica, igralec), igra);
			if (liha) igra =  vloziDiagonaloL(x, y, spremeni(diagonalaL, mestoDiagonalaL, igralec), igra);
			if (soda) igra =  vloziDiagonaloS(x, y, spremeni(diagonalaS, mestoDiagonalaS, igralec), igra);
			
			//igralec = nasprotnik();
			//return true;
			
			return igra;
		}


	//-----------------------------------------------------------------------------------------------------
	//MOZNE POTEZE----------------------------------------------------------------------------------------------
		
		//vsa mozna mesta za potezo 
		public static Poteza[] moznePoteze(String[][] plosca, String igralec) {
			Poteza[] poteze = new Poteza[steviloPraznihMest(plosca)];
			int stevec = 0;
			for (int y = 0; y < 8; ++y) {
				for (int x = 0; x < 8; ++x) {
					if (plosca[x][y] == null) {
						String[] stolpec = getStolpec(x, y, plosca);
						String[] vrstica = getVrstica(x, y, plosca);
						String[] diagonalaL = getDiagonalaL(x, y, plosca);
						String[] diagonalaS = getDiagonalaS(x, y, plosca);
						
						int mestoStolpec = x;
						int mestoVrstica = y;
						int mestoDiagonalaL = mestoDiagonalaL(x, y, plosca);
						int mestoDiagonalaS = mestoDiagonalaS(x, y, plosca);
						
						boolean s = preveri(stolpec, mestoStolpec, igralec);
						boolean v = preveri(vrstica, mestoVrstica, igralec);
						boolean liha = preveri(diagonalaL, mestoDiagonalaL, igralec);
						boolean soda = preveri(diagonalaS, mestoDiagonalaS, igralec);
						
						if (s || v || soda || liha) {
							poteze[stevec] = new Poteza(x, y);
							++stevec;
						}
					}
				}
			}
			return poteze;
		}
		
		public static int steviloMoznihPotez(String[][] plosca, String igralec) {
			Poteza[] moznePoteze = moznePoteze(plosca, igralec);
			for (int i = 0; i < moznePoteze.length; ++i) {
				if (moznePoteze[i] == null) return i;
			}
			return moznePoteze.length;
		}
		
		
	//-----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
		
	//razveljavi zadnjo potezo

		
		
	//-----------------------------------------------------------------------------------------------------
	//STANJE-----------------------------------------------------------------------------------------------
	//preverjanje stanja : ali je konec igre, kdo je zmagal, stetje zetonov
		
		public static int[] stanjeZetonov(String[][] plosca){
			int crni = 0;
			int beli = 0;
			int[] stanje = new int[2];
			for (int x = 0; x < 8; ++x) {
				for (int y = 0; y < 8; ++y) {
					String elt = plosca[x][y];
					if (elt == "C") ++crni;
					if(elt == "B") ++beli;
				}
			}
			stanje[0] = crni;
			stanje[1] = beli;
			return stanje;
		}
		
		public static int steviloPraznihMest (String[][] plosca) {
			int[] zetoni = stanjeZetonov(plosca);
			int beli = zetoni[1];
			int crni = zetoni[0];
			return (64 - beli - crni);
		}
		
		public static String vodilniIgralec(String[][] plosca) {
			int[] zetoni = stanjeZetonov(plosca);
			int beli = zetoni[1];
			int crni = zetoni[0];
			if (beli > crni) return "B";
			if(crni > beli) return "C";
			else return null;
		}
		
		
		public static String stanjeIgre(String[][] plosca, String igralec) {
			//pogledamo ce ima igralec ki je na vrsti kaksno mozno potezo
			String nasprotnik = nasprotnik(igralec);
			int i = steviloMoznihPotez(plosca, igralec);
			int n = steviloMoznihPotez(plosca, nasprotnik);
			String vodilni = vodilniIgralec(plosca);
			if (n == 0){
				if (i == 0) {
					return ("Z" + vodilni);
					//ZB zmaga belega
					//ZC zmaga crnega
					//Z izenaceno, konec igre ... Z + null
				}
				else {
					return ("Z" + igralec);
				}
			}
			//ce ima igralec se na voljo poteze, igra se ni koncana
			return null;
		}
		
		
		
	}
}
