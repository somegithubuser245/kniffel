package main.logic.combos;

import main.logic.Combos;
import main.logic.Wuerfel;

public class ViererPash extends Combos{

	public ViererPash() {
		super("ViererPash");
	}

	@Override
	public void berechnePunkte() {
			//wenn eine Zahl mind. 4x Vorkommt--> Punkte an Map übergeben ,sonst Punkte = 0 gesetzt
			if(umgewandeltZuListe().contains(4) || umgewandeltZuListe().contains(5)||umgewandeltZuListe().contains(6)) {
				int ergebnis = Wuerfel.getAugenzahl();
				System.out.println("Combo: Vierer-Pasch mit "+ergebnis+" Punkten");
				moeglicheComboPunkte.put(getComboName(), ergebnis);
				
			}
			else {
				System.out.println("Combo: kein Vierer-Pasch");
				moeglicheComboPunkte.put(getComboName(), 0);
			}
		
	}
		
}
