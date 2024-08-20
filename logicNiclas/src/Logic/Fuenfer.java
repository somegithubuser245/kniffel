package Logic;

public class Fuenfer extends Combos {

	public Fuenfer() {
		super("Fuenfer");
	}

	@Override
	public void berechnePunkte() {
		int index = 5;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}

}
