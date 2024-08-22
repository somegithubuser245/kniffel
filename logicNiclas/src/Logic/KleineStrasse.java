package Logic;

public class KleineStrasse extends Combos{

	public KleineStrasse() {
		super("KleineStrasse");
	}

	//counter machen der bei eins beginnt und dann immer auf nachfolger checkt-->wenn count mind. 4 --> kleineStrasse == tru
	
	
	@Override
	public void berechnePunkte() {
		
		int counter = 0;		
		  System.out.println("Häufigkeiten: " + Wuerfel.getAnzahlWerte());

		    for (int i = 1; i <= 6; i++) {  // Durchlaufe die Zahlen 1 bis 6
		        if (Wuerfel.getAnzahlWerte()[i] > 0) {  // Wenn die Zahl mindestens einmal geworfen wurde
		            counter++;
		            // Debugging-Ausgabe: Counter-Wert anzeigen
		            System.out.println("Zahl: " + i + " -> Counter: " + counter);
		            if (counter >= 4) {     // Wenn vier aufeinanderfolgende Zahlen vorhanden sind
		                moeglicheComboPunkte.put(getComboName(), 30);
		                System.out.println("Kleine Straße erkannt!");
		                return;
		            }
		        } else {
		            counter = 0;  // Wenn eine Zahl nicht vorhanden ist, setze den Counter zurück
		            // Debugging-Ausgabe: Counter zurückgesetzt
		            System.out.println("Counter zurückgesetzt bei Zahl: " + i);
		        }
		    }
		    moeglicheComboPunkte.put(getComboName(), 0); // Keine kleine Straße gefunden
		    System.out.println("Keine kleine Straße gefunden, Counter am Ende: " + counter);
	}
}
