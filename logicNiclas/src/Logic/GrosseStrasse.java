package Logic;

import java.util.Arrays;

public class GrosseStrasse extends Combos{

	public GrosseStrasse() {
		super("GrosseStrasse");
	} 

	@Override
	public void berechnePunkte() {
		int[]GrosseStrasse1 = {0,1,1,1,1,1,0};//1,2,3,4,5
		int[]GrosseStrasse2 = {0,0,1,1,1,1,1};//2,3,4,5,6
		int[]test = {0,0,1,1,1,1,1};
		
		
		if(Arrays.equals(GrosseStrasse1, Wuerfel.getAnzahlWerte()) || Arrays.equals(GrosseStrasse2, Wuerfel.getAnzahlWerte() )) {
			moeglicheComboPunkte.put(getComboName(), 40);
		}
		else moeglicheComboPunkte.put(getComboName(), 0);	
		
	}
}