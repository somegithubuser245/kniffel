package main.logic;

import main.gui.*;
import main.data.*;

public class GameController {
	private int spielerAnzahl;
	public boolean fertigGewurfelt;
	
	private Player[] playerList;
	private Combos[] ComboKlassen;
	public String currentPlayer;
	//private int currentRound;
	
	public GameController(int spielerAnzahl) {
		initGame(spielerAnzahl);
		
		this.fertigGewurfelt = false;
		
		//TO-DO : ComboKlassen initialisieren
	}
	
	private void initGame(int spielerAnzahl) { //updates die spielerAnzahl und sets playerList Groesse
		this.spielerAnzahl = spielerAnzahl;
		playerList = new Player[this.spielerAnzahl];
		
		for(int i = 0; i < spielerAnzahl; i++) {
			playerList[i] = new Player("test " + i);
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
	
	private void gameOver() {
		
	}
	
	private void nextPlayer() {
		
	}
	
	private void checkPlayerDone() {
		
	}
	
	private void starteBerechnung() {
		
	}
	
	
	
}
