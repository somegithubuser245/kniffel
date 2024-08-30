package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Dreier extends Combos{

	public Dreier() {
		super("Dreier");
	}

	@Override
	public void calculatePoints() {
		int index = 3;				
		
		int result = index * Dice.getCountingArray()[index];
		possibleComboPoints.put(getComboName(), result);
		if(result < 0) {
			System.out.println("Combo: Dreier mit "+result+" Punkten");
		}
	}
}
