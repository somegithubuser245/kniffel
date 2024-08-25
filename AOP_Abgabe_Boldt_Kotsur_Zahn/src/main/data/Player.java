package main.data;
import java.util.Arrays;

public class Player {
	private String name;
	private int reihenFolgeNummer;
	private int totalPointsSum;
	private int anzahlGestrichen;
	private int[] scoreStats;
//	private boolean isDone;
	
	public Player(String name, int reihenFolgeNummer) {
		this.name = name;
		this.reihenFolgeNummer = reihenFolgeNummer;
//		this.isDone = false;
		this.totalPointsSum = 0;
		this.anzahlGestrichen = 0;
		this.scoreStats = new int[14];
		Arrays.fill(scoreStats, 0);
	}
	
	public void updateScoreStats(int combinationIndex, int points) {
		totalPointsSum += points;
		scoreStats[combinationIndex] = points;
		if(combinationIndex != 13) anzahlGestrichen += 1; //alles ausser bonusPunkte zaehlt
	}
	
//	private void totalPointsAdd(int points) {
//		totalPointsSum += points;
//	}
	
	public boolean getPlayerDone() {
		return anzahlGestrichen == 13;
	}
	
	public int[] getScoreStats() {
		return scoreStats;
	}
	
	public void setScoreStats(int[] scoreStats) {
		this.scoreStats = scoreStats;
	}
	
//	//updating, wenn eine combination gewählt wurde, wäre viel besser
//	private void updateTotalPoints() {
//		int sum = 0;
//		
//		for(int i = 0; i < scoreStats.length; i++) {
//			if(scoreStats[i] != -1) {
//				sum += scoreStats[i];
//			}
//		}
//		
//		totalPointsSum = sum;
//	}
	
	public int getReihenFolgeNummer() {
		return reihenFolgeNummer;
	}
	
	public String getName() {
		return name;
	}
}
