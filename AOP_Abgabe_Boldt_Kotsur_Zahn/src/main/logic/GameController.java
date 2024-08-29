package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private static int spielerAnzahl;
	private static boolean gameOver;
	
	private static Player[] playerList;
	private static String[] playerNamesList;
 	private static Player currentPlayer;
	
	private static int gewinnerPunkte = 1000;
	private static String gewinnerName = "yupiiiiiiii";
	
	public static void initGame(String[] namen, int spielerAnzahl) {
		gameOver = false; 
		GameController.spielerAnzahl = spielerAnzahl;
	
		playerList = new Player[spielerAnzahl];
		playerNamesList = new String[spielerAnzahl];

		for(int i = 0; i < spielerAnzahl; i++) {
			playerList[i] = new Player(namen[i], i);
			playerNamesList[i] = namen[i];
		}


		currentPlayer = playerList[0];
		PunkteTabelle.init(spielerAnzahl, 0);
	}
	
	public static int nextPlayer() {
		int index = currentPlayer.getReihenFolgeNummer();

		if(index < playerList.length - 1) {
			index ++;
		} else {
			index = 0;
		}

		currentPlayer = playerList[index];
		PunkteTabelle.setCurrentPlayer(index);
		
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
		currentPlayer.updateScoreStats();
		checkPlayerDone();
	}
	
	private static void checkPlayerDone() {
		int playerIndex = currentPlayer.getReihenFolgeNummer();
		int lastPlayerIndex = spielerAnzahl - 1;
		
		if (playerIndex == lastPlayerIndex) {
			gameOver = currentPlayer.getPlayerDone();
			updateGewinner();
		} else {
			gameOver = false;
		}
	}
	
	private static void updateGewinner() {
		int[] gewinnerData = PunkteTabelle.getGewinner();
		gewinnerName = playerList[gewinnerData[0]].getName();
		gewinnerPunkte = gewinnerData[1];
	}

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
