package Logic;

import java.util.Arrays;
import java.util.Random; 

public class Wuerfel {
	private static int[] wuerfelWerte = new int[5];
	private static boolean[]gehalteneWuerfel = new boolean[5];
	private static int[]anzahlWerte = new int [7];// C_Feld
	private static int wurfZahl = 0;
	private static boolean fertigGewuerfelt;
	private static int augenZahl = 0;
	
	
	public static void wurfeln() {
		Arrays.fill(gehalteneWuerfel,false);// nur zu testzwecken
		fertigGewuerfelt = false;
		while(wurfZahl < 3) {
			for(int i = 0;i < 5;i++) {
				if (!gehalteneWuerfel[i]) {
					wuerfelWerte[i] = new Random().nextInt(6)+1;
				};
			}
			ermittleAugenzahl();
			starteComboChecker();
			zaehleWerte();
			wurfZahl++;
		}
		fertigGewuerfelt = true;
	}
	
	//CountingSort Hilfarray
	public static int[]zaehleWerte() {
		int[] anzahlWerte = new int[7];// von 0 bis 6 --> 0 kann man nicht Würfeln deshalb [0] immer 0
		Arrays.fill(anzahlWerte,0);
		
		for(int i : wuerfelWerte) {
			anzahlWerte[i] += 1;
		}
		
		for(int i = 1; i < anzahlWerte.length;i++) {
			anzahlWerte[i] += anzahlWerte[i -1];
		}
		
		return anzahlWerte;
	}
	
	public static void starteComboChecker() {
		//berchne Punkte von jeder KLasse muss aufgerufen werden
		// Instanzen der Subklassen erstellen
	    Combos zahlen = new Zahlen();
	    Combos dreierPash = new DreierPash();
	    Combos viererPash = new ViererPash();
	    Combos fullHouse = new FullHouse();
	    Combos grosseStrasse = new GrosseStrasse();
	    Combos kleineStrasse = new KleineStrasse();
	    Combos chance = new Chance();

	    // berechnePunkte() für jede Instanz aufrufen
	    zahlen.berechnePunkte();
	    dreierPash.berechnePunkte();
	    viererPash.berechnePunkte();
	    fullHouse.berechnePunkte();
	    grosseStrasse.berechnePunkte();
	    kleineStrasse.berechnePunkte();
	    chance.berechnePunkte();
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
