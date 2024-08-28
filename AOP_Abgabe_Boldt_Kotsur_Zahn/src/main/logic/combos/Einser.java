package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class Einser extends Combos {

	public Einser() {
		super("Einser");		
	}

	@Override
	public void berechnePunkte() {
		int index = 1;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}

}
