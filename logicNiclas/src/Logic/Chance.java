package Logic;

public class Chance extends Combos{

	public Chance() {
		super("Chance");
	}

	@Override
	public void berechnePunkte() {
		int ergebnis = Wuerfel.ermittleAugenzahl(Wuerfel.getAnzahlWerte());
		moeglicheComboPunkte.put(getComboName(), ergebnis);
	}
	
}
