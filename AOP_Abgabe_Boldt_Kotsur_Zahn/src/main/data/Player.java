package main.data;
import java.util.Arrays;

public class Player {
	private String name;
	private int reihenFolgeNummer;
	private int totalPointsSum;
	private int[] scoreStats;
	
	public Player(String name) {
		this.name = name;
		this.totalPointsSum = 0;
		this.scoreStats = new int[14];
		Arrays.fill(scoreStats, -1);
	}
	
	public boolean getPlayerDone() {
		for(int i = 0; i < scoreStats.length; i++) {
			if (scoreStats[i] == -1) return false;
		}
		
		return true;
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
}
