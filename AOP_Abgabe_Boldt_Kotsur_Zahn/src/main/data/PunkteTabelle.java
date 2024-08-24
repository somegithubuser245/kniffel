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
	
	public PunkteTabelle(int spielerAnzahl, Player currentPlayer) {
		PunkteTabelle.currentPlayer = currentPlayer;
		punkteBerechnet = new int[13];
		punkteReal = new int [6][21]; //6 muesste eigentlich mit spielerAnzahl ersetzt werden
		punkteAnzeige = new int [6][21];
		punkteRealSingle = new int [21];
		punkteAnzeigeSingle = new int [21];
		resetPunkte();
	}
	
	public static void init(int spielerAnzahl, Player currentPlayer) {
		PunkteTabelle.currentPlayer = currentPlayer;
		punkteBerechnet = new int[13];
		punkteReal = new int [6][21]; //6 muesste eigentlich mit spielerAnzahl ersetzt werden
		punkteAnzeige = new int [6][21];
		punkteRealSingle = new int [21];
		punkteAnzeigeSingle = new int [21];
		resetPunkte();
	}
	
	public static void chooseCombination(int CombinationIndex) {
		currentPlayer.updateScoreStats(CombinationIndex, punkteBerechnet[CombinationIndex]);
		updatePunkteReal();
	}
	
	private static int[] convertMapToArray(Map<String, Integer> mapToConvert) {
		int[] converted = new int[13];
		int i = 0;
		for (String key : mapToConvert.keySet()) {
            converted[i] = mapToConvert.get(key);
            i++;
        }
		return converted;
	}
	
	private static void updatePunkteReal()  {
		int gesamtZahlenPunkte = 0;
		int bonus = 0;
		int gesamtObereTeil = 0;
		int gesamtUntereTeil = 0;
		int endSumme = 0;
		int[] playerScoreStats = currentPlayer.getScoreStats();
		
		//erste umwandlung fuer einser usw. auch gesamtZahlenPunkte berechnung
		for(int i = 0; i < 6; i++) {
			punkteRealSingle[i] = playerScoreStats[i];
			gesamtZahlenPunkte += playerScoreStats[i];
		}
		
		//bonus check
		if (gesamtZahlenPunkte > 63) {
			currentPlayer.updateScoreStats(13, 35); //das letzte index ist bei Player bonus
			bonus = 35;
		}
		
		gesamtObereTeil = gesamtZahlenPunkte + bonus;
		
		punkteRealSingle[6] = gesamtZahlenPunkte;
		punkteRealSingle[7] = bonus;
		punkteRealSingle[8] = gesamtObereTeil;
		
		
		//zweite teil der Berechnung (TODO: kann man nicht das ganze in einem for reinPacken und einfach indeces "groesser machen?")
		for(int i = 6; i < 13; i++) {
			punkteRealSingle[i+4] = playerScoreStats[i];
			gesamtUntereTeil += playerScoreStats[i];
		}
		
		endSumme = gesamtObereTeil + gesamtUntereTeil;
		
		punkteRealSingle[16] = gesamtUntereTeil;
		punkteRealSingle[17] = gesamtObereTeil;
		punkteRealSingle[18] = endSumme;
		
		//set according to player in the whole table
		punkteReal[currentPlayer.getReihenFolgeNummer()] = java.util.Arrays.copyOf(punkteRealSingle, punkteRealSingle.length);
		System.out.println("\npunkte real updated:");
		output(punkteReal[currentPlayer.getReihenFolgeNummer()]);
	}
	
	public static void updatePunkteAnzeige() {
		System.arraycopy(punkteRealSingle, 0, punkteAnzeigeSingle, 0, punkteRealSingle.length); //copy all
		
		//check obere teil
		for(int i = 0; i < 6; i++) {
			if(punkteRealSingle[i] == 0) {
				punkteAnzeigeSingle[i] = punkteBerechnet[i];
			}
		}
		
		//check untere teil
		for(int i = 6; i < 13; i++) {
			if(punkteRealSingle[i+4] == 0) {
				punkteAnzeigeSingle[i+4] = punkteBerechnet[i];
			}
		}
		
		//copy all to the array
		punkteAnzeige[currentPlayer.getReihenFolgeNummer()] = java.util.Arrays.copyOf(punkteAnzeigeSingle, punkteAnzeigeSingle.length);
		System.out.println("\npunkte anzeige updated:");
		output(punkteAnzeige[currentPlayer.getReihenFolgeNummer()]);
	}
	
	private static void resetPunkte() {
		for(int i = 0; i < 6; i++) {
			Arrays.fill(punkteReal[i], 0);
			Arrays.fill(punkteAnzeige[i], 0);
		}
		resetPunkteSingle();
	}
	
	private static void resetPunkteSingle() {
		Arrays.fill(punkteRealSingle, 0);
		Arrays.fill(punkteAnzeigeSingle, 0);
		Arrays.fill(punkteBerechnet, 0);
	}
	
	public static void output(int[] arrayInput) {
		for(int i = 0; i < arrayInput.length; i++) {
			System.out.println(i + ". " + arrayInput[i]);
		}
	}
	

	
	public static void setCurrentPlayer(Player currentPlayer) {
		PunkteTabelle.currentPlayer = currentPlayer;
	}
	
	public static int[][] getPunkte(String PunkteType) {
		switch(PunkteType) {
		case "anzeige":
			return punkteAnzeige;
		case "real":
			return punkteReal;
		default:
			return null;
		}
	}
	
	public static int[] getPunkteBerechnet() {
		return PunkteTabelle.punkteBerechnet;
	}
	
	public static void setPunkteBerechnet(Map<String, Integer> punkteBerechnet) {
		PunkteTabelle.punkteBerechnet = convertMapToArray(punkteBerechnet);
	}
	

}
