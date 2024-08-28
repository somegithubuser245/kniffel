package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private static int spielerAnzahl;
	private static boolean gameOver;
	private static int roundsCounter;
	
	private static Player[] playerList;
	private static String[] playerNamesList;
	private static boolean[] playerDoneIndexes;
 	private static Player currentPlayer;
	
	private static int gewinnerPunkte = 1000;
	private static String gewinnerName = "yupiiiiiiii";
	private static GUI gui;
	
	
	public GameController() {
		gui = new GUI();
		gui.startScreen();
	}
	
	public static void initGame(String[] namen, int spielerAnzahl) {
		gameOver = false; 
		roundsCounter = 0;
		GameController.spielerAnzahl = spielerAnzahl;
	
		playerList = new Player[spielerAnzahl];
		playerNamesList = new String[spielerAnzahl];

		for(int i = 0; i < spielerAnzahl; i++) {
			playerList[i] = new Player(namen[i], i);
			playerNamesList[i] = namen[i];
		}


		currentPlayer = playerList[0];
		PunkteTabelle.init(spielerAnzahl, currentPlayer);
	}
	
	public static int nextPlayer() {
		int index = currentPlayer.getReihenFolgeNummer();
		// checkPlayerDone();

		if(index < playerList.length - 1) {
				currentPlayer = playerList[index + 1];
		} else {
			currentPlayer = playerList[0]; //start again from the beginning
		}

		// for(player : playerList) {
		// 	int index = currentPlayer.getReiehnFolgeNummer();

		// }
		

		PunkteTabelle.setCurrentPlayer(currentPlayer);
		System.out.println("\nnext player von gameController\n");
		return currentPlayer.getReihenFolgeNummer();
	}
	
	public static int[] wuerfeln() {
		Wuerfel.wurfeln();
		PunkteTabelle.setPunkteBerechnet(Combos.getMoeglicheComboPunkte());
		PunkteTabelle.updatePunkteAnzeige(false);
		return Wuerfel.getWuerfelWerte();
	}
	

	public static void chooseCombination(int combinationIndex, int punkte) {
		PunkteTabelle.chooseCombination(combinationIndex, punkte);
	}
	
	private void checkPlayerDone() {
		if (currentPlayer.getPlayerDone()) {
			playerDoneIndexes[currentPlayer.getReihenFolgeNummer()] = true;
		}
	}

	// public boolean checkGameOver() {
	// 	if() {
			
	// 		return true;
	// 	}
	// 	return false;
	// }

	//fuer mainScreen bei init
	public static String[] getSpielerNamen() {
		return playerNamesList;
	}

	public static String getGewinnerName() {
		return gewinnerName;
	}

	public static int getGewinnerPunkte() {
		return gewinnerPunkte;
	}

	public static boolean getGameOver() {
		return gameOver;
	}
}
