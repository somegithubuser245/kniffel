package main.logic.combos;

import main.logic.Wuerfel;

public class Sechser extends Combos{

	public Sechser() {
		super("sechser");
	}

	@Override
	public void berechnePunkte() {
		int index = 6;				
		
		int ergebnis = index * Wuerfel.getAnzahlWerte()[index];
		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
	}

}
