package main.logic.combos;

import main.logic.Wuerfel;

public class DreierPash extends Combos{

	public DreierPash() {
		super("DreierPash");
		
	}

	@Override
	public void berechnePunkte() { 
		//wenn eine Zahl mind. 3x Vorkommt--> Punkte an Map übergeben ,sonst Punkte = 0 gesetzt
		if(umgewandeltZuListe().contains(3) || umgewandeltZuListe().contains(4) || umgewandeltZuListe().contains(5) || umgewandeltZuListe().contains(6)) {
			int ergebnis = Wuerfel.getAugenzahl();
			moeglicheComboPunkte.put(getComboName(), ergebnis);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);
	}
}
