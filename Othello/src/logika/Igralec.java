package logika;

public enum Igralec {

	BEL, CRN;

	public Igralec nasprotnik() {
		return (this == BEL ? CRN : BEL);
	}

	public Polje getPolje() {
		return (this == BEL ? Polje.BEL : Polje.CRN);
	}
	
	@Override
	public String toString() {
		return (this == BEL ? "B" : "C");
	}
	
}
