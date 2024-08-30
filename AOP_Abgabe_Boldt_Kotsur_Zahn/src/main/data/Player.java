package main.data;
import java.util.Arrays;

public class Player {
	private String name;
	private int playerIndex;
	private int comboDoneCounter;
	
	public Player(String name, int playerIndex) {
		this.name = name;
		this.playerIndex = playerIndex;
		this.comboDoneCounter = 0;
	}

	//einetragene punkte müssen in das scorestats array
	public void updateScoreStats() {
		comboDoneCounter++;
		System.out.println("\nAnzahlGestrichen: " + comboDoneCounter + "\n");
	}
	
	public boolean getPlayerDone() {
		return comboDoneCounter == 13;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	public String getName() {
		return name;
	}
}
