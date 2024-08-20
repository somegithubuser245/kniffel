package Logic;

public class Zahlen extends Combos{
	public Zahlen() {
		super("Zahlen");
	}

	@Override
	public void berechnePunkte(/*Playerinput*/) {
		// es müssen alle berechnet werden--> Neue Klassen erstellen 1er...
		int index = 1/*Playerinput*/;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}
}
