package main.data;
import java.util.Arrays;

public class Player {
	private String name;
	private int reihenFolgeNummer;
	private int totalPointsSum;
	private int[] scoreStats;
	private boolean isDone;
	
	public Player(String name, int reihenFolgeNummer) {
		this.name = name;
		this.reihenFolgeNummer = reihenFolgeNummer;
		this.isDone = false;
		this.totalPointsSum = 0;
		this.scoreStats = new int[14];
		Arrays.fill(scoreStats, -1);
	}
	
	public boolean getPlayerDone() {
		return isDone;
	}
	
	public int[] getScoreStats() {
		return scoreStats;
	}
	
	public void setScoreStats(int[] scoreStats) {
		this.scoreStats = scoreStats;
	}
	
	//updating, wenn eine combination gewählt wurde, wäre viel besser
	private void updateTotalPoints() {
		int sum = 0;
		
		for(int i = 0; i < scoreStats.length; i++) {
			if(scoreStats[i] != -1) {
				sum += scoreStats[i];
			}
		}
		
		totalPointsSum = sum;
	}
	
	//also hier noch die funktion
	public void totalPointsAdd(int points) {
		totalPointsSum += points;
	}
	
	public int getReihenFolgeNummer() {
		return reihenFolgeNummer;
	}
	
	public String getName() {
		return name;
	}
}
