package main.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private String currentPlayerName;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public int chooseNumberOfPlayers() {
        System.out.print("Enter the number of players: ");
        return scanner.nextInt();
    }

    public String inputPlayerNames(int index) {
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter name for player " + (index) + ": ");
        String playerName = scanner.nextLine();
        
        return playerName;
    }

    public void outputData(int[] data) {
    	System.out.print("Current player: " + currentPlayerName + "\n");
        System.out.print("Wuerfel: ");
        for (int value : data) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public int[] inputIndexesToSave() {
        System.out.print("Enter the indexes to save (comma-separated): ");
        scanner.nextLine();
        String[] input = scanner.nextLine().split(",");
        int[] indexes = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            indexes[i] = Integer.parseInt(input[i].trim());
        }
        return indexes;
    }
    
    public int chooseCombination() {
        System.out.print("Enter the combination to choose: ");
        return scanner.nextInt();
    }

    public void outputPossibleCombinations(Map<String, Integer> moeglicheComboPunkte) {
        System.out.println("\nPossible combinations:");
        for (String key : moeglicheComboPunkte.keySet()) {
            System.out.print(key + ": " + moeglicheComboPunkte.get(key) + "  ");
        }
        System.out.print("\n");
    }

    public boolean nextRandom() {
        System.out.print("\nPress Enter to proceed to the next random event...");
        scanner.nextLine();
        return true;
    }
    
    public int chooseNextState() {
    	System.out.println("1. For next random wurf \n"
    			+ "2. For save some Wuerfel \n"
    			+ "3. For choose combination ");
    	return scanner.nextInt();
    }
    
    public void setCurrentPlayerName(String name) {
    	this.currentPlayerName = name;
    }
}
