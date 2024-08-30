package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Sechser extends Combos{

	public Sechser() {
		super("sechser");
	}

	@Override
	public void calculatePoints() {
		int index = 6;				
		
		int result = index * Dice.getCountingArray()[index];
		possibleComboPoints.put(getComboName(), result);
		
	}

}
