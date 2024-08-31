package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class Chance extends Combos{

	public Chance() {
		super("Chance");
	}

	@Override 
	public void calculatePoints() {
		//Addiert alle Aufgenzahlen
		int result = Dice.getSum();
		System.out.println("Combo: Chance mit "+result+" Punkten");

		possibleComboPoints.put(getComboName(), result);
		
		
	}
	
}
