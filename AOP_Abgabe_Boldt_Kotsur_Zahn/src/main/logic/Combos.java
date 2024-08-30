package main.logic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Combos {

	private String comboName;
	protected static Map<String, Integer>possibleComboPoints = new LinkedHashMap<>();
	private int pointValue = 0;
	
	//Konstruktor generiert HashMap mit Key = KlassenName und Value = PunktZahl, wenn man die jeweilige Kombination wählen sollte
	public Combos(String comboName) {
		this.comboName = comboName;

		//key = Comboname --> value = pointValue
		possibleComboPoints.put(comboName,pointValue);
	}
	

	
	
	public static List<Integer> transformedToList(){
		List<Integer> zuListe = new ArrayList<>();
		
		for(int i : Dice.getCountingArray() ) {
			zuListe.add(i);
		}
		return zuListe;
	}

	
	
	public abstract void calculatePoints();
	
	
	
	//Getter/Setter
	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public static Map<String, Integer> getPossibleComboPoints() {
		return possibleComboPoints;
	}

	public void setPossibleComboPoints(Map<String, Integer> possibleComboPoints) {
		this.possibleComboPoints = possibleComboPoints;
	}
	public int getPointValue() {
		return pointValue;
	}
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
}
