package Logic;

public class DreierPash extends Combos{

	public DreierPash() {
		super("DreierPash");
		
	}

	@Override
	public void berechnePunkte() {
		//wenn eine Zahl mind. 3x Vorkommt
		if(umgewandeltZuListe().contains(3)|| umgewandeltZuListe().contains(4) || umgewandeltZuListe().contains(5)||umgewandeltZuListe().contains(6)) {
			int ergebnis =Wuerfel.ermittleAugenzahl(Wuerfel.getAnzahlWerte());
			moeglicheComboPunkte.put(getComboName(), ergebnis);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);
	}
	
}
