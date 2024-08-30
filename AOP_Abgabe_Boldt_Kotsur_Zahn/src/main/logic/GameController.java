package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private static int numberOfPlayers;
	private static boolean gameOver;
	
	private static Player[] playerList;
	private static String[] playerNamesList;
 	private static Player currentPlayer;
	
	private static int winnerPoints = 1000;
	private static String winnerName = "yupiiiiiiii";
	
	public static void initGame(String[] names, int numberOfPlayers) {
		gameOver = false; 
		GameController.numberOfPlayers = numberOfPlayers;
	
		playerList = new Player[numberOfPlayers];
		playerNamesList = new String[numberOfPlayers];

		for(int i = 0; i < numberOfPlayers; i++) {
			playerList[i] = new Player(names[i], i);
			playerNamesList[i] = names[i];
		}


		currentPlayer = playerList[0];
		Points.init(numberOfPlayers, 0);
	}
	
	public static int nextPlayer() {
		int index = currentPlayer.getPlayerIndex();

		if(index < playerList.length - 1) {
			index ++;
		} else {
			index = 0;
		}

		currentPlayer = playerList[index];
		Points.setCurrentPlayer(index);
		
		return currentPlayer.getPlayerIndex();
	}
	
	public static int[] roll() {
		Dice.roll();
		Points.setCalculatedPoints(Combos.getPossibleComboPoints());
		Points.updatePointsToShow(false);
		return Dice.getDiceValues();
	}
	

	public static void chooseCombination(int combinationIndex, int points) {
		Points.chooseCombination(combinationIndex, points);
		currentPlayer.updateScoreStats();
		checkPlayerDone();
	}
	
	private static void checkPlayerDone() {
		int playerIndex = currentPlayer.getPlayerIndex();
		int lastPlayerIndex = numberOfPlayers - 1;
		
		if (playerIndex == lastPlayerIndex) {
			gameOver = currentPlayer.getPlayerDone();
			updateWinner();
		} else {
			gameOver = false;
		}
	}
	
	private static void updateWinner() {
		int[] winnerData = Points.getWinner();
		winnerName = playerList[winnerData[0]].getName();
		winnerPoints = winnerData[1];
	}

	//fuer mainScreen bei init
	public static String[] getPlayerNames() {
		return playerNamesList;
	}

	public static String getWinnerName() {
		return winnerName;
	}

	public static int getWinnerPoints() {
		return winnerPoints;
	}

	public static boolean getGameOver() {
		return gameOver;
	}
}
