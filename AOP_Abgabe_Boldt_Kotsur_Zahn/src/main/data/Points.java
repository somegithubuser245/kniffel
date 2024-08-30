package main.data;
import java.util.Map;
import java.util.Arrays;

public class Points {
	private static int[] calculatedPoints;
	private static int[][] pointsReal;
	private static int[][] pointsToShow;
	private static int[] pointsRealSingle;
	private static int[] pointsToShowSingle;
	private static int currentPlayerIndex;
	private static int numberOfPlayers;
	private static int pointsWinner;
	private static int winnerIndex;
	
	
	public static void init(int number, int currentPlayerIndex) {
		Points.currentPlayerIndex = currentPlayerIndex;
		Points.numberOfPlayers = number;
		winnerIndex = 0;
		pointsWinner = 0;
		calculatedPoints = new int[13];
		pointsReal = new int [number][19];
		pointsToShow = new int [number][19];
		pointsRealSingle = new int [19];
		pointsToShowSingle = new int [19];
		resetPoints(); //füllt mit -1
	}
	
	//kniffel update, 2 mal updateScoreStats()
	// bekommt choosecombination den richtigen index?? 
	public static void chooseCombination(int combinationIndex, int points) {
		System.out.println("\n[Points class] Index ist " + combinationIndex + "\n");
		updatePointsReal(combinationIndex, points);
		updatePointsToShow(true);
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
	
	private static void updatePointsReal(int index, int points)  {
		pointsReal[currentPlayerIndex][index] = points;

		int pointsForBonus = 0;
		int bonus = 0;
		int sumTop = 0;
		int sumBottom = 0;
		int endSum = 0;
		//setze punkte array des CurrentPlayer als pointsRealSingle[]
		int[] pointsRealSingle = pointsReal[currentPlayerIndex];
		
		//erste umwandlung fuer einser usw. auch gesamtZahlenPunkte berechnung
		for(int i = 0; i < 6; i++) {
			if(pointsRealSingle[i] > 0) {
				pointsForBonus += pointsRealSingle[i];
			}
		}
		
		//bonus check
		if (pointsForBonus > 63) {
			bonus = 35;
		}
		
		sumTop = pointsForBonus + bonus;
		
		pointsRealSingle[6] = pointsForBonus;
		pointsRealSingle[7] = bonus;
		pointsRealSingle[8] = sumTop;
		
		
		//zweite teil der Berechnung (TODO: kann man nicht das ganze in einem for reinPacken und einfach indeces "groesser machen?")
		for(int i = 9; i < 16; i++) {
				//habe aus i+4 -> i+3 gemacht. da index im array sonst verschoben (david)
			if(pointsRealSingle[i] > 0) {
				sumBottom += pointsRealSingle[i];
			}
			
		}
		
		endSum = sumTop + sumBottom;
		
		pointsRealSingle[16] = sumBottom;
		pointsRealSingle[17] = sumTop;
		pointsRealSingle[18] = endSum;
		
		//set according to player in the whole table
		pointsReal[currentPlayerIndex] = java.util.Arrays.copyOf(pointsRealSingle, pointsRealSingle.length);
		updateWinner(endSum);
	}
	
	public static void updatePointsToShow(boolean calculateAfterCombo) {
		pointsToShowSingle = java.util.Arrays.copyOf(pointsReal[currentPlayerIndex], pointsReal[currentPlayerIndex].length);
		
		//check obere teil
		for(int i = 0; i < 6; i++) {
			if(pointsReal[currentPlayerIndex][i] < 0 && !calculateAfterCombo) {
				pointsToShowSingle[i] = calculatedPoints[i];
			} else {
				pointsToShowSingle[i] = pointsReal[currentPlayerIndex][i];
			}
		}
		
		//check untere teil
		for(int i = 6; i < 13; i++) {
			if(pointsReal[currentPlayerIndex][i+3] < 0 && !calculateAfterCombo) {
				pointsToShowSingle[i+3] = calculatedPoints[i];
			} else {
				pointsToShowSingle[i+3] = pointsReal[currentPlayerIndex][i+3];
			}
		}
		
		//copy all to the array
		pointsToShow[currentPlayerIndex] = java.util.Arrays.copyOf(pointsToShowSingle, pointsToShowSingle.length);
	}
	
	private static void resetPoints() {
		for(int i = 0; i < numberOfPlayers; i++) {
			Arrays.fill(pointsReal[i], -1);
			Arrays.fill(pointsToShow[i], -1);
		}
		resetPointsSingle();
	}
	
	private static void resetPointsSingle() {
		Arrays.fill(pointsRealSingle, -1);
		Arrays.fill(pointsToShowSingle, -1);
		Arrays.fill(calculatedPoints, -1);
	}
	
	public static void output(int[] arrayInput) {
		for(int i = 0; i < arrayInput.length; i++) {
			System.out.println(i + ". " + arrayInput[i]);
		}
	}
	
	public static void setCurrentPlayer(int currentPlayer) {
		Points.currentPlayerIndex = currentPlayer;
	}

	public static int[][] getPointsReal() {
		return pointsReal;
	}
	
	
	public static int[][] getPointsToShow() {
		output(pointsToShow[currentPlayerIndex]);
		return pointsToShow;
	}

	public static int[] getCalculatedPoints() {
		output(pointsReal[currentPlayerIndex]);
		return calculatedPoints;
	}
	
	public static void setCalculatedPoints(Map<String, Integer> calculatedPoints) {
		Points.calculatedPoints = convertMapToArray(calculatedPoints);
	}
	
	private static void updateWinner(int points) {
		if (points > pointsWinner) {
			pointsWinner = points;
			winnerIndex = currentPlayerIndex;
		}
	}
	
	public static int[] getWinner() {
		int [] gewinnerData = {winnerIndex, pointsWinner};
		return gewinnerData;
	}
	

}
