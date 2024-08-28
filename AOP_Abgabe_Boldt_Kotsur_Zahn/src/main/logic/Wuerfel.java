package main.logic;

import java.util.Arrays;
import java.util.Random;

import main.logic.combos.*;

public class Wuerfel {
	private static int[] wuerfelWerte = new int[5];
	private static boolean[]gehalteneWuerfel = new boolean[5];
	private static int[]anzahlWerte = new int [7];// C_Feld
	private static int wurfZahl = 0;
	private static boolean fertigGewuerfelt;
	private static int augenZahl = 0;
	
	
	public static void wurfeln() {
		System.out.println("LOGIK wird gewürfelt");
		for(int i = 0;i < 5;i++) {
			if (!gehalteneWuerfel[i]) {
				wuerfelWerte[i] = new Random().nextInt(6)+1;
			};
		}
		zaehleWerte();
		ermittleAugenzahl();
		starteComboChecker();
		
		wurfZahl += 1;
		Arrays.fill(gehalteneWuerfel, false);
		System.out.println("LOGIK fertig gewürfelt");
	}
	
	//CountingSort Hilfarray
	public static void zaehleWerte() {
		Arrays.fill(anzahlWerte,0);
		
		for(int i : wuerfelWerte) {
			anzahlWerte[i] += 1;
		}
	}
	
	public static void starteComboChecker() {
		//berchne Punkte von jeder KLasse muss aufgerufen werden
		// Instanzen der Subklassen erstellen
		System.out.println("combochecker wird gestartet");
	    Combos einser = new Einser();
	    Combos zweier = new Zweier();
	    Combos dreier = new Dreier();
	    Combos vierer = new Vierer();
	    Combos fuenfer = new Fuenfer();
	    Combos sechser = new Sechser();
	    
	    Combos dreierPash = new DreierPash();
	    Combos viererPash = new ViererPash();
	    Combos fullHouse = new FullHouse();
	    Combos grosseStrasse = new GrosseStrasse();
	    Combos kleineStrasse = new KleineStrasse();
	    Combos chance = new Chance();
	    Combos kniffel = new Kniffel();
	    
	    Combos[] combosAsArray = {einser, zweier, dreier, vierer, fuenfer, sechser, dreierPash, viererPash, fullHouse, grosseStrasse, 
	    		kleineStrasse, chance, kniffel};
	    
	    for (Combos combo : combosAsArray) {
	    	combo.berechnePunkte();
	    }

////	     berechnePunkte() für jede Instanz aufrufen
//	    einser.berechnePunkte();
//	    zweier.berechnePunkte();
//	    dreier.berechnePunkte();
//	    vierer.berechnePunkte();
//	    fuenfer.berechnePunkte();
//	    sechser.berechnePunkte();
//	    
//	    dreierPash.berechnePunkte();
//	    viererPash.berechnePunkte();
//	    fullHouse.berechnePunkte();
//	    grosseStrasse.berechnePunkte();
//	    kleineStrasse.berechnePunkte();
//	    kniffel.berechnePunkte();
//	    chance.berechnePunkte();
	}
	
	
	
	
	
	//Getter/Setter
	public static int ermittleAugenzahl() {
		augenZahl = 0;
		for(int i : wuerfelWerte) {
			augenZahl += i;
		}
		return augenZahl;
	}
	public static int[] getWuerfelWerte() {
		return wuerfelWerte;
	}
	public static void setWuerfelWerte(int[] wuerfelWerte) {
		Wuerfel.wuerfelWerte = wuerfelWerte;
	}
	
	public static void setGehalteneWuerfel(boolean[] gehalteneWuerfel) {
		Wuerfel.gehalteneWuerfel = gehalteneWuerfel;
	}

	public static int[] getAnzahlWerte() {
		return anzahlWerte;
	}

	public static void setAnzahlWerte(int[] anzahlWerte) {
		Wuerfel.anzahlWerte = anzahlWerte;
	}

	public static int getWurfZahl() {
		return wurfZahl;
	}

	public static void setWurfZahl(int wurfZahl) {
		Wuerfel.wurfZahl = wurfZahl;
	}

	public static boolean isFertigGewuerfelt() {
		return fertigGewuerfelt;
	}

	public static void setFertigGewuerfelt(boolean fertigGewuerfelt) {
		Wuerfel.fertigGewuerfelt = fertigGewuerfelt;
	}

	public static int getAugenzahl() {
		return augenZahl;
	}

	public static void setAugenzahl(int augenZahl) {
		Wuerfel.augenZahl = augenZahl;
	}
	
	
}
