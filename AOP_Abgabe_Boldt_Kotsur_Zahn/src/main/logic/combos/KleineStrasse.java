package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class KleineStrasse extends Combos{

	public KleineStrasse() {
		super("KleineStrasse");
	}

	@Override
	public void berechnePunkte() {
		
		int counter = 0;		

		    for (int i = 1; i <= 6; i++) { 
		        if (Wuerfel.getAnzahlWerte()[i] > 0) { 
		            counter++;
		            if (counter >= 4) {     // Wenn vier aufeinanderfolgende Zahlen vorhanden sind
		                moeglicheComboPunkte.put(getComboName(), 30);
		                System.out.println("Combo: Kleine Straße mit 30 Punkten");
		                return;
		            }
		        } else {
		        	System.out.println("Combo: keine Kleine Straße");
		            counter = 0;  // Wenn eine Zahl nicht vorhanden ist, setze den Counter zurück
		        }
		    }
		    moeglicheComboPunkte.put(getComboName(), 0);
	}

}
