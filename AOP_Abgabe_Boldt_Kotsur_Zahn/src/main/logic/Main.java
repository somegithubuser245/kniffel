package main.logic;
import main.gui.*;

public class Main {
	GameController game;
	public static void main(String[] args) {
		//int spielerAnzahl = gui.getSpielerAnzahl();
		int spielerAnzahl = 4;
		GameController game = new GameController(4);
		
		while(!game.gameOver()) {
			game.nextRound();
		}
		
	}
	
	private void newGame() {
		//
	}
}
