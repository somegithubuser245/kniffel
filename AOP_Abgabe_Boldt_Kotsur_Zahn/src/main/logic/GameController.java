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
	private static GUI gui;
	
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
		PunkteTabelle.init(spielerAnzahl, currentPlayer);
	}
	
	public static int nextPlayer() {
		int index = currentPlayer.getReihenFolgeNummer();

		if(index < playerList.length - 1) {
				currentPlayer = playerList[index + 1];
		} else {
			currentPlayer = playerList[0]; //starte am Anfang
		}

		PunkteTabelle.setCurrentPlayer(currentPlayer);
		
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
		checkPlayerDone();
	}
	
	private static void checkPlayerDone() {
		if (PunkteTabelle.getLastPlayerDone()) {
			gameOver = true;
		}
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
