package main.logic;

import java.util.Arrays;
import java.util.Random;

import main.logic.combos.*;

public class Dice {
	private static int[] diceValues = new int[5];
	private static boolean[]savedDiceValues = new boolean[5];
	private static int[]countingArray = new int [7];// C_Feld
	private static int diceSum = 0;
	
	
	public static void roll() {
		System.out.println("LOGIK wird gewürfelt");
		for(int i = 0;i < 5;i++) {
			if (!savedDiceValues[i]) {
				diceValues[i] = new Random().nextInt(6)+1;
			};
		}
		updateCountingArray();
		updateSum();
		startComboChecker();
		
		Arrays.fill(savedDiceValues, false);
		System.out.println("LOGIK fertig gewürfelt");
	}
	
	//CountingSort Hilfarray
	public static void updateCountingArray() {
		Arrays.fill(countingArray,0);
		
		for(int i : diceValues) {
			countingArray[i] += 1;
		}
	}
	
	public static void startComboChecker() {
		//berchne Punkte von jeder KLasse muss aufgerufen werden
		// Instanzen der Subklassen erstellen
		System.out.println("combochecker wird gestartet");
	    Combos einser = new Einser();
	    Combos zweier = new Zweier();
	    Combos dreier = new Dreier();
	    Combos vierer = new Vierer();
	    Combos fuenfer = new Fuenfer();
	    Combos sechser = new Sechser();
	    
	    Combos dreierPash = new DreierPash();
	    Combos viererPash = new ViererPash();
	    Combos fullHouse = new FullHouse();
		Combos kleineStrasse = new KleineStrasse();
	    Combos grosseStrasse = new GrosseStrasse();
		Combos kniffel = new Kniffel();
	    Combos chance = new Chance();
	    
	    Combos[] combosAsArray = {einser, zweier, dreier, vierer, fuenfer, sechser, dreierPash, viererPash, fullHouse, kleineStrasse, 
	    		grosseStrasse, kniffel, chance};
	    
	    for (Combos combo : combosAsArray) {
	    	combo.calculatePoints();
	    }
	}
	
	
	
	
	
	//Getter/Setter
	public static int updateSum() {
		diceSum = 0;
		for(int i : diceValues) {
			diceSum += i;
		}
		return diceSum;
	}
	public static int[] getDiceValues() {
		return diceValues;
	}
	public static void setDiceValues(int[] diceValues) {
		Dice.diceValues = diceValues;
		System.out.println("LOGIC [wuerfel class] setze wuerfel auf");
		for(int i = 0; i<Dice.diceValues.length; i++) {
			System.out.println("In: "+ diceValues[i]+" saved as: "+Dice.diceValues[i]);
		}
	}
	
	public static void setSavedDiceValues(boolean[] savedDiceValues) {
		System.out.println("LOGIC [wuerfel] setze gehalten: ");
		for(boolean w:savedDiceValues) {
			System.out.println("/n"+w);
		}
		
		Dice.savedDiceValues = savedDiceValues;
	}

	public static int[] getCountingArray() {
		return countingArray;
	}

	public static void setCountingArray(int[] countingArray) {
		Dice.countingArray = countingArray;
	}


	public static int getSum() {
		return diceSum;
	}

	public static void setSum(int diceSum) {
		Dice.diceSum = diceSum;
	}
	
	
}
