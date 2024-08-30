package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Einser extends Combos {

	public Einser() {
		super("Einser");		
	}

	@Override
	public void calculatePoints() {
		int index = 1;				
		
		int result = index * Dice.getCountingArray()[index];
		possibleComboPoints.put(getComboName(), result);
		
	}

}
