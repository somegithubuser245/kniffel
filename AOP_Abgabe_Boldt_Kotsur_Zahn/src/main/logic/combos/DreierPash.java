package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class DreierPash extends Combos{

	public DreierPash() {
		super("DreierPash");
		
	}

	@Override
	public void calculatePoints() { 
		//wenn eine Zahl mind. 3x Vorkommt--> Punkte an Map übergeben ,sonst Punkte = 0 gesetzt
		if(transformedToList().contains(3) || transformedToList().contains(4) || transformedToList().contains(5) || transformedToList().contains(6)) {
			//TODO darf nur die augen des dreier Pasches beinhalten!!!
			int result = Dice.getSum();
			System.out.println("Combo: Dreier-Pasch mit "+result+" Punkten");
			possibleComboPoints.put(getComboName(), result);
			
			
			
		}
		else {
			System.out.println("Combo: kein Dreier-Pasch");
			possibleComboPoints.put(getComboName(), 0);
		}
		
	}
}
