package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class Zweier extends Combos {

	public Zweier() {
		super("Zweier");
	}

	@Override
	public void berechnePunkte() {
		int index = 2;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}

}
