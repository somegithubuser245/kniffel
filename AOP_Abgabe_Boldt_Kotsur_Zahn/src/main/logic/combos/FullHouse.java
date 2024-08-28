package main.logic.combos;

import main.logic.Combos;

public class FullHouse extends Combos{

	public FullHouse() {
		super("FullHouse");
	}

	@Override
	public void berechnePunkte() {
		if(umgewandeltZuListe().contains(2) && umgewandeltZuListe().contains(3)) {
			System.out.println("Combo: Full-House mit 25 Punkten");
			moeglicheComboPunkte.put(getComboName(), 25);
			
			
			
		}
		else {
			System.out.println("Combo: kein Full House");
			moeglicheComboPunkte.put(getComboName(), 0);
		}
	}
}
