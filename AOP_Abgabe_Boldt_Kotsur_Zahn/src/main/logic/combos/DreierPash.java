package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class DreierPash extends Combos{

	public DreierPash() {
		super("DreierPash");
		
	}

	@Override
	public void berechnePunkte() { 
		//wenn eine Zahl mind. 3x Vorkommt--> Punkte an Map übergeben ,sonst Punkte = 0 gesetzt
		if(umgewandeltZuListe().contains(3) || umgewandeltZuListe().contains(4) || umgewandeltZuListe().contains(5) || umgewandeltZuListe().contains(6)) {
			//TODO darf nur die augen des dreier Pasches beinhalten!!!
			int ergebnis = Wuerfel.getAugenzahl();
			System.out.println("Combo: Dreier-Pasch mit "+ergebnis+" Punkten");
			moeglicheComboPunkte.put(getComboName(), ergebnis);
			
			
			
		}
		else {
			System.out.println("Combo: kein Dreier-Pasch");
			moeglicheComboPunkte.put(getComboName(), 0);
		}
		
	}
}
