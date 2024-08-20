package Logic;

public class Kniffel extends Combos{

	public Kniffel() {
		super("Kniffel");
	}

	@Override
	public void berechnePunkte() {
		int[]kniffel = {0,1,1,1,1,1,1};
		if(Wuerfel.getAnzahlWerte() == kniffel) {
			moeglicheComboPunkte.put(getComboName(), 30);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);
		
	}
}
