package main.logic.combos;

import main.logic.Combos;
import main.logic.Dice;

public class KleineStrasse extends Combos{

	public KleineStrasse() {
		super("KleineStrasse");
	}

	@Override
	public void calculatePoints() {
		
		int counter = 0;		

		    for (int i = 1; i <= 6; i++) { 
		        if (Dice.getCountingArray()[i] > 0) { 
		            counter++;
		            if (counter >= 4) {     // Wenn vier aufeinanderfolgende Zahlen vorhanden sind
		                possibleComboPoints.put(getComboName(), 30);
		                System.out.println("Combo: Kleine Straße mit 30 Punkten");
		                return;
		            }
		        } else {
		        	System.out.println("Combo: keine Kleine Straße");
		            counter = 0;  // Wenn eine Zahl nicht vorhanden ist, setze den Counter zurück
		        }
		    }
		    possibleComboPoints.put(getComboName(), 0);
	}

}
