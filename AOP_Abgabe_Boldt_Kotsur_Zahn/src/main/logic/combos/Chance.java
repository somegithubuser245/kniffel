package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class Chance extends Combos{

	public Chance() {
		super("Chance");
	}

	@Override 
	public void berechnePunkte() {
		int ergebnis = Wuerfel.getAugenzahl();
		System.out.println("Combo: Chance mit "+ergebnis+" Punkten");

		moeglicheComboPunkte.put(getComboName(), ergebnis);
		
		
	}
	
}
