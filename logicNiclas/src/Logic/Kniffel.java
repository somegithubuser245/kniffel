package Logic;

public class Kniffel extends Combos{

	public Kniffel() {
		super("Kniffel");
	}

	@Override
	public void berechnePunkte() {
		if(umgewandeltZuListe().contains(5)) {
			moeglicheComboPunkte.put(getComboName(), 30);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);
		
	}
}
