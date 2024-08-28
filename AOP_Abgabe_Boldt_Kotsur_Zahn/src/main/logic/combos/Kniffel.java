package main.logic.combos;

import main.logic.Combos;

public class Kniffel extends Combos{

	public Kniffel() {
		super("Kniffel");
	}

	@Override
	public void berechnePunkte() {
		if(umgewandeltZuListe().contains(5)) {
			System.out.println("Combo: Kniffel<3 mit 50 Punkten");
			moeglicheComboPunkte.put(getComboName(), 50);
			
		}
		else {
			System.out.println("Combo: kein Kniffel");
			moeglicheComboPunkte.put(getComboName(), 0);
		}
		
	}
}
