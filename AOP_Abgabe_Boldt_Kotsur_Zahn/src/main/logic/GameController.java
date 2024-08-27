package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private static int spielerAnzahl;
	private static boolean gameOver;
	
	private static Player[] playerList;
	private static String[] playerNamesList;
 	private static Player currentPlayer;
	
	private static GUI gui;
	//private int currentRound;
	
	
	public GameController() {
		gui = new GUI();
		gui.startScreen();
	}
	
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
	
	public boolean gameOver() {
		if(gameOver) {
			// gui.endScreen();
			return true;
		}
		return false;
	}
	
	public static int nextPlayer() {
		int index = currentPlayer.getReihenFolgeNummer();

		if(index < playerList.length - 1) {
			currentPlayer = playerList[index + 1];
		} else {
			currentPlayer = playerList[0]; //start again from the beginning
		}

		PunkteTabelle.setCurrentPlayer(currentPlayer);
		System.out.println("\nnext player von gameController\n");
		return currentPlayer.getReihenFolgeNummer();
	}
	
	// private void nextRound() {
	// 	nextPlayer();
		
	// 	PunkteTabelle.setCurrentPlayer(currentPlayer);
	// 	ui.setCurrentPlayerName(currentPlayer.getName());
	// }
	
	// public void mainGameState() {
	// 	int wurfZahl = Wuerfel.getWurfZahl();
		
		
	// 	if (wurfZahl < 3 && wurfZahl > 0) {
	// 		switch(ui.chooseNextState()) {
	// 			case 1: 
	// 				defaultUiOutput();
	// 				break;
	// 			case 2:
	// 				Wuerfel.setGehalteneWuerfel(ui.inputIndexesToSave());
	// 				defaultUiOutput();
	// 				break;
	// 			case 3: 
	// 				chooseCombination(false);
	// 				nextRound();
	// 				break;
	// 			default:
	// 				System.out.println("False input!");
	// 				break;
	// 		}
	// 	} else if (wurfZahl == 0){
	// 		defaultUiOutput();
	// 	} else {
	// 		chooseCombination(true);
	// 		nextRound();
	// 	}
		
	// }
	
	public static int[] wuerfeln() {
		Wuerfel.wurfeln();
		PunkteTabelle.setPunkteBerechnet(Combos.getMoeglicheComboPunkte());
		PunkteTabelle.updatePunkteAnzeige();
		return Wuerfel.getWuerfelWerte();
	}

// 	private void defaultUiOutput() {
// 		if (ui.nextRandom()) {
// 			Wuerfel.wurfeln();
// 			ui.outputData(Wuerfel.getWuerfelWerte());
// 			ui.outputPossibleCombinations(Combos.getMoeglicheComboPunkte());
// 			PunkteTabelle.setPunkteBerechnet(Combos.getMoeglicheComboPunkte());
// 			PunkteTabelle.updatePunkteAnzeige();
// //			ui.outputPossibleCombinations(Combos.getMoeglicheComboPunkte());
// 		}
// 	}
	

	public static void chooseCombination(int combinationIndex) {
		PunkteTabelle.chooseCombination(combinationIndex);
	}
	
	private void checkPlayerDone() {
		if (currentPlayer.getPlayerDone()) {
			
		}
	}

	// public void erstelleSpieler() {

	// }

	//fuer mainScreen bei init
	public static String[] getSpielerNamen() {
		return playerNamesList;
	}
	
//	public static int getSpielerAnzahl() {
//		return spielerAnzahl;
//	}
	
	
}
