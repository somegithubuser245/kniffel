package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class Vierer extends Combos{

	public Vierer() {
		super("Vierer");
	}

	@Override
	public void berechnePunkte() {
		int index = 4;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}

}
