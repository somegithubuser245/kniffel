package main.logic.combos;

import main.logic.Wuerfel;

public class GrosseStrasse extends Combos{

	public GrosseStrasse() {
		super("Strasse");
	} 

	@Override
	public void berechnePunkte() {
		int[]GrosseStrasse1 = {0,1,1,1,1,1,0};
		int[]GrosseStrasse2 = {0,0,1,1,1,1,1};
		if( (Wuerfel.getAnzahlWerte() == GrosseStrasse1) || (Wuerfel.getAnzahlWerte() == GrosseStrasse2)) {
			moeglicheComboPunkte.put(getComboName(), 40);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);	
	}
}
