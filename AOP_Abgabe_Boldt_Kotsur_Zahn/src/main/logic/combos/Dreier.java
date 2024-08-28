package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class Dreier extends Combos{

	public Dreier() {
		super("Dreier");
	}

	@Override
	public void berechnePunkte() {
		int index = 3;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		if(ergebnis < 0) {
			System.out.println("Combo: Dreier mit "+ergebnis+" Punkten");
		}
	}
}
