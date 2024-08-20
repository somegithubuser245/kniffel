package Logic;

public class ViererPash extends Combos{

	public ViererPash() {
		super("ViererPash");
	}

	@Override
	public void berechnePunkte() {
			//wenn eine Zahl mind. 4x Vorkommt--> Punkte an Map übergeben ,sonst Punkte = 0 gesetzt
			if(umgewandeltZuListe().contains(4) || umgewandeltZuListe().contains(5)||umgewandeltZuListe().contains(6)) {
				int ergebnis = Wuerfel.ermittleAugenzahl(Wuerfel.getAnzahlWerte());
				moeglicheComboPunkte.put(getComboName(), ergebnis);
			}
			else moeglicheComboPunkte.put(getComboName(), 0);
		
	}
		
}
