package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class KleineStrasse extends Combos{

	public KleineStrasse() {
		super("KleineStrasse");
	}

	@Override
	public void berechnePunkte() {
		int[]KleineStrasse1 = {0,1,1,1,1,0,0};
		int[]KleineStrasse2 = {0,0,1,1,1,1,0};
		int[]KleineStrasse3 = {0,0,0,1,1,1,1};
		if((Wuerfel.getAnzahlWerte()== KleineStrasse1) || (Wuerfel.getAnzahlWerte()== KleineStrasse2) || (Wuerfel.getAnzahlWerte()== KleineStrasse3)) {
			moeglicheComboPunkte.put(getComboName(), 30);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);
	}

}
