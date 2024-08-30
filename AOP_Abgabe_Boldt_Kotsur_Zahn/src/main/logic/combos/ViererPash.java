package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class ViererPash extends Combos{

	public ViererPash() {
		super("ViererPash");
	}

	@Override
	public void calculatePoints() {
			//wenn eine Zahl mind. 4x Vorkommt--> Punkte an Map übergeben ,sonst Punkte = 0 gesetzt
			if(transformedToList().contains(4) || transformedToList().contains(5)||transformedToList().contains(6)) {
				int result = Dice.getSum();
				System.out.println("Combo: Vierer-Pasch mit "+result+" Punkten");
				possibleComboPoints.put(getComboName(), result);
				
			}
			else {
				System.out.println("Combo: kein Vierer-Pasch");
				possibleComboPoints.put(getComboName(), 0);
			}
		
	}
		
}
