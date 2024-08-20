package Logic;

public class Zahlen extends Combos{
	public Zahlen() {
		super("Zahlen");
	}

	@Override
	public void berechnePunkte(/*Playerinput*/) {											//darf ic das static machen???????????
		// sonst kann contains nicht genutzt werden und das macht alles komplizierter
		
		int index = 1/*Playerinput*/;
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}
}
