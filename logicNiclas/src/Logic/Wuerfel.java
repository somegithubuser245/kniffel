package Logic;

import java.util.Arrays;
import java.util.Random; 

public class Wuerfel {
	private static int[] wuerfelWerte;
	private static int[]gehalteneWuerfel;
	private static int[]anzahlWerte;// C_Feld
	private static int wurfZahl = 0;
	private static boolean fertigGewuerfelt = false;
	private static int augenZahl = 0;
	
	
	public static void wurfeln() {
		while(wurfZahl < 3) {
			for(int i = 0;i < 5;i++) {
				wuerfelWerte[i] = new Random().nextInt(6)+1;
				starteComboChecker();
				wuerfelHalten();
				
			}
			
			wurfZahl++;
			augenZahl = 0;
		}
		fertigGewuerfelt = true;
	}
	
	//CountingSort Hilfarray
	public static int[]zaehleWerte(int[]wuerfelWerte) {
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
	
	public static int[] wuerfelHalten() {
		//Würfel, die gehalten werden sollen, werden über GUI ausgewählt
		
		return gehalteneWuerfel;
	}
	
	public static void starteComboChecker() {
		
	}
	
	
	
	
	
	//Getter/Setter
	public static int ermittleAugenzahl(int[]wuerfelWerte) {
		for(int i : wuerfelWerte) {
			augenZahl += wuerfelWerte[i];
		}
		return augenZahl;
	}
	public static int[] getWuerfelWerte() {
		return wuerfelWerte;
	}

	public static void setWuerfelWerte(int[] wuerfelWerte) {
		Wuerfel.wuerfelWerte = wuerfelWerte;
	}

	public static int[] getGehalteneWuerfel() {
		return gehalteneWuerfel;
	}

	public static void setGehalteneWuerfel(int[] gehalteneWuerfel) {
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

	public static int getAugenZahl() {
		return augenZahl;
	}

	public static void setAugenZahl(int augenZahl) {
		Wuerfel.augenZahl = augenZahl;
	}
	
	
}
