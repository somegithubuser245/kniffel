package Logic;

public class FullHouse extends Combos{

	public FullHouse() {
		super("FullHouse");
	}

	@Override
	public void berechnePunkte() {
		if(umgewandeltZuListe().contains(2) && umgewandeltZuListe().contains(3)) {
			moeglicheComboPunkte.put(getComboName(), 25);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);
	}
}
