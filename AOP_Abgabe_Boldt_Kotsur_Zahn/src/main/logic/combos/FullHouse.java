package main.logic.combos;

import main.logic.Combos;

public class FullHouse extends Combos{

	public FullHouse() {
		super("FullHouse");
	}

	@Override
	public void calculatePoints() {
		if(transformedToList().contains(2) && transformedToList().contains(3)) {
			System.out.println("Combo: Full-House mit 25 Punkten");
			possibleComboPoints.put(getComboName(), 25);
			
			
			
		}
		else {
			System.out.println("Combo: kein Full House");
			possibleComboPoints.put(getComboName(), 0);
		}
	}
}
