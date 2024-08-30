package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Vierer extends Combos{

	public Vierer() {
		super("Vierer");
	}

	@Override
	public void calculatePoints() {
		int index = 4;				
		
		int result = index * Dice.getCountingArray()[index];
		possibleComboPoints.put(getComboName(), result);
		
	}

}
