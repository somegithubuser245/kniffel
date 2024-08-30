package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Zweier extends Combos {

	public Zweier() {
		super("Zweier");
	}

	@Override
	public void calculatePoints() {
		int index = 2;				
		
		int result = index * Dice.getCountingArray()[index];
		possibleComboPoints.put(getComboName(), result);
		
	}

}
