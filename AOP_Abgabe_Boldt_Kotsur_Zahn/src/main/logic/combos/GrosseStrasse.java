package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;
import java.util.Arrays;

public class GrosseStrasse extends Combos{

	public GrosseStrasse() {
		super("Strasse");
	} 

	@Override
	public void berechnePunkte() {
		int[]GrosseStrasse1 = {0,1,1,1,1,1,0};
		int[]GrosseStrasse2 = {0,0,1,1,1,1,1};
		if(Arrays.equals(GrosseStrasse1, Wuerfel.getAnzahlWerte()) || Arrays.equals(GrosseStrasse2, Wuerfel.getAnzahlWerte())) {
			moeglicheComboPunkte.put(getComboName(), 40);
			System.out.println("Combo: Große Straße mit 40 Punkten");
		}
		else {
			System.out.println("Combo: keine Große Straße");
			moeglicheComboPunkte.put(getComboName(), 0);	
		}
	}
}
