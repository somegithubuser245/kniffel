package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private int spielerAnzahl;
	public boolean fertigGewurfelt;
	
	private boolean gameOver;
	
	private Player[] playerList;
	private Combos[] comboKlassen;
	public Player currentPlayer;
	//private int currentRound;
	
	public GameController(int spielerAnzahl) {
		initGame(spielerAnzahl);
		this.gameOver = false;
		
		this.fertigGewurfelt = false;
		
		//TO-DO : ComboKlassen initialisieren
	}
	
	private void initGame(int spielerAnzahl) { //updates die spielerAnzahl und sets playerList Groesse
		this.spielerAnzahl = spielerAnzahl;
		playerList = new Player[this.spielerAnzahl];
		
		for(int i = 0; i < spielerAnzahl; i++) {
			//String name = gui.playerNameInput();
			playerList[i] = new Player("test " + i, i);
		}
		
		
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
		int index = currentPlayer.getReihenFolgeNummer();
		if(index > playerList.length - 1) {
			currentPlayer = playerList[0]; //start again from the beginning
		} else {
			currentPlayer = playerList[index + 1];
		}
	}
	
	public void nextRound() {
		nextPlayer();
		fertigGewurfelt = false;
	}
	
	private void checkPlayerDone() {
		if (currentPlayer.getPlayerDone()) {
			
		}
	}
	
	private void starteBerechnung() {
		
	}
	
	
	
}
