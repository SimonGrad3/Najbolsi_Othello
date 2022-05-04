package logika;

public enum Polje {
	BEL, CRN, PRAZNO;
	
	@Override
	public String toString() {
		if (this == BEL) return "B";
		else if (this == CRN) return "C";
		return null;
	}
	

	
}
