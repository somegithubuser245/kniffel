package main.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

import java.awt.*;
import main.data.*;
import main.logic.*;

/*die Klasse übernimmt das komplette Hauptfenster des Spiels, die Würfelanzeige, Punkte-Tabelle, Spielernamen 
 *und Input für "Würfeln", "Würfel Halten", "Punkte eintragen", "Bestätigen", "nächster Spieler"
 *
 *
 *TODO methoden/klassen aufteilen?
 *
 *TODO brauche von DANI die drei PunkteTabellen um mit der Tabelle weiter zu machen
 *		TODO bestätigen lockt die Punkte ein, speichert sie ab und gibt sie an PunkteTabelle.java weiter (muss DANI machen)
 *		TODO je nach punkteReal müssen zellen andere Farben haben und andere 
 *TODO auf spielerAnzahl reagieren (weniger spalten)
 *TODO arrays auf [spielerAnzahl][21] ändern!
 *TODO fertig knopf muss neue MainScreen Instanz starten @dani
*/
public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L; // ???

	private int  windowSizeWidth = 1000;
	private int  windowSizeHeight = 800;
	
	public static int[] wuerfelErgebnis = new int[5]; // von Niclas : array zum anzeigen der Würfel
	private static boolean[] wuerfelGehalten = { false, false, false, false, false }; // für niclas : array mit user
																						// auswahl
	public static int anzahlWuerfe = 0;
	public static int anzahlSpieler = 6; // wird in StartScreen gewählt und übergeben : für dani
					//TODO				= GameController.getSpielerAnzahl();
	
	public int currentPlayer = 0; // 0-5 für 6 spieler , muss von GameController gesetzt werden (und in GUI
									// aktiviert) : dani
				//TODO		= GameController.getCurrentPlayer();
	
	
	// wird in gui gesetzt und muss in die spieler instanzen
	
	//setSpielerNamenDefault neue methode
	public static String[] spielerNamen = new String[6];
	
	
	
	// punkteAnzeige Mischung aus *Berechnet und *Real -> von Dani (und Niclas)
	public static int[][] punkteAnzeige = PunkteTabelle.getPunkteAnzeige();
	
	public static int[] punkteBerechnet = PunkteTabelle.getPunkteBerechnet();

	//TODO punkte berechnet ist 1D und muss nur für aktuellen Spieler

	// punkteReal[6][21] und punkteAnzeige[6][21] (länge der arrays in den brackets)
	public static int[][] punkteReal = PunkteTabelle.getPunkteReal();


	
	
	// data für die anzeigeTabelle, muss nicht(!) geändert werden, liest dynamisch
	// die Werte aus punkteAnzeige als String ein!!!
	private static String[][] data;

	// GUI-Komponenten
	private JPanel leftPanel;
	private JButton wuerfeln;
	private JButton confirm;
	JButton nextPlayer;
	private JPanel comboAnzeige;
	

	private JPanel dicePanel;
	private static GUIWuerfel[] wuerfelButtons = new GUIWuerfel[5]; // initiert ein array für 5 instanzen der Würfel
																	// JButtons

	// für Tabellen Arbeit
	// private static String[] columnNames = { "Kombination", "Spieler 1", "Spieler
	// 2", "Spieler 3", "Spieler 4",
	// "Spieler 5", "Spieler 6" };
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

		// GUI sichtbar machen
		setVisible(true);

	}


	private void addToJFrame() {
		add(leftPanel, BorderLayout.WEST);
		add(comboAnzeige, BorderLayout.CENTER);
		add(wuerfeln, BorderLayout.SOUTH);
	}


	private void addWuerfelMenu() {
		// Linkes Panel für Spielername und Würfel
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// Spielername oben links
		JLabel playerName = new JLabel(spielerNamen[currentPlayer]);
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		leftPanel.add(playerName, BorderLayout.NORTH);

		// Panel für die Würfel unter dem Spielernamen
		dicePanel = new JPanel();
		dicePanel.setPreferredSize(new Dimension(200, 400)); // Adjust dimensions as needed

		dicePanel.setLayout(new GridLayout(5, 2)); // 3 Reihen(+2 als Puffer), 2 Spalten

		// füge 5 Würfel hinzu

		// TODO würfel zu dicePanel adden und anzeigen lassen!
		// TODO set würfel text mit array
		// TODO set würfel gehalten + farbe über eventlistener und ändere array

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
			wuerfeln();


			//Zusatz GUI infos 
			if (anzahlWuerfe == 0) {
				dicePanel.add(hinweis1);
				dicePanel.add(hinweis2);
			}
			
			if (anzahlWuerfe == 1) {				
				dicePanel.add(hinweisWurfZahl);
				hinweisWurfZahl.setText(""+anzahlWuerfe+". Wurf");
				
				
			} else if (anzahlWuerfe > 1) {
				hinweisWurfZahl.setText(""+anzahlWuerfe+". Wurf");
			}
			if (anzahlWuerfe == 3) {
				wuerfeln.setBackground(Color.GRAY);
				wuerfeln.setEnabled(false);
				hinweisWurfZahl.setText("Wähle Kombo");
			}
			dicePanel.repaint();
			dicePanel.revalidate();
			leftPanel.revalidate(); // Layout wird neu berechnet
			leftPanel.repaint(); // Panel wird neu gezeichnet

		});

		leftPanel.add(dicePanel, BorderLayout.CENTER);
	}

	public void wuerfeln() {
		// Hinzufügen eines ActionListeners mit Lambda-Ausdruck
		System.out.println("GUI würfeln() ausgeführt");
		//gehaltene Würfel and Wuefel-Klasse übergeben damit diese nicht geändert werden
		if(anzahlWuerfe > 0) uebergebeGehalteneWuerfel();

		//gamecontroller aktiviert würfel funktion der würf.Klasse und übergibt GUI das würfelergebnis
		wuerfelErgebnis = GameController.wuerfeln();

		// Code, der ausgeführt wird, wenn der Button geklickt wird
		if (anzahlWuerfe == 3) {
			System.out.println("Max Würfelzahlerreicht");
		}
		if (anzahlWuerfe == 1 || anzahlWuerfe == 2) {

			System.out.println("Wird gewürfelt");
			for (int i = 0; i < 5; i++) {
				//wuerfelErgebnis von Wuerfel Klasse
				wuerfelButtons[i].wuerfeln(wuerfelErgebnis[i]);
				System.out.println("" + anzahlWuerfe + ". Wurf: Würfel " + (i + 1) + ": " + wuerfelButtons[i].getValue()
						+ " (Gehalten = " + wuerfelButtons[i].gehalten() + ")");
				

			}
			anzahlWuerfe++;
			System.out.println("anzahl Würfe: " + anzahlWuerfe);
		}
		if (anzahlWuerfe == 0) {

			for (int i = 0; i < 5; i++) {
				System.out.println("erster wurf, erstelle würfel: " + i);
				wuerfelButtons[i] = new GUIWuerfel(wuerfelErgebnis[i]); // Create an instance of GUIWuerfel
				dicePanel.add(wuerfelButtons[i]);

			}

			anzahlWuerfe++;
			System.out.println("anzahlWuerfe=" + anzahlWuerfe);
		}
		
		//entfernt comboAnzeige JPanel und aktualisiert dann die ScoreTable und fügt dann die comboAnzeige wieder ein 
		refreshComboAnzeige();
	}

	private void setWindowOptions() {
		// Fenster-Eigenschaften setzen
		setTitle("Kniffel Spiel");
		setSize(windowSizeWidth, windowSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	public static void uebergebeGehalteneWuerfel() {
		for (int i = 0; i < 5; i++) {
			MainScreen.wuerfelGehalten[i] = wuerfelButtons[i].gehalten();
		}
		Wuerfel.setGehalteneWuerfel(wuerfelGehalten);
	}

	private void addTableUI() {

		confirm = new JButton("Zeile in Tabelle auswählen...");
		confirm.addActionListener(e -> {
			System.out.println("Auswahl Bestätigt: " + getComboSpielerAuswahl() + " mit " + getPunkteSpielerAuswahl()
					+ " Punkten.");
			// TODO bestätigen knopf speichert valueat in die

			// 2 möglichkeiten: 0 Punkte->Bestätigen lassen->dann "fertig knopf" aktivieren
			// / mehr als Null Punkte, dann eintragen
			if (punkteSpielerAuswahl != 0 && !nullPunkteWarnung) {
				System.out.println(
						"Gespeichert: Kombi: " + getComboSpielerAuswahl() + " mit Wert: " + getPunkteSpielerAuswahl());
				// TODO punkteSpielerAuswahl in dem punkteReal Array speichern (muss gamecontroller machen) @dani
				nextPlayer.setEnabled(true);
			} else if (nullPunkteWarnung) {
				confirm.setText("" + getComboSpielerAuswahl() + " gestrichen :(");
				nextPlayer.setEnabled(true);
			} else if (punkteSpielerAuswahl == 0) {
				nullPunkteWarnung = true;
				confirm.setText("NULL PUNKTE EINTRAGEN?");
				confirm.setOpaque(true); // Ensure the button is opaque
				confirm.setBackground(Color.RED);
				confirm.revalidate();
				confirm.repaint();
			}

		});
		nextPlayer = new JButton("Fertig");
		// TODO reset der GUI wenn "fertig" !!! achtung sollte nicht mehr nötig sein
		// wenn gamecontroller neue instanzen aufruft !!! @dani
		nextPlayer.addActionListener(e -> {
			//übergibt combo an gamecontroller als index in combo array der gamecontroller klasse
			GameController.chooseCombination(comboSpielerAuswahl);
			//nextplayer im gamecontroller ausführen und mit return wert currentplayer aktualisieren
			currentPlayer = GameController.nextPlayer();

			//reset values
			anzahlWuerfe = 0;
			nextPlayer.setEnabled(false);
			confirm.setText("Zeile in Tabelle auswählen...");
			wuerfeln.setEnabled(true);
			punkteReal = PunkteTabelle.getPunkteReal();

			
		
			// entferne die Würfel
			dicePanel.removeAll();

			// wüerfelButtons[] frei machen
			for (int i = 0; i < wuerfelButtons.length; i++) {
				wuerfelButtons[i] = null;
			}

			 // Remove all components from the JFrame
		    getContentPane().removeAll();

		    // Rebuild the UI
		    setWindowOptions();  // Assuming this method sets up the basic JFrame settings
		    addWuerfelMenu();    // Re-add the dice menu and components
		    addScoreTable();     // Re-add the score table
		    addTableUI();        // Re-add the confirm and nextPlayer buttons
		    addToJFrame();
		    
		    // Revalidate and repaint the JFrame
		    revalidate();
		    repaint();
			
			System.out.println("GUI: Current Player = " + currentPlayer);
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

		c.fill = GridBagConstraints.BOTH; // Komponenten können sich ausbreiten
		c.insets = new Insets(2, 2, 2, 2); // Padding pro Zelle
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;		
		//gleiche Größe für alle Elemente
		Dimension sizeFeld = new Dimension((windowSizeWidth/10), (windowSizeHeight/25));
		
		//combonamen einfügen
		for (int i = 0; i < ersteSpalte.length; i++) {		
			c.gridy = i;
			c.gridx = 0;
			JLabel zeile = new JLabel(ersteSpalte[i]);
			zeile.setMinimumSize(sizeFeld);
			tabelle.add(zeile, c);
		}		
		// restliche zellen füllen	
		
		//namen einfügen (erste Zeile)
		for(int i = 0; i < anzahlSpieler; i++){
			c.gridy = 0;
			c.gridx = i+1;
			JLabel name = new JLabel(spielerNamen[i]);
			name.setMinimumSize(sizeFeld);
			name.setBackground(new Color(200, 200, 200));
			name.setHorizontalAlignment(SwingConstants.CENTER);
			tabelle.add(name, c);
		}
		//restliche zellen einfügen (außer spieler-namen und combo-bezeichnungen)
		for (int reihe = 1; reihe < anzahlSpieler+1; reihe++) {
			c.gridx = reihe;
			for(int zeile = 1; zeile < 20; zeile++){
				c.gridy = zeile;
				//feld ist eintragbar
				if(punkteReal[reihe-1][zeile-1] < 0){
					
				}
				if (punkteReal[reihe-1][zeile-1] < 0 && (reihe-1) == currentPlayer) { 
					
					JButton neuePkt = new JButton(""+punkteAnzeige[reihe-1][zeile-1]);
					String stringCombo = ersteSpalte[zeile];
					int zeileCombo = zeile;
					int punkte = Integer.parseInt(""+punkteAnzeige[reihe-1][zeile-1]);
					neuePkt.setMinimumSize(sizeFeld);
					neuePkt.setPreferredSize(sizeFeld);
					//neuePkt.setBorder(BorderFactory.createEmptyBorder());
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
						if((zeileCombo-1) <= 5){
							comboSpielerAuswahl = zeileCombo-1;
						}else if((zeileCombo-1) >= 9){
							comboSpielerAuswahl = zeileCombo-5;
						}		
					});
					tabelle.add(neuePkt, c);
				} else {
					
					JLabel bestandPkt = new JLabel("");
					if(punkteAnzeige[reihe-1][zeile-1] < 0){
						bestandPkt.setText("-");
					}else{
						bestandPkt.setText("" + punkteAnzeige[reihe-1][zeile-1]);
					}
					
					bestandPkt.setMinimumSize(sizeFeld);
			        bestandPkt.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(bestandPkt, c);
				
				}	
				/*for (int reihe = 0; reihe < anzahlSpieler; reihe++) {		
				//x=spalten, y=zeilen		
				c.gridx = reihe+1;
				// spielernamen (1. zeile, y==0) als jlabel
				
				// gesamtpunkte als jlabel (zeilen mit zwischenrechnung 7,8,9,17,18,19)
				else if (zeile == 7 || zeile == 8 || zeile == 9 || zeile == 17 || zeile == 18 || zeile == 19) {
					JLabel zwRechnung = new JLabel(""+punkteAnzeige[reihe][zeile-1]); 
					zwRechnung.setMinimumSize(sizeFeld);
			        zwRechnung.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(zwRechnung, c);
				}
				// neue punkte als button punkteReal fehlt name in 0. zeile, deshalb -1, reihe ist bei data 
				else  // bestehende Punkte als label
				//reihe zeile für punkte real weil [spielerAnzahl][Zeilen]
				else if (punkteReal[reihe][zeile-1] >= 0 || reihe != (currentPlayer)) {
					JLabel bestandPkt = new JLabel("" + punkteAnzeige[reihe][zeile-1]);
					bestandPkt.setMinimumSize(sizeFeld);
			        bestandPkt.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(bestandPkt, c);
				}	*/	
			}
		}		
		tabelle.revalidate();
		tabelle.repaint();
		comboAnzeige.add(tabelle, BorderLayout.CENTER);
	}


	private void removeComboAnzeige() {
    // Remove the comboAnzeige panel from its parent container
    if (comboAnzeige != null && comboAnzeige.getParent() != null) {
        getContentPane().remove(comboAnzeige); // Remove from JFrame
        revalidate(); // Refresh the layout
        repaint();    // Repaint the UI
    }
}
private void refreshComboAnzeige() {
    removeComboAnzeige(); // Remove the existing panel first

    // Update or recreate the comboAnzeige panel
    addScoreTable(); // Assuming this method recreates and updates the comboAnzeige panel
	// füge Table UI erneut ein
	addTableUI();

    // Add the updated panel back to the JFrame
    add(comboAnzeige, BorderLayout.CENTER);
    revalidate(); // Refresh the layout
    repaint();    // Repaint the UI
}


	private int getPunkteSpielerAuswahl() {
		// TODO Auto-generated method stub
		return punkteSpielerAuswahl;
	}

	// TODO fertig -> currentPlayer++; anzahlWuerfe = 0; fertig deaktivieren,
	// bestätigen resetten

	public int getComboSpielerAuswahl() {
		return comboSpielerAuswahl;
	}
}