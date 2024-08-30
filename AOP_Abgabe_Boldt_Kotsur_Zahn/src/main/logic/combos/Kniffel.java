package main.logic.combos;

import main.logic.Combos;

public class Kniffel extends Combos{

	public Kniffel() {
		super("Kniffel");
	}

	@Override
	public void calculatePoints() {
		if(transformedToList().contains(5)) {
			System.out.println("Combo: Kniffel<3 mit 50 Punkten");
			possibleComboPoints.put(getComboName(), 50);
			
		}
		else {
			System.out.println("Combo: kein Kniffel");
			possibleComboPoints.put(getComboName(), 0);
		}
		
	}
}
