package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Combos {

	private String comboName;
	protected static Map<String, Integer>moeglicheComboPunkte= new HashMap<>();;
	private int punkteWert = 0;
	
	//Konstruktor generiert HashMap mit Key = KlassenName und Value = PunktZahl, wenn man die jeweilige Kombination wählen sollte
	public Combos(String comboName) {
		this.comboName = comboName;

		//key = Comboname --> value = punkteWert
		moeglicheComboPunkte.put(comboName,punkteWert);
	}
	
	
	
		
	//Generiert Array welcher an GUI weitergegeben wird in dem alle möglichen Kombinationen enhalten sind --------> brauche ich glaube ich nicht mehr????--_> nimmt punkteWerte un aktuallisiert diese in der Map
	public static Map<String, Integer>generiereArray() {
		
		
		
		return moeglicheComboPunkte;
	}
	
	
	public static List<Integer> umgewandeltZuListe(){
		List<Integer> zuListe = new ArrayList<>();
		
		for(int i : Wuerfel.getAnzahlWerte() ) {
			zuListe.add(i);
		}
		return zuListe;
	}

	
	
	public abstract void berechnePunkte();
	
	
	
	//Getter/Setter
	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public Map<String, Integer> getMoeglicheComboPunkte() {
		return moeglicheComboPunkte;
	}

	public void setMoeglicheComboPunkte(Map<String, Integer> moeglicheCombosPunkte) {
		this.moeglicheComboPunkte = moeglicheComboPunkte;
	}
	public int getPunkteWert() {
		return punkteWert;
	}
	public void setPunkteWert(int punkteWert) {
		this.punkteWert = punkteWert;
	}
}
