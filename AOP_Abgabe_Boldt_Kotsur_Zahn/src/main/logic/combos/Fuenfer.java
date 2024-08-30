package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Fuenfer extends Combos {

	public Fuenfer() {
		super("Fuenfer");
	}

	@Override
	public void calculatePoints() {
		int index = 5;				
		
		int result = index * Dice.getCountingArray()[index];
		possibleComboPoints.put(getComboName(), result);
		
	}

}
