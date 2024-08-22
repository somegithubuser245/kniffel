package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private int spielerAnzahl;
	public boolean fertigGewurfelt;
	
	private boolean gameOver;
	
	private Player[] playerList;
	public Player currentPlayer;
	
	private UI ui;
	//private int currentRound;
	
	public GameController() {
		ui = new UI();
		initGame();
	}
	
	private void initGame() { //updates die spielerAnzahl und sets playerList Groesse
		//default bools to set new game up
		this.gameOver = false;
		this.fertigGewurfelt = false; 
		
		this.spielerAnzahl = ui.chooseNumberOfPlayers();
		playerList = new Player[this.spielerAnzahl];
		
		for(int i = 0; i < spielerAnzahl; i++) {
			String name = ui.inputPlayerNames(i);
			playerList[i] = new Player(name, i);
		}
		
		currentPlayer = playerList[0];
		ui.setCurrentPlayerName(currentPlayer.getName());
	}
	
	public void getSchowCombination() {
		//wuerfel. ...
		//gui. ...
	}
	
	public void chooseCombinationOrSave() {
		//wuerfel. ...
		//gui. ...
	}
	
	public boolean gameOver() {
		if(gameOver) {
			//gui.ergebnisTabelle
			return true;
		}
		return false;
		//gui.ergebnisTabelle;
	}
	
	private void nextPlayer() {
		Wuerfel.setWurfZahl(0);
		
		int index = currentPlayer.getReihenFolgeNummer();
		if(index < playerList.length - 1) {
			currentPlayer = playerList[index + 1];
		} else {
			currentPlayer = playerList[0]; //start again from the beginning
		}
	}
	
	private void nextRound() {
		nextPlayer();
		ui.setCurrentPlayerName(currentPlayer.getName());
		fertigGewurfelt = false;
	}
	
	public void mainGameState() {
		if (Wuerfel.getWurfZahl() < 3 && Wuerfel.getWurfZahl() > 0) {
			switch(ui.chooseNextState()) {
			case 1: 
				defaultUiOutput();
				break;
			case 2:
				Wuerfel.setGehalteneWuerfel(ui.inputIndexesToSave());
				defaultUiOutput();
				break;
			case 3: 
				chooseCombination(false);
				nextRound();
				break;
			default:
				System.out.println("False input!");
			}
		} else if (Wuerfel.getWurfZahl() == 0){
			defaultUiOutput();
		} else {
			chooseCombination(true);
			nextRound();
		}
		
	}
	
	private void defaultUiOutput() {
		if (ui.nextRandom()) {
			Wuerfel.wurfeln();
			ui.outputData(Wuerfel.getWuerfelWerte());
			ui.outputPossibleCombinations(Combos.moeglicheComboPunkte);
		}
	}
	
	private void chooseCombination(boolean lastRound) {
		if(lastRound) {
			System.out.println("You MUST choose a combination. This is your last round!");
		}
		
		int combinationIndex = ui.chooseCombination();
		System.out.println("TO-Do: save this info into player" + combinationIndex + "\n");
		fertigGewurfelt = true;
	}
	
	private void checkPlayerDone() {
		if (currentPlayer.getPlayerDone()) {
			
		}
	}
	
	private void starteBerechnung() {
		
	}
	
	
	
}
