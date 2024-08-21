package main.logic.combos;

import main.logic.Wuerfel;

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
