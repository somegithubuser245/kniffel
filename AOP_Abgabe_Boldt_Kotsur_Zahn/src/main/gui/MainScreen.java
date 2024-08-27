package main.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

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
	
	public static int[] wuerfelErgebnis = { 1, 2, 3, 4, 5 }; // von Niclas : array zum anzeigen der Würfel
	private static boolean[] wuerfelGehalten = { false, false, false, false, false }; // für niclas : array mit user
																						// auswahl
	public static int anzahlWuerfe = 0;
	public static int anzahlSpieler = 6; // wird in StartScreen gewählt und übergeben : für dani
					//TODO				= GameController.getSpielerAnzahl();
	
	public int currentPlayer = 1; // 0-5 für 6 spieler , muss von GameController gesetzt werden (und in GUI
									// aktiviert) : dani
				//TODO		= GameController.getCurrentPlayer();
	
	
	// wird in gui gesetzt und muss in die spieler instanzen

	public static String[] spielerNamen = { "david", "niclas", "dani", "tobias", "lordi", "nora" };
				//TODO					= GameController.getSpielerNamen();
	
	
	// punkteAnzeige Mischung aus *Berechnet und *Real -> von Dani (und Niclas)
	public static int[][] punkteAnzeige = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
	//TODO punkteAnzeige = punkteTabelle.getPunkteAnzeige
	public static int[][] punkteBerechnet = { { 115, 115, 115, 115, 115, 115 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 15, 15, 15, 15, 15 }, { 15, 0, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 0, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 15, 15, 15, 15, 15 }, { 15, 0, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 15, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 15, 15, 15, 15, 15 }, { 15, 0, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 15, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 }, { 15, 15, 15, 15, 15, 15 },
			{ 15, 0, 15, 15, 15, 15 } };
	//TODO punkteBerechnet = punkteTabelle.getPunkteBerechnet
	// punkteReal[6][21] und punkteAnzeige[6][21] (länge der arrays in den brackets)

	public static int[][] punkteReal = { { 10, 15, 0, 0, 0, 1000 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }, { 15, -1, -1, 0, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, 0, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }, { 0, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, 15, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, 0 },
			{ -1, -1, 16, -1, -1, -1 }, { -1, 4, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, 10 },
			{ -1, -1, -1, -1, 16, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 } };
	//TODO punkteReal = punkteTabelle.getPunkteReal

	
	
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
	String comboSpielerAuswahl;
	boolean nullPunkteWarnung = false;

	public MainScreen() {
		// DataFunktion zum Test
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 6; y++) {
				punkteAnzeige[x][y] = x + y;
			}
		}
		
		System.out.println("Current Player = "+spielerNamen[currentPlayer]+" (Nr. "+currentPlayer+")");
		
		refreshTable();

		setWindowOptions();

		addWuerfelMenu();

		// Tabelle rechts
		addScoreTable();

		// Bestätigen Knopf
		addTableUI();

		// Elemente in den JFrame packen
		add(leftPanel, BorderLayout.WEST);
		add(comboAnzeige, BorderLayout.CENTER);
		add(wuerfeln, BorderLayout.SOUTH);

		// GUI sichtbar machen
		setVisible(true);

		

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
			JLabel hinweis1 = new JLabel("    Klicke Würfel");
			JLabel hinweis2 = new JLabel("zum Halten!");
			
			if (anzahlWuerfe == 0) {
				dicePanel.add(hinweis1);
				dicePanel.add(hinweis2);
			}
			wuerfeln();
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

		// Code, der ausgeführt wird, wenn der Button geklickt wird
		if (anzahlWuerfe == 3) {
			System.out.println("Max Würfelzahlerreicht");
		}
		if (anzahlWuerfe == 1 || anzahlWuerfe == 2) {

			System.out.println("Wird gewürfelt");
			for (int i = 0; i < 5; i++) {
				wuerfelButtons[i].wuerfeln(wuerfelErgebnis[i]);
				System.out.println("" + anzahlWuerfe + ". Wurf: Würfel " + (i + 1) + ": " + wuerfelButtons[i].getValue()
						+ " (Gehalten = " + wuerfelButtons[i].gehalten() + ")");
				wuerfelErgebnis[i] = anzahlWuerfe;

			}
			anzahlWuerfe++;
			System.out.println("anzahl Würfe: " + anzahlWuerfe);

		}
		if (anzahlWuerfe == 0) {

			for (int i = 0; i < 5; i++) {
				System.out.println("erster wurf, erstelle würfel: " + i);
				wuerfelButtons[i] = new GUIWuerfel(wuerfelErgebnis[i]); // Create an instance of GUIWuerfel
				dicePanel.add(wuerfelButtons[i]);
				wuerfelErgebnis[i] = anzahlWuerfe;

			}

			anzahlWuerfe++;
			System.out.println("anzahlWuerfe=" + anzahlWuerfe);
		}

	}

	private void setWindowOptions() {
		// Fenster-Eigenschaften setzen
		setTitle("Kniffel Spiel");
		setSize(windowSizeWidth, windowSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	public static boolean[] getWuerfelGehalten() {
		return wuerfelGehalten;
	}

	public static void setWuerfelGehalten(boolean[] wuerfelGehalten) {
		for (int i = 0; i < 5; i++) {
			MainScreen.wuerfelGehalten[i] = wuerfelButtons[i].gehalten();

		}
	}

	private void refreshTable() {
		// aktualisiert das data Array und die JTable
		data = new String[][] {
				{ spielerNamen[0], spielerNamen[1], spielerNamen[2], spielerNamen[3], spielerNamen[4],
						spielerNamen[5] },
				{ "" + punkteAnzeige[0][0], "" + punkteAnzeige[0][1], "" + punkteAnzeige[0][2],
						"" + punkteAnzeige[0][3], "" + punkteAnzeige[0][4], "" + punkteAnzeige[0][5] },
				{ "" + punkteAnzeige[1][0], "" + punkteAnzeige[1][1], "" + punkteAnzeige[1][2],
						"" + punkteAnzeige[1][3], "" + punkteAnzeige[1][4], "" + punkteAnzeige[1][5] },
				{ "" + punkteAnzeige[2][0], "" + punkteAnzeige[2][1], "" + punkteAnzeige[2][2],
						"" + punkteAnzeige[2][3], "" + punkteAnzeige[2][4], "" + punkteAnzeige[2][5] },
				{ "" + punkteAnzeige[3][0], "" + punkteAnzeige[3][1], "" + punkteAnzeige[3][2],
						"" + punkteAnzeige[3][3], "" + punkteAnzeige[3][4], "" + punkteAnzeige[3][5] },
				{ "" + punkteAnzeige[4][0], "" + punkteAnzeige[4][1], "" + punkteAnzeige[4][2],
						"" + punkteAnzeige[4][3], "" + punkteAnzeige[4][4], "" + punkteAnzeige[4][5] },
				{ "" + punkteAnzeige[5][0], "" + punkteAnzeige[5][1], "" + punkteAnzeige[5][2],
						"" + punkteAnzeige[5][3], "" + punkteAnzeige[5][4], "" + punkteAnzeige[5][5] },
				{ "" + punkteAnzeige[6][0], "" + punkteAnzeige[6][1], "" + punkteAnzeige[6][2],
						"" + punkteAnzeige[6][3], "" + punkteAnzeige[6][4], "" + punkteAnzeige[6][5] },
				{ "" + punkteAnzeige[7][0], "" + punkteAnzeige[7][1], "" + punkteAnzeige[7][2],
						"" + punkteAnzeige[7][3], "" + punkteAnzeige[7][4], "" + punkteAnzeige[7][5] },
				{ "" + punkteAnzeige[8][0], "" + punkteAnzeige[8][1], "" + punkteAnzeige[8][2],
						"" + punkteAnzeige[8][3], "" + punkteAnzeige[8][4], "" + punkteAnzeige[8][5] },
				{ "" + punkteAnzeige[9][0], "" + punkteAnzeige[9][1], "" + punkteAnzeige[9][2],
						"" + punkteAnzeige[9][3], "" + punkteAnzeige[9][4], "" + punkteAnzeige[9][5] },
				{ "" + punkteAnzeige[10][0], "" + punkteAnzeige[10][1], "" + punkteAnzeige[10][2],
						"" + punkteAnzeige[10][3], "" + punkteAnzeige[10][4], "" + punkteAnzeige[10][5] },
				{ "" + punkteAnzeige[11][0], "" + punkteAnzeige[11][1], "" + punkteAnzeige[11][2],
						"" + punkteAnzeige[11][3], "" + punkteAnzeige[11][4], "" + punkteAnzeige[11][5] },
				{ "" + punkteAnzeige[12][0], "" + punkteAnzeige[12][1], "" + punkteAnzeige[12][2],
						"" + punkteAnzeige[12][3], "" + punkteAnzeige[12][4], "" + punkteAnzeige[12][5] },
				{ "" + punkteAnzeige[13][0], "" + punkteAnzeige[13][1], "" + punkteAnzeige[13][2],
						"" + punkteAnzeige[13][3], "" + punkteAnzeige[13][4], "" + punkteAnzeige[13][5] },
				{ "" + punkteAnzeige[14][0], "" + punkteAnzeige[14][1], "" + punkteAnzeige[14][2],
						"" + punkteAnzeige[14][3], "" + punkteAnzeige[14][4], "" + punkteAnzeige[14][5] },
				{ "" + punkteAnzeige[15][0], "" + punkteAnzeige[15][1], "" + punkteAnzeige[15][2],
						"" + punkteAnzeige[15][3], "" + punkteAnzeige[15][4], "" + punkteAnzeige[15][5] },
				{ "" + punkteAnzeige[16][0], "" + punkteAnzeige[16][1], "" + punkteAnzeige[16][2],
						"" + punkteAnzeige[16][3], "" + punkteAnzeige[16][4], "" + punkteAnzeige[16][5] },
				{ "" + punkteAnzeige[17][0], "" + punkteAnzeige[17][1], "" + punkteAnzeige[17][2],
						"" + punkteAnzeige[17][3], "" + punkteAnzeige[17][4], "" + punkteAnzeige[17][5] },
				{ "" + punkteAnzeige[18][0], "" + punkteAnzeige[18][1], "" + punkteAnzeige[18][2],
						"" + punkteAnzeige[18][3], "" + punkteAnzeige[18][4], "" + punkteAnzeige[18][5] } };

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
			currentPlayer++;
			anzahlWuerfe = 0;
			nextPlayer.setEnabled(false);
			confirm.setText("Zeile in Tabelle auswählen...");
			wuerfeln.setEnabled(true);

			// entferne die Würfel
			dicePanel.removeAll();

			// wüerfelButtons[] frei machen
			for (int i = 0; i < wuerfelButtons.length; i++) {
				wuerfelButtons[i] = null;
			}

			// Revalidate and repaint the dice panel to update the UI
			dicePanel.revalidate();
			dicePanel.repaint();
			System.out.println("Current Player = " + currentPlayer);
			

		});
		nextPlayer.setEnabled(false);
		JPanel tableButtons = new JPanel(new GridLayout(1, 2));
		tableButtons.add(confirm);
		tableButtons.add(nextPlayer);
		comboAnzeige.add(tableButtons, BorderLayout.SOUTH);
	}
	
	
	private void addScoreTable() {
		// array für 1. zeile labels
		String[] ersteZeile = { "Name", "Einser", "Zweier", "Dreier", "Vierer", "Fünfer", "Sechser", "Summe",
				"Bonus (ab 63)", "Oben Gesamt", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
				"Große Straße", "Kniffel", "Chance", "Unten Gesamt", "Oben Gesamt", "Gesamtpunkte" };

		// neues JPanel für Tabelle erstellen
		comboAnzeige = new JPanel(new BorderLayout());
		JPanel tabelle = new JPanel(new GridBagLayout());
		// GridBagLAyout Setup
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		
		c.fill = GridBagConstraints.BOTH; // Komponenten können sich ausbreiten
		c.insets = new Insets(2, 2, 2, 2); // Padding pro Zelle
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;		
		//gleiche Größe für alle Elemente
		Dimension sizeFeld = new Dimension((windowSizeWidth/10), (windowSizeHeight/25));
		
		//zeilen namen einfügen
		for (int i = 0; i < ersteZeile.length; i++) {		
			c.gridy = i;
			c.gridx = 0;
			JLabel zeile = new JLabel(ersteZeile[i]);
			zeile.setMinimumSize(sizeFeld);
			tabelle.add(zeile, c);
		}		
		// restliche zellen füllen	
		for (int y = 0; y < 20; y++) {
			c.gridy = y;
			for (int x = 0; x < anzahlSpieler; x++) {		
				//x=spalten, y=zeilen		
				c.gridx = x + 1;
				// spielernamen (1. zeile, y==0) als jlabel
				if (y == 0) {
					JLabel name = new JLabel(data[y][x]);
					name.setMinimumSize(sizeFeld);
					name.setBackground(new Color(200, 200, 200));
			        name.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(name, c);
				}
				// gesamtpunkte als jlabel (zeilen mit zwischenrechnung 7,8,9,17,18,19)
				else if (y == 7 || y == 8 || y == 9 || y == 17 || y == 18 || y == 19) {
					JLabel zwRechnung = new JLabel(data[y][x]);
					zwRechnung.setMinimumSize(sizeFeld);
			        zwRechnung.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(zwRechnung, c);
				}
				// neue punkte als button
				else if (punkteReal[y][x] < 0 && x == (currentPlayer)) {
					JButton neuePkt = new JButton(data[y][x]);
					String stringCombo = ersteZeile[y];
					int punkte = punkteBerechnet[y][x];
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
						punkteSpielerAuswahl = punkte;
						comboSpielerAuswahl = stringCombo;
					});
					tabelle.add(neuePkt, c);
				} // bestehende Punkte als label
				else if (punkteReal[y][x] >= 0 || x != (currentPlayer)) {
					JLabel bestandPkt = new JLabel(data[y][x]);
					bestandPkt.setMinimumSize(sizeFeld);
			        bestandPkt.setHorizontalAlignment(SwingConstants.CENTER);
					tabelle.add(bestandPkt, c);
				}				
			}
		}		
		comboAnzeige.add(tabelle, BorderLayout.CENTER);
		tabelle.revalidate();
		tabelle.repaint();
	}

	private int getPunkteSpielerAuswahl() {
		// TODO Auto-generated method stub
		return punkteSpielerAuswahl;
	}

	// TODO fertig -> currentPlayer++; anzahlWuerfe = 0; fertig deaktivieren,
	// bestätigen resetten

	public String getComboSpielerAuswahl() {
		return comboSpielerAuswahl;
	}
}