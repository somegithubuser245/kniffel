package main.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

/*die Klasse übernimmt das komplette Hauptfenster des Spiels, die Würfelanzeige, Punkte-Tabelle, Spielernamen 
 *und Input für "Würfeln", "Würfel Halten", "Punkte eintragen", "Bestätigen", "nächster Spieler"
 *
 *TODO warnung wenn mit 0 punkten bestätigt wird
 *		-> Es muss mehere Punkte Tabellen geben: 
 *			-punkteBerechnet: für die Punkte die mit den geworfenen Würfeln möglich sind
 *			-punkteReal: für die Punkte die der Spieler tatsächlich schon erreicht und eingetragen hat
 *			-punkteAnzeige: eine Mischung aus beidem: punkteReal müssen immer da bleiben, wo punkteReal noch leer muss punkteBerechnet ergänzt werden
 *
 *TODO methoden/klassen aufteilen?
 *
 *TODO brauche von DANI die drei PunkteTabellen um mit der Tabelle weiter zu machen
 *		TODO bestätigen lockt die Punkte ein, speichert sie ab und gibt sie an PunkteTabelle.java weiter (muss DANI machen)
 *		TODO je nach punkteReal müssen zellen andere Farben haben und andere 
 *TODO auf spielerAnzahl reagieren (weniger spalten)
*/
public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L; // ???

	public static int[] wuerfelErgebnis = { 1, 2, 3, 4, 5 }; // von Niclas : array zum anzeigen der Würfel
	private static boolean[] wuerfelGehalten = { false, false, false, false, false }; // für niclas : array mit user
																						// auswahl
	public static int anzahlWuerfe = 0;
	public static int anzahlSpieler = 6; // wird in StartScreen gewählt und übergeben : für dani
	public int currentPlayer = 1; // 0-5 für 6 spieler , muss von GameController gesetzt werden (und in GUI
									// aktiviert) : dani

	// wird in gui gesetzt und muss in die spieler instanzen

	public static String[] spielerNamen = { "david", "niclas", "dani", "tobias", "lordi", "nora" };

	// punkteAnzeige Mischung aus *Berechnet und *Real -> von Dani (und Niclas)
	public static int[][] punkteAnzeige = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

	// punkteReal[21][6] und punkteAnzeige[21][6] (länge der arrays in den brackets)

	public static int[][] punkteReal = { { 10, 15, 0, 0, 0, 1000 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }, { 15, -1, -1, 0, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, 0, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }, { 0, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, 15, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, 0 },
			{ -1, -1, 16, -1, -1, -1 }, { -1, 4, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, 10 },
			{ -1, -1, -1, -1, 16, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 } };

	// data für die anzeigeTabelle, muss nicht(!) geändert werden, liest dynamisch
	// die Werte aus punkteAnzeige als String ein!!!
	private static String[][] data;

	// GUI-Komponenten
	private JPanel leftPanel;
	private JButton wuerfeln;
	private JButton confirm;
	JButton nextPlayer;
	private JPanel comboAnzeige;
	private JTable scoreTable;
	private JScrollPane scrollPane;
	private JPanel dicePanel;
	private static GUIWuerfel[] wuerfelButtons = new GUIWuerfel[5]; // initiert ein array für 5 instanzen der Würfel
																	// JButtons
	// Farben
	Color dunkelGruen = new Color(0, 100, 0);

	// für Tabellen Arbeit
	private static String[] columnNames = { "Kombination", "Spieler 1", "Spieler 2", "Spieler 3", "Spieler 4",
			"Spieler 5", "Spieler 6" };
	private int rowScoreTable;
	private int columnScoreTable;
	private Object value;
	private Object combo;
	String stringValue;
	String stringCombo;
	boolean nullPunkteWarnung = false;

	public MainScreen() {
		// DataFunktion zum Test
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 6; y++) {
				punkteAnzeige[x][y] = x + y;
			}
		}
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

		// Add a selection listener to the table
		tableSelectionListener();

	}

	private void addTableUI() {

		confirm = new JButton("Zeile in Tabelle auswählen...");
		confirm.addActionListener(e -> {
			System.out.println("Auswahl Bestätigt: " + getStringCombo() + " mit " + getStringValue() + " Punkten.");
			// TODO bestätigen knopf speichert valueat in die
			// punkteReal[CombiRow][spielerNr]
			// int!!
			// punkte von String zu Int umwandeln
			int punkte = 0;
			try {
				punkte = Integer.parseInt(getStringValue());
			} catch (NumberFormatException f) {
				System.out.println("Der String konnte nicht in eine Ganzzahl umgewandelt werden.");
			}

			// 2 möglichkeiten: 0 Punkte->Bestätigen lassen->dann "fertig knopf" aktivieren
			// / mehr als Null Punkte, dann eintragen
			if (punkte != 0 && !nullPunkteWarnung) {
				System.out.println("Gespeichert an Kombi: " + getStringCombo() + "=index " + rowScoreTable
						+ " mit Wert: " + punkte);
				nextPlayer.setEnabled(true);
			} else if (nullPunkteWarnung) {
				confirm.setText("" + getStringCombo() + " gestrichen :(");
				nextPlayer.setEnabled(true);
			} else if (punkte == 0) {
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
		// wenn gamecontroller neue instanzen aufruft !!!
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

		});
		nextPlayer.setEnabled(false);
		JPanel tableButtons = new JPanel(new GridLayout(1, 2));
		tableButtons.add(confirm);
		tableButtons.add(nextPlayer);
		comboAnzeige.add(tableButtons, BorderLayout.SOUTH);
	}
	// TODO fertig -> currentPlayer++; anzahlWuerfe = 0; fertig deaktivieren,
	// bestätigen resetten

	public String getStringValue() {
		return stringValue;
	}

	public String getStringCombo() {
		return stringCombo;
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

		// Würfeln Knopf
		wuerfeln = new JButton("Würfeln");
		wuerfeln.setPreferredSize(new Dimension(300, 50));
		wuerfeln.addActionListener(e -> {
			JLabel hinweis1 = new JLabel("    Klicke Würfel");
			JLabel hinweis2 = new JLabel("zum Halten!");
			JLabel hinweisWurfZahl = new JLabel("" + (anzahlWuerfe + 1));
			if (anzahlWuerfe == 0) {
				dicePanel.add(hinweis1);
				dicePanel.add(hinweis2);
			}
			wuerfeln();
			if (anzahlWuerfe > 1) {
				dicePanel.remove(hinweisWurfZahl);
				dicePanel.add(hinweisWurfZahl);
			} else if (anzahlWuerfe == 1) {
				dicePanel.add(hinweisWurfZahl);
			}

			if (anzahlWuerfe == 3) {
				wuerfeln.setBackground(Color.GRAY);
				wuerfeln.setEnabled(false);
			}
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
		setSize(1000, 600);
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
				{ "Name:", spielerNamen[0], spielerNamen[1], spielerNamen[2], spielerNamen[3], spielerNamen[4],
						spielerNamen[5] },
				{ "Einser", "" + punkteAnzeige[0][0], "" + punkteAnzeige[0][1], "" + punkteAnzeige[0][2],
						"" + punkteAnzeige[0][3], "" + punkteAnzeige[0][4], "" + punkteAnzeige[0][5] },
				{ "Zweier", "" + punkteAnzeige[1][0], "" + punkteAnzeige[1][1], "" + punkteAnzeige[1][2],
						"" + punkteAnzeige[1][3], "" + punkteAnzeige[1][4], "" + punkteAnzeige[1][5] },
				{ "Dreier", "" + punkteAnzeige[2][0], "" + punkteAnzeige[2][1], "" + punkteAnzeige[2][2],
						"" + punkteAnzeige[2][3], "" + punkteAnzeige[2][4], "" + punkteAnzeige[2][5] },
				{ "Vierer", "" + punkteAnzeige[3][0], "" + punkteAnzeige[3][1], "" + punkteAnzeige[3][2],
						"" + punkteAnzeige[3][3], "" + punkteAnzeige[3][4], "" + punkteAnzeige[3][5] },
				{ "Fünfer", "" + punkteAnzeige[4][0], "" + punkteAnzeige[4][1], "" + punkteAnzeige[4][2],
						"" + punkteAnzeige[4][3], "" + punkteAnzeige[4][4], "" + punkteAnzeige[4][5] },
				{ "Sechser", "" + punkteAnzeige[5][0], "" + punkteAnzeige[5][1], "" + punkteAnzeige[5][2],
						"" + punkteAnzeige[5][3], "" + punkteAnzeige[5][4], "" + punkteAnzeige[5][5] },
				{ "Summe", "" + punkteAnzeige[6][0], "" + punkteAnzeige[6][1], "" + punkteAnzeige[6][2],
						"" + punkteAnzeige[6][3], "" + punkteAnzeige[6][4], "" + punkteAnzeige[6][5] },
				{ "Bonus (ab 63)", "" + punkteAnzeige[7][0], "" + punkteAnzeige[7][1], "" + punkteAnzeige[7][2],
						"" + punkteAnzeige[7][3], "" + punkteAnzeige[7][4], "" + punkteAnzeige[7][5] },
				{ "Oben Gesamt", "" + punkteAnzeige[8][0], "" + punkteAnzeige[8][1], "" + punkteAnzeige[8][2],
						"" + punkteAnzeige[8][3], "" + punkteAnzeige[8][4], "" + punkteAnzeige[8][5] },
				{ "Dreierpasch", "" + punkteAnzeige[9][0], "" + punkteAnzeige[9][1], "" + punkteAnzeige[9][2],
						"" + punkteAnzeige[9][3], "" + punkteAnzeige[9][4], "" + punkteAnzeige[9][5] },
				{ "Viererpasch", "" + punkteAnzeige[10][0], "" + punkteAnzeige[10][1], "" + punkteAnzeige[10][2],
						"" + punkteAnzeige[10][3], "" + punkteAnzeige[10][4], "" + punkteAnzeige[10][5] },
				{ "Full House", "" + punkteAnzeige[11][0], "" + punkteAnzeige[11][1], "" + punkteAnzeige[11][2],
						"" + punkteAnzeige[11][3], "" + punkteAnzeige[11][4], "" + punkteAnzeige[11][5] },
				{ "Kleine Straße", "" + punkteAnzeige[12][0], "" + punkteAnzeige[12][1], "" + punkteAnzeige[12][2],
						"" + punkteAnzeige[12][3], "" + punkteAnzeige[12][4], "" + punkteAnzeige[12][5] },
				{ "Große Straße", "" + punkteAnzeige[13][0], "" + punkteAnzeige[13][1], "" + punkteAnzeige[13][2],
						"" + punkteAnzeige[13][3], "" + punkteAnzeige[13][4], "" + punkteAnzeige[13][5] },
				{ "Kniffel", "" + punkteAnzeige[14][0], "" + punkteAnzeige[14][1], "" + punkteAnzeige[14][2],
						"" + punkteAnzeige[14][3], "" + punkteAnzeige[14][4], "" + punkteAnzeige[14][5] },
				{ "Chance", "" + punkteAnzeige[15][0], "" + punkteAnzeige[15][1], "" + punkteAnzeige[15][2],
						"" + punkteAnzeige[15][3], "" + punkteAnzeige[15][4], "" + punkteAnzeige[15][5] },
				{ "Unten Gesamt", "" + punkteAnzeige[16][0], "" + punkteAnzeige[16][1], "" + punkteAnzeige[16][2],
						"" + punkteAnzeige[16][3], "" + punkteAnzeige[16][4], "" + punkteAnzeige[16][5] },
				{ "Oben Gesamt", "" + punkteAnzeige[17][0], "" + punkteAnzeige[17][1], "" + punkteAnzeige[17][2],
						"" + punkteAnzeige[17][3], "" + punkteAnzeige[17][4], "" + punkteAnzeige[17][5] },
				{ "Gesamtpunkte", "" + punkteAnzeige[18][0], "" + punkteAnzeige[18][1], "" + punkteAnzeige[18][2],
						"" + punkteAnzeige[18][3], "" + punkteAnzeige[18][4], "" + punkteAnzeige[18][5] } };
		// Update the JTable model with new data
		scoreTable = new JTable(data, columnNames);
	}

	private void addScoreTable() {

		// neues JPanel für Tabelle erstellen
		comboAnzeige = new JPanel(new BorderLayout());
		// Cutom Table Model damit nicht alle Zellen auswählbar sind
		CustomTableModel tableModel = new CustomTableModel();
		tableModel.setDataVector(data, columnNames);
		// JTable mit dem Custom Table Model initiieren
		scoreTable = new JTable(tableModel);
		// Auswählen nach Zelle nicht ganze Zeile/Spalte
		scoreTable.setCellSelectionEnabled(true);
		scoreTable.setRowSelectionAllowed(false);
		scoreTable.setColumnSelectionAllowed(false);
		// Neues Rendering für verschiedene Farben je nach Wert
		scoreTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		// scrollPane maybe unnötig aber ist besser UI für Tabelle (wenn nötig kann man
		// scrollen)
		scrollPane = new JScrollPane(scoreTable);
		scrollPane.setPreferredSize(new Dimension(600, 400)); // Setze Größe der Tabelle

		// Tabelle in comboAnzeige packen
		comboAnzeige.add(scrollPane, BorderLayout.CENTER);
	}

	private void tableSelectionListener() {
		scoreTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Get selected row and column
				rowScoreTable = scoreTable.getSelectedRow();
				columnScoreTable = scoreTable.getSelectedColumn();

				// Check if selection is valid
				if (rowScoreTable > 0 && columnScoreTable > 0) {
					value = scoreTable.getValueAt(rowScoreTable, columnScoreTable);
					combo = scoreTable.getValueAt(rowScoreTable, 0);
					stringValue = (String) value;
					stringCombo = (String) combo;

					boolean isFilteredRow = "Name: ".equals(combo) || "Summe".equals(combo)
							|| "Bonus (ab 63)".equals(combo) || "Oben Gesamt".equals(combo)
							|| "Unten Gesamt".equals(combo) || "Gesamtpunkte".equals(combo);

					if (!isFilteredRow && punkteReal[rowScoreTable - 1][columnScoreTable - 1] < 0) {
						System.out.println("Gewählte Zelle: Row: " + rowScoreTable + " Column: " + columnScoreTable
								+ " Wert: " + stringValue);
						confirm.setText("Als '" + stringCombo + "' eintragen (" + stringValue + " Punkte)");

					}

				}
			};
		});

	}

	public class CustomTableCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel l = new JLAbel
			JButton
			// Überprüfe, ob der Zellenwert ein String ist
			if (value instanceof String) {

				//hier ist riesen baustelle!!! kp was hier abgeht
				//wie kann ich mehrere verschiedene zellen je nach array/row usw ausgeben
				//am besten verschiedene types nehmen: jbutton oder Jlabel jenachdem was gebraucht ist
				
				//	if ("Name: ".equals(combo) || "Summe".equals(combo) || "Bonus (ab 63)".equals(combo)
							|| "Oben Gesamt".equals(combo) || "Unten Gesamt".equals(combo)
							|| "Gesamtpunkte".equals(combo)) {
					
			
		}
	}

	public class CustomTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L; // KP was das ist? Eclipse will das immer..

		@Override
		public boolean isCellEditable(int row, int column) {
			Object comboObj = getValueAt(row, 0);
			String combo = (comboObj instanceof String) ? (String) comboObj : "";

			boolean isFilteredRow = "Summe".equals(combo) || "Bonus (ab 63)".equals(combo)
					|| "Oben Gesamt".equals(combo) || "Unten Gesamt".equals(combo) || "Gesamtpunkte".equals(combo);

			return !isFilteredRow; // Return false if the row is filtered
		}
	}

}
