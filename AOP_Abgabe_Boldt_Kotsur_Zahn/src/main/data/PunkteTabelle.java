package main.data;
import java.util.Map;
import java.util.Arrays;

public class PunkteTabelle {
	private static int[] punkteBerechnet;
	private static int[][] punkteReal;
	private static int[][] punkteAnzeige;
	private static int[] punkteRealSingle;
	private static int[] punkteAnzeigeSingle;
	private static Player currentPlayer;
	private static int spielerAnzahl;
	
	
	public static void init(int spielerAnzahl, Player currentPlayer) {
		PunkteTabelle.currentPlayer = currentPlayer;
		PunkteTabelle.spielerAnzahl = spielerAnzahl;
		punkteBerechnet = new int[13];
		punkteReal = new int [spielerAnzahl][19];
		punkteAnzeige = new int [spielerAnzahl][19];
		punkteRealSingle = new int [19];
		punkteAnzeigeSingle = new int [19];
		resetPunkte(); //füllt mit -1
		//updatePunkteReal(); 
	}
	
	//kniffel update, 2 mal updateScoreStats()
	// bekommt choosecombination den richtigen index?? 
	public static void chooseCombination(int combinationIndex, int punkte) {
		System.out.println("\n[PunkteTabelle class] Index ist " + combinationIndex + "\n");
		currentPlayer.updateScoreStats();
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
		punkteReal[currentPlayer.getReihenFolgeNummer()][index] = punkte;

		int gesamtZahlenPunkte = 0;
		int bonus = 0;
		int gesamtObereTeil = 0;
		int gesamtUntereTeil = 0;
		int endSumme = 0;
		//setze punkte array des CurrentPlayer als punkteRealSingle[]
		int[] punkteRealSingle = punkteReal[currentPlayer.getReihenFolgeNummer()];
		
		//erste umwandlung fuer einser usw. auch gesamtZahlenPunkte berechnung
		for(int i = 0; i < 6; i++) {
			if(punkteRealSingle[i] > 0) {
				gesamtZahlenPunkte += punkteRealSingle[i];
			}
		}
		
		//bonus check
		if (gesamtZahlenPunkte > 63) {
			currentPlayer.updateScoreStats(); //das letzte index ist bei Player bonus
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
		punkteReal[currentPlayer.getReihenFolgeNummer()] = java.util.Arrays.copyOf(punkteRealSingle, punkteRealSingle.length);
	}
	
	public static void updatePunkteAnzeige(boolean berechnungNachKombo) {
		punkteAnzeigeSingle = java.util.Arrays.copyOf(punkteReal[currentPlayer.getReihenFolgeNummer()], punkteReal[currentPlayer.getReihenFolgeNummer()].length);
		//System.arraycopy(punkteReal[currentPlayer.getReihenFolgeNummer], 0, punkteAnzeigeSingle, 0, punkteRealSingle.length); //copy all
		

		int playerIndex = currentPlayer.getReihenFolgeNummer();
		//check obere teil
		for(int i = 0; i < 6; i++) {
			if(punkteReal[playerIndex][i] < 0 && !berechnungNachKombo) {
				punkteAnzeigeSingle[i] = punkteBerechnet[i];
			} else {
				punkteAnzeigeSingle[i] = punkteReal[playerIndex][i];
			}
		}
		
		//check untere teil
		for(int i = 6; i < 13; i++) {
			if(punkteReal[playerIndex][i+3] < 0 && !berechnungNachKombo) {
				punkteAnzeigeSingle[i+3] = punkteBerechnet[i];
			} else {
				punkteAnzeigeSingle[i+3] = punkteReal[playerIndex][i+3];
			}
		}
		
		//copy all to the array
		punkteAnzeige[playerIndex] = java.util.Arrays.copyOf(punkteAnzeigeSingle, punkteAnzeigeSingle.length);
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
	
	public static void setCurrentPlayer(Player currentPlayer) {
		PunkteTabelle.currentPlayer = currentPlayer;
	}

	public static int[][] getPunkteReal() {
		return punkteReal;
	}
	
	
	public static int[][] getPunkteAnzeige() {
		output(punkteAnzeige[currentPlayer.getReihenFolgeNummer()]);
		return punkteAnzeige;
	}

	public static int[] getPunkteBerechnet() {
		output(punkteReal[currentPlayer.getReihenFolgeNummer()]);
		return punkteBerechnet;
	}
	
	public static void setPunkteBerechnet(Map<String, Integer> punkteBerechnet) {
		PunkteTabelle.punkteBerechnet = convertMapToArray(punkteBerechnet);
	}
	
	public static boolean getLastPlayerDone() {
		int playerIndex = currentPlayer.getReihenFolgeNummer() + 1;
		
		if (playerIndex == spielerAnzahl) {
			return currentPlayer.getPlayerDone();
		}
		return false;
	}
	

}
