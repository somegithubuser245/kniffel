package main.data;
import java.util.Map;
import java.util.Arrays;

public class PunkteTabelle {
	private static int[] punkteBerechnet;
	private static int[][] punkteReal;
	private static int[][] punkteAnzeige;
	private static int[] punkteRealSingle;
	private static int[] punkteAnzeigeSingle;
	private static int currentPlayerIndex;
	private static int spielerAnzahl;
	private static int gewinnerPunkte;
	
	
	public static void init(int spielerAnzahl, int currentPlayerIndex) {
		PunkteTabelle.currentPlayerIndex = currentPlayerIndex;
		PunkteTabelle.spielerAnzahl = spielerAnzahl;
		punkteBerechnet = new int[13];
		punkteReal = new int [spielerAnzahl][19];
		punkteAnzeige = new int [spielerAnzahl][19];
		punkteRealSingle = new int [19];
		punkteAnzeigeSingle = new int [19];
		resetPunkte(); //füllt mit -1
	}
	
	//kniffel update, 2 mal updateScoreStats()
	// bekommt choosecombination den richtigen index?? 
	public static void chooseCombination(int combinationIndex, int punkte) {
		System.out.println("\n[PunkteTabelle class] Index ist " + combinationIndex + "\n");
		updatePunkteReal(combinationIndex, punkte);
		updatePunkteAnzeige(true);
	}
	
	private static int[] convertMapToArray(Map<String, Integer> mapToConvert) {
		int[] converted = new int[13];
		int i = 0;
		for (String key : mapToConvert.keySet()) {
			System.out.println(key + " " + mapToConvert.get(key));
            converted[i] = mapToConvert.get(key);
            i++;
        }
		return converted;
	}
	
	private static void updatePunkteReal(int index, int punkte)  {
		punkteReal[currentPlayerIndex][index] = punkte;

		int gesamtZahlenPunkte = 0;
		int bonus = 0;
		int gesamtObereTeil = 0;
		int gesamtUntereTeil = 0;
		int endSumme = 0;
		//setze punkte array des CurrentPlayer als punkteRealSingle[]
		int[] punkteRealSingle = punkteReal[currentPlayerIndex];
		
		//erste umwandlung fuer einser usw. auch gesamtZahlenPunkte berechnung
		for(int i = 0; i < 6; i++) {
			if(punkteRealSingle[i] > 0) {
				gesamtZahlenPunkte += punkteRealSingle[i];
			}
		}
		
		//bonus check
		if (gesamtZahlenPunkte > 63) {
			bonus = 35;
		}
		
		gesamtObereTeil = gesamtZahlenPunkte + bonus;
		
		punkteRealSingle[6] = gesamtZahlenPunkte;
		punkteRealSingle[7] = bonus;
		punkteRealSingle[8] = gesamtObereTeil;
		
		
		//zweite teil der Berechnung (TODO: kann man nicht das ganze in einem for reinPacken und einfach indeces "groesser machen?")
		for(int i = 9; i < 16; i++) {
				//habe aus i+4 -> i+3 gemacht. da index im array sonst verschoben (david)
			if(punkteRealSingle[i] > 0) {
				gesamtUntereTeil += punkteRealSingle[i];
			}
			
		}
		
		endSumme = gesamtObereTeil + gesamtUntereTeil;
		
		punkteRealSingle[16] = gesamtUntereTeil;
		punkteRealSingle[17] = gesamtObereTeil;
		punkteRealSingle[18] = endSumme;
		
		//set according to player in the whole table
		punkteReal[currentPlayerIndex] = java.util.Arrays.copyOf(punkteRealSingle, punkteRealSingle.length);
	}
	
	public static void updatePunkteAnzeige(boolean berechnungNachKombo) {
		punkteAnzeigeSingle = java.util.Arrays.copyOf(punkteReal[currentPlayerIndex], punkteReal[currentPlayerIndex].length);
		
		//check obere teil
		for(int i = 0; i < 6; i++) {
			if(punkteReal[currentPlayerIndex][i] < 0 && !berechnungNachKombo) {
				punkteAnzeigeSingle[i] = punkteBerechnet[i];
			} else {
				punkteAnzeigeSingle[i] = punkteReal[currentPlayerIndex][i];
			}
		}
		
		//check untere teil
		for(int i = 6; i < 13; i++) {
			if(punkteReal[currentPlayerIndex][i+3] < 0 && !berechnungNachKombo) {
				punkteAnzeigeSingle[i+3] = punkteBerechnet[i];
			} else {
				punkteAnzeigeSingle[i+3] = punkteReal[currentPlayerIndex][i+3];
			}
		}
		
		//copy all to the array
		punkteAnzeige[currentPlayerIndex] = java.util.Arrays.copyOf(punkteAnzeigeSingle, punkteAnzeigeSingle.length);
	}
	
	private static void resetPunkte() {
		for(int i = 0; i < spielerAnzahl; i++) {
			Arrays.fill(punkteReal[i], -1);
			Arrays.fill(punkteAnzeige[i], -1);
		}
		resetPunkteSingle();
	}
	
	private static void resetPunkteSingle() {
		Arrays.fill(punkteRealSingle, -1);
		Arrays.fill(punkteAnzeigeSingle, -1);
		Arrays.fill(punkteBerechnet, -1);
	}
	
	public static void output(int[] arrayInput) {
		for(int i = 0; i < arrayInput.length; i++) {
			System.out.println(i + ". " + arrayInput[i]);
		}
	}
	
	public static void setCurrentPlayer(int currentPlayer) {
		PunkteTabelle.currentPlayerIndex = currentPlayer;
	}

	public static int[][] getPunkteReal() {
		return punkteReal;
	}
	
	
	public static int[][] getPunkteAnzeige() {
		output(punkteAnzeige[currentPlayerIndex]);
		return punkteAnzeige;
	}

	public static int[] getPunkteBerechnet() {
		output(punkteReal[currentPlayerIndex]);
		return punkteBerechnet;
	}
	
	public static void setPunkteBerechnet(Map<String, Integer> punkteBerechnet) {
		PunkteTabelle.punkteBerechnet = convertMapToArray(punkteBerechnet);
	}
	

}
