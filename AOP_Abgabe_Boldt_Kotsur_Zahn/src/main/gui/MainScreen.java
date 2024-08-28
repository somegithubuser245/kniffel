package main.gui;

import javax.swing.*;
import java.util.Arrays;

import java.awt.*;
import main.data.*;
import main.logic.*;

/*die Klasse übernimmt das komplette Hauptfenster des Spiels, die Würfelanzeige, Punkte-Tabelle, Spielernamen 
 *und Input für "Würfeln", "Würfel Halten", "Punkte eintragen", "Bestätigen", "nächster Spieler"
*/
public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L; // ???

	private int  windowSizeWidth = 1400;
	private int  windowSizeHeight = 1000;
	
	public static int[] wuerfelErgebnis = new int[5];
	private static boolean[] wuerfelGehalten = { false, false, false, false, false }; 
	public static int anzahlWuerfe = 0;
	public static int anzahlSpieler = 6;
	
	public int currentPlayer = 0; 
	
	
	
	//setSpielerNamenDefault neue methode
	public static String[] spielerNamen = new String[6];
	
	
	
	// punkteAnzeige Mischung aus *Berechnet und *Real -> von Dani (und Niclas)
	public static int[][] punkteAnzeige = PunkteTabelle.getPunkteAnzeige();
	
	public static int[] punkteBerechnet = PunkteTabelle.getPunkteBerechnet();


	// punkteReal[6][21] und punkteAnzeige[6][21] (länge der arrays in den brackets)
	public static int[][] punkteReal = PunkteTabelle.getPunkteReal();


	
	
	// GUI-Komponenten
	private JPanel leftPanel;
	private JButton wuerfeln;
	private JButton confirm;
	JButton nextPlayer;
	private JPanel comboAnzeige;
	
	//DebugMode 
	boolean debugged = false;

	private JPanel dicePanel;
	private static GUIWuerfel[] wuerfelButtons = new GUIWuerfel[5]; 

	
	int punkteSpielerAuswahl;
	String stringCombo;
	int comboSpielerAuswahl;
	boolean nullPunkteWarnung = false;

	public MainScreen() {
		spielerNamen = GameController.getSpielerNamen();
		anzahlSpieler = spielerNamen.length;
		
		System.out.println(Arrays.toString(spielerNamen));
		System.out.println("Current Player = "+spielerNamen[currentPlayer]+" (Nr. "+currentPlayer+")");

		setWindowOptions();
 
		addWuerfelMenu();

		// Tabelle rechts
		addScoreTable();

		// Bestätigen Knopf
		addTableUI();
		// Elemente in den JFrame packen
		addToJFrame();

		setVisible(true);

	}


	private void addToJFrame() {
		add(leftPanel, BorderLayout.WEST);
		add(comboAnzeige, BorderLayout.CENTER);
		add(wuerfeln, BorderLayout.SOUTH);
	}

	private void addWuerfelMenu() {
		
		// Linkes Panel für Spielername/Debug und Würfel
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		addPlayerName();

		// Panel für die Würfel unter Spielernamen
		dicePanel = new JPanel();
		dicePanel.setPreferredSize(new Dimension(300, 300)); 
		dicePanel.setLayout(new GridLayout(5, 2)); // 3 Reihen(+2 als Puffer), 2 Spalten
		
		// Hinweis, wie oft gewürfelt wurde
		JLabel hinweisWurfZahl = new JLabel("");
		
		// Würfeln Knopf
		wuerfeln = new JButton("Würfeln");
		wuerfeln.setPreferredSize(new Dimension(300, 50));
		
		wuerfeln.addActionListener(e -> {   
			System.out.println("GUI Würfeln Knopf gedrückt");
			JLabel hinweis1 = new JLabel("    Klicke Würfel");
			JLabel hinweis2 = new JLabel("zum Halten!");
			//eigentliche WuerfelMethode
			wuerfeln(false);

			//Zusatz GUI infos 
			if (anzahlWuerfe == 0) {
				dicePanel.add(hinweis1);
				dicePanel.add(hinweis2);
			}
			if (anzahlWuerfe == 1) {				
				dicePanel.add(hinweisWurfZahl);
				hinweisWurfZahl.setText("         >>>   "+anzahlWuerfe+". Wurf");	
			} else if (anzahlWuerfe > 1) {
				hinweisWurfZahl.setText("         >>>   "+anzahlWuerfe+". Wurf");
			}
			if (anzahlWuerfe == 3) {
				wuerfeln.setBackground(Color.GRAY);
				wuerfeln.setEnabled(false);
				hinweisWurfZahl.setText(" >>>   Wähle Kombo");
				hinweisWurfZahl.setHorizontalAlignment(SwingConstants.CENTER); 
			}
			dicePanel.repaint();
			dicePanel.revalidate();
			leftPanel.revalidate(); 
			leftPanel.repaint(); 
		});		
		leftPanel.add(dicePanel, BorderLayout.CENTER);
	    
		//Leeres Component um würfel von links einzurücken
		  JPanel leer1 = new JPanel();
		    leer1.setPreferredSize(new Dimension(100, 100)); 
		    leftPanel.add(leer1, BorderLayout.WEST);

		  JPanel leer2 = new JPanel();
		  leer2.setPreferredSize(new Dimension(100, 100)); 
		  leftPanel.add(leer2, BorderLayout.EAST);
	
	}


	private void addPlayerName() {
		// Spielername oben links
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		JLabel playerName = new JLabel("   >> "+spielerNamen[currentPlayer]+" <<      ");
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		JButton debug = new JButton("");
		debug.addActionListener(e -> {
			debugged = true;
		});
		JButton debugConfirm = new JButton(":)");
		debugConfirm.addActionListener(e -> {
			
			if(debugged) {
				 boolean[] halteAlle = {true, true, true, true, true};
				 int[] userInput = {1,1,1,1,1};
	            System.out.println("GUI [mainscreen] wuerfelknopf: starte DebugMode");  
	            //öffne DialogFenster mit user input für Würfel
	            DebugMode dialog = new DebugMode(this); // Create and show dialog
	            userInput = dialog.getUserInput(); // Get the result
	           //setze in würfelklasse die userinput werte und setze alle = gehalten
	            Wuerfel.setWuerfelWerte(userInput);
	            Wuerfel.setGehalteneWuerfel(halteAlle);
	            //wurfel mit debug = true damit logik angepasst wird
	            wuerfeln(true);
			}
		});
		namePanel.add(playerName, BorderLayout.WEST);
		namePanel.add(debug, BorderLayout.CENTER);
		namePanel.add(debugConfirm, BorderLayout.EAST);
		leftPanel.add(namePanel, BorderLayout.NORTH);
	}

	public void wuerfeln(boolean debug) {
		
		System.out.println("GUI [mainscreen] würfeln() ausgeführt - Debug = "+debug);
		//gehaltene Würfel and Wuefel-Klasse übergeben damit diese nicht geändert werden
		if(anzahlWuerfe > 0 && !debug) uebergebeGehalteneWuerfel();

		//gamecontroller aktiviert würfel funktion der würf.Klasse und übergibt GUI das würfelergebnis
		wuerfelErgebnis = GameController.wuerfeln();

		
		if (anzahlWuerfe == 1 || anzahlWuerfe == 2) {

			for (int i = 0; i < 5; i++) {
				//wuerfelErgebnis von Wuerfel Klasse
				wuerfelButtons[i].wuerfeln(wuerfelErgebnis[i]);
			}
		}
		if (anzahlWuerfe == 0) {
			if(wuerfelButtons != null) {
				dicePanel.removeAll();
			}
			addWuerfel();

			System.out.println("anzahlWuerfe=" + anzahlWuerfe);
		}
		if(!debug) anzahlWuerfe++;
		if(anzahlWuerfe == 3) wuerfeln.setEnabled(false);
		//entfernt comboAnzeige JPanel und aktualisiert dann die ScoreTable und fügt dann die comboAnzeige wieder ein 
		refreshComboAnzeige();
	}


	private void addWuerfel() {
		for (int i = 0; i < 5; i++) {
			Font wuerfelFont = new Font("Arial", Font.BOLD, 60); 
			wuerfelButtons[i] = new GUIWuerfel(wuerfelErgebnis[i]); 
			wuerfelButtons[i].setPreferredSize(new Dimension(100,100));
	        wuerfelButtons[i].setMinimumSize(new Dimension(100,100));
	        wuerfelButtons[i].setFont(wuerfelFont);
			dicePanel.add(wuerfelButtons[i]);

		}
	}

	
	public static void uebergebeGehalteneWuerfel() {
		for (int i = 0; i < 5; i++) {
			MainScreen.wuerfelGehalten[i] = wuerfelButtons[i].gehalten();
		}
		Wuerfel.setGehalteneWuerfel(wuerfelGehalten);
	}
	
	
	
	private void setWindowOptions() {
		//basic fenster einstellungen
		setTitle("Kniffel Spiel");
		setSize(windowSizeWidth, windowSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	
	private void addTableUI() {

		confirm = new JButton("Zeile in Tabelle auswählen...");
		confirm.setEnabled(false);
		confirm.addActionListener(e -> {
			System.out.println("Auswahl Bestätigt: " + getComboSpielerAuswahl() + " mit " + getPunkteSpielerAuswahl()
					+ " Punkten.");
			
			if (punkteSpielerAuswahl != 0 && !nullPunkteWarnung) {
				System.out.println(
						"Gespeichert: Kombi: " + getComboSpielerAuswahl() + " mit Wert: " + getPunkteSpielerAuswahl());
				
				nextPlayer.setEnabled(true);
			} else if (nullPunkteWarnung) {
				confirm.setText("" + getComboSpielerAuswahl() + " gestrichen :(");
				nextPlayer.setEnabled(true);
			} else if (punkteSpielerAuswahl == 0) {
				nullPunkteWarnung = true;
				confirm.setText("NULL PUNKTE EINTRAGEN?");
				confirm.setOpaque(true); 
				confirm.setBackground(Color.RED);
				confirm.revalidate();
				confirm.repaint();		
			}
	        

		});
		nextPlayer = new JButton("Fertig");
		nextPlayer.addActionListener(e -> {
			if(GameController.getGameOver()) {
				openSpielEndeDialog();
			}else {
			
			//übergibt combo an gamecontroller als index in combo array der gamecontroller klasse
			GameController.chooseCombination(comboSpielerAuswahl, punkteSpielerAuswahl);
			//nextplayer im gamecontroller ausführen und mit return wert currentplayer aktualisieren
			currentPlayer = GameController.nextPlayer();

			//resette werte
			anzahlWuerfe = 0;
			nextPlayer.setEnabled(false);
			confirm.setText("Zeile in Tabelle auswählen...");
			wuerfeln.setEnabled(true);
			punkteReal = PunkteTabelle.getPunkteReal();
			nullPunkteWarnung = false;
			
		
			// entferne die Würfel
			dicePanel.removeAll();

			// wüerfelButtons[] frei machen
			for (int i = 0; i < wuerfelButtons.length; i++) {
				wuerfelButtons[i] = null;
			}

			 // alles vom jframe löschen
		    getContentPane().removeAll();

		    // neu aufbauen
		    setWindowOptions();
		    addWuerfelMenu();    
		    addScoreTable();     
		    addTableUI();        
		    addToJFrame();
		    
		    //das hier muss halt immer
		    revalidate();
		    repaint();	        

			System.out.println("GUI: Current Player = " + currentPlayer);
			}
		});
		nextPlayer.setEnabled(false);
		JPanel tableButtons = new JPanel(new GridLayout(1, 2));
		tableButtons.add(confirm);
		tableButtons.add(nextPlayer);
		comboAnzeige.add(tableButtons, BorderLayout.SOUTH);
	}
	
	
	private void addScoreTable() {

		punkteAnzeige = Arrays.copyOf(PunkteTabelle.getPunkteAnzeige(),anzahlSpieler);
		
		
		// array für 1. zeile labels
		String[] ersteSpalte = { "Name", "Einser", "Zweier", "Dreier", "Vierer", "Fünfer", "Sechser", "Summe",
				"Bonus (ab 63)", "Oben Gesamt", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
				"Große Straße", "Kniffel", "Chance", "Unten Gesamt", "Oben Gesamt", "Gesamtpunkte" };

		// neues JPanel für Tabelle erstellen
		comboAnzeige = new JPanel(new BorderLayout());
		JPanel tabelle = new JPanel(new GridBagLayout());
		// GridBagLAyout Setup
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2, 2, 2, 2);
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;		
		//gleiche Größe für alle Elemente
		Dimension sizeFeld = new Dimension((windowSizeWidth/15), (windowSizeHeight/30));
		
		fillScoreTable(ersteSpalte, tabelle, c, sizeFeld);		
		tabelle.revalidate();
		tabelle.repaint();
		comboAnzeige.add(tabelle, BorderLayout.CENTER);
	}


	private void fillScoreTable(String[] ersteSpalte, JPanel tabelle, GridBagConstraints c, Dimension sizeFeld) {
		//combonamen einfügen
		for (int i = 0; i < ersteSpalte.length; i++) {		
			c.gridy = i;
			c.gridx = 0;
			JLabel zeile = new JLabel(ersteSpalte[i]);
			zeile.setMinimumSize(sizeFeld);
			zeile.setPreferredSize(sizeFeld);
			tabelle.add(zeile, c);
		}		
		// restliche zellen füllen	
		
		//namen einfügen (erste Zeile)
		for(int i = 0; i < anzahlSpieler; i++){
			c.gridy = 0;
			c.gridx = i+1;
			JLabel name = new JLabel(spielerNamen[i]);
			name.setMinimumSize(sizeFeld);
			name.setPreferredSize(sizeFeld);
			name.setBackground(new Color(200, 200, 200));
			name.setHorizontalAlignment(SwingConstants.CENTER);
			tabelle.add(name, c);
		}
		//restliche zellen einfügen (außer spieler-namen und combo-bezeichnungen)
		for (int reihe = 1; reihe < anzahlSpieler+1; reihe++) {
			c.gridx = reihe;
			for(int zeile = 1; zeile < 20; zeile++){
				c.gridy = zeile;
				//feld ist eintragbar -> button
				if (punkteReal[reihe-1][zeile-1] < 0 && (reihe-1) == currentPlayer && punkteAnzeige[reihe-1][zeile-1] >= 0) { 	
					JButton neuePkt = new JButton("");
					//minus 1 zu "-"
					if(punkteAnzeige[reihe-1][zeile-1] < 0){
						neuePkt.setText("-");
					}else{
						neuePkt.setText("" + punkteAnzeige[reihe-1][zeile-1]);
					}
					String stringCombo = ersteSpalte[zeile];
					int zeileCombo = zeile;
					int punkte = Integer.parseInt("" + punkteAnzeige[reihe-1][zeile-1]);
					neuePkt.setMinimumSize(sizeFeld);
					neuePkt.setPreferredSize(sizeFeld);
					neuePkt.setMargin(new Insets(0, 0, 0, 0));
					neuePkt.setOpaque(true);
					if(punkte==0) {
						neuePkt.setBackground(new Color(255, 200, 200));
					} else {
						neuePkt.setBackground(new Color(200, 255, 200));
					}
					// punkte int wir vor der lamda expr gespeichert und wenn lambda ausgeführt wird
					// das passende value genutzt
					// das wird auch variable capture genannt
					neuePkt.addActionListener(e -> {					

						confirm.setText("Als '" + stringCombo + "' eintragen (" + punkte + " Punkte)");
						//aktualisiert die SpielerAuswahl Variablen mit den für den Knopf zwischengespeicherten Variablen
						punkteSpielerAuswahl = punkte;
						//0-5 = {einser,zweier,...,sechser}
						comboSpielerAuswahl = zeileCombo-1;		
						confirm.setEnabled(true);
					});
					tabelle.add(neuePkt, c);
				//Jbutton
				} else {
					JLabel bestandPkt = new JLabel("");
					bestandPkt.setMinimumSize(sizeFeld);
					bestandPkt.setPreferredSize(sizeFeld);
					if(punkteAnzeige[reihe-1][zeile-1] < 0){
						bestandPkt.setText("-");
					}else{
						bestandPkt.setText("" + punkteAnzeige[reihe-1][zeile-1]);
					}	
					bestandPkt.setMinimumSize(sizeFeld);
			        bestandPkt.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(bestandPkt, c);			
				}			
			}
		}
	}


	private void removeComboAnzeige() {
    //entfernt alles von Jpanel comboanzeige
    if (comboAnzeige != null && comboAnzeige.getParent() != null) {
        getContentPane().remove(comboAnzeige); 
        revalidate(); 
        repaint();    
    }
}
	private void refreshComboAnzeige() {
    //hard reset damit die Tabelle richtige Werte hat
	removeComboAnzeige(); 
    addScoreTable(); 
	addTableUI();
    add(comboAnzeige, BorderLayout.CENTER);
    revalidate(); 
    repaint();    
    

}

	private void openSpielEndeDialog() {
	//JDIalog = kleines Fenster starten
    JDialog dialog = new JDialog(this, "Spielende", true);
    dialog.setSize(300, 100);
    dialog.setLocationRelativeTo(this);
    JPanel endPanel = new JPanel();
    endPanel.setLayout(new BorderLayout());
    
    JLabel gewinner = new JLabel();
    gewinner.setText("Der Gewinner ist: "+GameController.getGewinnerName()+" mit "+GameController.getGewinnerPunkte()+" Punkten :)" );
    endPanel.add(gewinner, BorderLayout.NORTH);
    JButton closeButton = new JButton("Spiel Beenden");
    closeButton.addActionListener(e -> {
    		//alles schließen
            dispose();
            dialog.dispose();     
    });
    
    endPanel.add(closeButton, BorderLayout.SOUTH);
    dialog.add(endPanel);
    dialog.setVisible(true);
}

	private int getPunkteSpielerAuswahl() {
	
		return punkteSpielerAuswahl;
	}



	public int getComboSpielerAuswahl() {
		return comboSpielerAuswahl;
	}
}