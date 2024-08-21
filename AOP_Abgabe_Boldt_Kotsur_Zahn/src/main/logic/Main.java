package main.logic;
import main.gui.*;

import main.logic.combos.Combos;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Wuerfel.wurfeln();
		for(int i = 0;i < Wuerfel.getWuerfelWerte().length;i++) {
			System.out.println(Wuerfel.getWuerfelWerte()[i]);
			
	GameController game;
	public static void main(String[] args) {
		//int spielerAnzahl = gui.getSpielerAnzahl();
		int spielerAnzahl = 4;
		GameController game = new GameController(4);
		
		while(!game.gameOver()) {
			game.nextRound();
		}
		
		System.out.println(Combos.getMoeglicheComboPunkte());
		System.out.println(Wuerfel.getAnzahlWerte());
		System.out.println(Combos.umgewandeltZuListe());
	}
	
	private void newGame() {
		//
	}
}
