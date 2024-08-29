package main.data;
import java.util.Arrays;

public class Player {
	private String name;
	private int reihenFolgeNummer;
	private int anzahlGestrichen;
	
	public Player(String name, int reihenFolgeNummer) {
		this.name = name;
		this.reihenFolgeNummer = reihenFolgeNummer;
		this.anzahlGestrichen = 0;
	}

	//einetragene punkte müssen in das scorestats array
	public void updateScoreStats() {
		anzahlGestrichen++;
		System.out.println("\nAnzahlGestrichen: " + anzahlGestrichen + "\n");
	}
	
	public boolean getPlayerDone() {
		return anzahlGestrichen == 13;
	}
	
	public int getReihenFolgeNummer() {
		return reihenFolgeNummer;
	}
	
	public String getName() {
		return name;
	}
}
