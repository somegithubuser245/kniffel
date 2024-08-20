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
 *TODO würfelHalten
 *TODO methoden/klassen aufteilen?
 *TODO aktuellen Spieler farbig markieren
 *TODO für Gesamt: und ähnliche Felder deaktivieren
 *TODO zu Beginn keine Würfel anzeigen bis würfeln geklickt wurde
*/
public class MainScreen extends JFrame {
	
	public static int[] wuerfelErgebnis = {1,2,3,4,5}; //von Niclas : array zum anzeigen der Würfel
	public static boolean[] wuerfelGehalten = {false, false, false, false, false}; //für niclas : array mit user auswahl
	public static int anzahlSpieler = 6; // wird in StartScreen gewählt und übergeben : für dani
	public int currentPlayer = 1; // 0-5 für 6 spieler , muss von GameController gesetzt werden (und in GUI aktiviert) : dani
	
	//wird in gui gesetzt und muss in die spieler instanzen

	public static String[] spielerNamen = {"david", "niclas", "dani","tobias", "lordi", "nora" }; 
	
	// punkteAnzeige Mischung aus *Berechnet und *Real -> von Dani (und Niclas)
	public static int[][] punkteAnzeige = {{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0},
							{0,0,0,0,0,0}};
	
	// data für die anzeigeTabelle, muss nicht(!) geändert werden, liest dynamisch die Werte aus punkteAnzeige als String ein!!!
	private static String[][] data = { 
			{ "Name:", spielerNamen[0], spielerNamen[1], spielerNamen[2], spielerNamen[3], spielerNamen[4], spielerNamen[5] }, 
			{ "Einser", ""+punkteAnzeige[0][0], ""+punkteAnzeige[0][1], ""+punkteAnzeige[0][2], ""+punkteAnzeige[0][3], ""+punkteAnzeige[0][4], ""+punkteAnzeige[0][5] },
			{ "Zweier", ""+punkteAnzeige[1][0], ""+punkteAnzeige[1][1], ""+punkteAnzeige[1][2], ""+punkteAnzeige[1][3], ""+punkteAnzeige[1][4], ""+punkteAnzeige[1][5] }, 
			{ "Dreier", ""+punkteAnzeige[2][0], ""+punkteAnzeige[2][1], ""+punkteAnzeige[2][2], ""+punkteAnzeige[2][3], ""+punkteAnzeige[2][4], ""+punkteAnzeige[2][5] },
			{ "Vierer", ""+punkteAnzeige[3][0], ""+punkteAnzeige[3][1], ""+punkteAnzeige[3][2], ""+punkteAnzeige[3][3], ""+punkteAnzeige[3][4], ""+punkteAnzeige[3][5] }, 
			{ "Fünfer", ""+punkteAnzeige[4][0], ""+punkteAnzeige[4][1], ""+punkteAnzeige[4][2], ""+punkteAnzeige[4][3], ""+punkteAnzeige[4][4], ""+punkteAnzeige[4][5] },
			{ "Sechser", ""+punkteAnzeige[5][0], ""+punkteAnzeige[5][1], ""+punkteAnzeige[5][2], ""+punkteAnzeige[5][3], ""+punkteAnzeige[5][4], ""+punkteAnzeige[5][5] }, 
			{ "Summe", ""+punkteAnzeige[6][0], ""+punkteAnzeige[6][1], ""+punkteAnzeige[6][2], ""+punkteAnzeige[6][3], ""+punkteAnzeige[6][4], ""+punkteAnzeige[6][5] },
			{ "Bonus (ab 63)", ""+punkteAnzeige[7][0], ""+punkteAnzeige[7][1], ""+punkteAnzeige[7][2], ""+punkteAnzeige[7][3], ""+punkteAnzeige[7][4], ""+punkteAnzeige[7][5] }, 
			{ "Oben Gesamt", ""+punkteAnzeige[8][0], ""+punkteAnzeige[8][1], ""+punkteAnzeige[8][2], ""+punkteAnzeige[8][3], ""+punkteAnzeige[8][4], ""+punkteAnzeige[8][5] },
			{ "Dreierpasch", ""+punkteAnzeige[9][0], ""+punkteAnzeige[9][1], ""+punkteAnzeige[9][2], ""+punkteAnzeige[9][3], ""+punkteAnzeige[9][4], ""+punkteAnzeige[9][5] }, 
			{ "Viererpasch", ""+punkteAnzeige[10][0], ""+punkteAnzeige[10][1], ""+punkteAnzeige[10][2], ""+punkteAnzeige[10][3], ""+punkteAnzeige[10][4], ""+punkteAnzeige[10][5] },
			{ "Full House", ""+punkteAnzeige[11][0], ""+punkteAnzeige[11][1], ""+punkteAnzeige[11][2], ""+punkteAnzeige[11][3], ""+punkteAnzeige[11][4], ""+punkteAnzeige[11][5] }, 
			{ "Kleine Straße", ""+punkteAnzeige[12][0], ""+punkteAnzeige[12][1], ""+punkteAnzeige[12][2], ""+punkteAnzeige[12][3], ""+punkteAnzeige[12][4], ""+punkteAnzeige[12][5] },
			{ "Große Straße", ""+punkteAnzeige[13][0], ""+punkteAnzeige[13][1], ""+punkteAnzeige[13][2], ""+punkteAnzeige[13][3], ""+punkteAnzeige[13][4], ""+punkteAnzeige[13][5] }, 
			{ "Kniffel", ""+punkteAnzeige[14][0], ""+punkteAnzeige[14][1], ""+punkteAnzeige[14][2], ""+punkteAnzeige[14][3], ""+punkteAnzeige[14][4], ""+punkteAnzeige[14][5] },
			{ "Chance", ""+punkteAnzeige[15][0], ""+punkteAnzeige[15][1], ""+punkteAnzeige[15][2], ""+punkteAnzeige[15][3], ""+punkteAnzeige[15][4], ""+punkteAnzeige[15][5] }, 
			{ "Unten Gesamt", ""+punkteAnzeige[16][0], ""+punkteAnzeige[16][1], ""+punkteAnzeige[16][2], ""+punkteAnzeige[16][3], ""+punkteAnzeige[16][4], ""+punkteAnzeige[16][5] },
			{ "Oben Gesamt", ""+punkteAnzeige[17][0], ""+punkteAnzeige[17][1], ""+punkteAnzeige[17][2], ""+punkteAnzeige[17][3], ""+punkteAnzeige[17][4], ""+punkteAnzeige[17][5] }, 
			{ "Gesamtpunkte", ""+punkteAnzeige[18][0], ""+punkteAnzeige[18][1], ""+punkteAnzeige[18][2], ""+punkteAnzeige[18][3], ""+punkteAnzeige[18][4], ""+punkteAnzeige[18][5] } };
	
	//GUI-Komponenten
	JPanel leftPanel;
	JButton wuerfeln;
	JButton confirm;
	JPanel comboAnzeige;
	JTable scoreTable;
	JScrollPane scrollPane;
	
	//für Tabellen Arbeit
	private static String[] columnNames = { "Kombination", "Spieler 1", "Spieler 2", "Spieler 3", "Spieler 4", "Spieler 5",
	"Spieler 6" };
	private int rowScoreTable;
	private int columnScoreTable;
	private Object value;
	private Object combo;
	String stringValue;
	String stringCombo;
	
	public MainScreen() {
		//DataFunktion zum Test
		for(int x = 0; x < 21; x++) {
			for(int y = 0; y < 6; y++) {
				punkteAnzeige[x][y] = x+y;
			}
		}
		refreshTable();
		
		setWindowOptions();

		addWuerfelMenu();

		// Tabelle rechts
		addScoreTable();

		// Bestätigen Knopf
		confirm = new JButton("Zeile in Tabelle auswählen...");
        confirm.addActionListener(e -> {
            System.out.println("Auswahl Bestätigt: "+getStringCombo()+" mit "+getStringValue()+" Punkten.");
            //TODO bestätigen knopf speichert valueat in die PunkteTabelle des Spielers als int!!
            int punkte=0;
            try {
                punkte = Integer.parseInt(getStringValue());
                
            } catch (NumberFormatException f) {
                System.out.println("Der String konnte nicht in eine Ganzzahl umgewandelt werden.");
            }
            System.out.println("Gespeichert an Kombi: "+getStringCombo()+"=index "+rowScoreTable+" mit Wert: "+punkte );
            if(punkte == 10) {
            	confirm.setText("NULL PUNKTE EINTRAGE?");
                confirm.setOpaque(true); // Ensure the button is opaque
                confirm.setBackground(Color.RED);
                confirm.revalidate();
                confirm.repaint();
            }
        });
		JButton nextPlayer = new JButton("Fertig");
		JPanel tableButtons = new JPanel(new GridLayout(1,2));
		tableButtons.add(confirm);
		tableButtons.add(nextPlayer);
		comboAnzeige.add(tableButtons, BorderLayout.SOUTH);	    

        
		// Elemente in den JFrame packen
		add(leftPanel, BorderLayout.WEST);
		add(comboAnzeige, BorderLayout.CENTER);
		add(wuerfeln, BorderLayout.SOUTH);

		// GUI sichtbar machen
		setVisible(true);
		
		// Add a selection listener to the table
        tableSelectionListener();   
        
            
	
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
                       if(stringValue.length() == 2) {
                       	System.out.println("Gewählte Zelle: Row: " + rowScoreTable + " Column: " + columnScoreTable + " Wert: " + stringValue);
                       	confirm.setText("Als '" + stringCombo + "' eintragen (" + stringValue + " Punkte)");
                       
                       
                   }
               
            }
           };
        });
		
	}

	public String getStringValue() {
		return stringValue;
	}
	
	public String getStringCombo() {
		return stringCombo;
	}

	private void addScoreTable() {
		
		
		comboAnzeige = new JPanel(new BorderLayout());
		
		scoreTable = new JTable(data, columnNames);
		scoreTable.setCellSelectionEnabled(true);
		scoreTable.setRowSelectionAllowed(false);
		scoreTable.setColumnSelectionAllowed(false);
        scoreTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		scrollPane = new JScrollPane(scoreTable);
		scrollPane.setPreferredSize(new Dimension(600, 400)); // Setze Größe der Tabelle

		// Tabelle in comboAnzeige packen
		comboAnzeige.add(scrollPane, BorderLayout.CENTER);
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
		JPanel dicePanel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				// Berechne die Dimensionen des Panels so, dass es quadratisch ist
				Dimension size = super.getPreferredSize();
				int dimension = Math.min(size.width, size.height);
				return new Dimension(dimension, dimension);
			}
		};
		dicePanel.setLayout(new GridLayout(5, 2)); // 3 Reihen(+2 als Puffer), 2 Spalten
		dicePanel.add(new JLabel(""));
		dicePanel.add(new JLabel(""));
		// füge 5 Würfel hinzu
		JButton wuerfel1 = new JButton();
		JButton wuerfel2 = new JButton();
		JButton wuerfel3 = new JButton();
		JButton wuerfel4 = new JButton();
		JButton wuerfel5 = new JButton();
		
		dicePanel.add(new JLabel(""));
		dicePanel.add(new JLabel(""));

		//TODO würfel zu dicePanel adden und anzeigen lassen!
		//TODO set würfel text mit array
		//TODO set würfel gehalten + farbe über eventlistener und ändere array
		
		// Würfeln Knopf
		wuerfeln = new JButton("Würfeln");
		wuerfeln.setPreferredSize(new Dimension(300, 50));

		 // Hinzufügen eines ActionListeners mit Lambda-Ausdruck
        wuerfeln.addActionListener(e -> {
            // Code, der ausgeführt wird, wenn der Button geklickt wird
            System.out.println("Es wird gewürfelt, folgende Würfel sind gehalten:" );
        });
		
		
		leftPanel.add(dicePanel, BorderLayout.CENTER);
	}



	private void setWindowOptions() {
		// Fenster-Eigenschaften setzen
		setTitle("Kniffel Spiel");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	private void refreshTable() {
	    //aktualisiert das data Array und die JTable
		data = new String[][] {
	        { "Name:", spielerNamen[0], spielerNamen[1], spielerNamen[2], spielerNamen[3], spielerNamen[4], spielerNamen[5] },
	        { "Einser", ""+punkteAnzeige[0][0], ""+punkteAnzeige[0][1], ""+punkteAnzeige[0][2], ""+punkteAnzeige[0][3], ""+punkteAnzeige[0][4], ""+punkteAnzeige[0][5] },
	        { "Zweier", ""+punkteAnzeige[1][0], ""+punkteAnzeige[1][1], ""+punkteAnzeige[1][2], ""+punkteAnzeige[1][3], ""+punkteAnzeige[1][4], ""+punkteAnzeige[1][5] },
	        { "Dreier", ""+punkteAnzeige[2][0], ""+punkteAnzeige[2][1], ""+punkteAnzeige[2][2], ""+punkteAnzeige[2][3], ""+punkteAnzeige[2][4], ""+punkteAnzeige[2][5] },
	        { "Vierer", ""+punkteAnzeige[3][0], ""+punkteAnzeige[3][1], ""+punkteAnzeige[3][2], ""+punkteAnzeige[3][3], ""+punkteAnzeige[3][4], ""+punkteAnzeige[3][5] },
	        { "Fünfer", ""+punkteAnzeige[4][0], ""+punkteAnzeige[4][1], ""+punkteAnzeige[4][2], ""+punkteAnzeige[4][3], ""+punkteAnzeige[4][4], ""+punkteAnzeige[4][5] },
	        { "Sechser", ""+punkteAnzeige[5][0], ""+punkteAnzeige[5][1], ""+punkteAnzeige[5][2], ""+punkteAnzeige[5][3], ""+punkteAnzeige[5][4], ""+punkteAnzeige[5][5] },
	        { "Summe", ""+punkteAnzeige[6][0], ""+punkteAnzeige[6][1], ""+punkteAnzeige[6][2], ""+punkteAnzeige[6][3], ""+punkteAnzeige[6][4], ""+punkteAnzeige[6][5] },
	        { "Bonus (ab 63)", ""+punkteAnzeige[7][0], ""+punkteAnzeige[7][1], ""+punkteAnzeige[7][2], ""+punkteAnzeige[7][3], ""+punkteAnzeige[7][4], ""+punkteAnzeige[7][5] },
	        { "Oben Gesamt", ""+punkteAnzeige[8][0], ""+punkteAnzeige[8][1], ""+punkteAnzeige[8][2], ""+punkteAnzeige[8][3], ""+punkteAnzeige[8][4], ""+punkteAnzeige[8][5] },
	        { "Dreierpasch", ""+punkteAnzeige[9][0], ""+punkteAnzeige[9][1], ""+punkteAnzeige[9][2], ""+punkteAnzeige[9][3], ""+punkteAnzeige[9][4], ""+punkteAnzeige[9][5] },
	        { "Viererpasch", ""+punkteAnzeige[10][0], ""+punkteAnzeige[10][1], ""+punkteAnzeige[10][2], ""+punkteAnzeige[10][3], ""+punkteAnzeige[10][4], ""+punkteAnzeige[10][5] },
	        { "Full House", ""+punkteAnzeige[11][0], ""+punkteAnzeige[11][1], ""+punkteAnzeige[11][2], ""+punkteAnzeige[11][3], ""+punkteAnzeige[11][4], ""+punkteAnzeige[11][5] },
	        { "Kleine Straße", ""+punkteAnzeige[12][0], ""+punkteAnzeige[12][1], ""+punkteAnzeige[12][2], ""+punkteAnzeige[12][3], ""+punkteAnzeige[12][4], ""+punkteAnzeige[12][5] },
	        { "Große Straße", ""+punkteAnzeige[13][0], ""+punkteAnzeige[13][1], ""+punkteAnzeige[13][2], ""+punkteAnzeige[13][3], ""+punkteAnzeige[13][4], ""+punkteAnzeige[13][5] },
	        { "Kniffel", ""+punkteAnzeige[14][0], ""+punkteAnzeige[14][1], ""+punkteAnzeige[14][2], ""+punkteAnzeige[14][3], ""+punkteAnzeige[14][4], ""+punkteAnzeige[14][5] },
	        { "Chance", ""+punkteAnzeige[15][0], ""+punkteAnzeige[15][1], ""+punkteAnzeige[15][2], ""+punkteAnzeige[15][3], ""+punkteAnzeige[15][4], ""+punkteAnzeige[15][5] },
	        { "Unten Gesamt", ""+punkteAnzeige[16][0], ""+punkteAnzeige[16][1], ""+punkteAnzeige[16][2], ""+punkteAnzeige[16][3], ""+punkteAnzeige[16][4], ""+punkteAnzeige[16][5] },
	        { "Oben Gesamt", ""+punkteAnzeige[17][0], ""+punkteAnzeige[17][1], ""+punkteAnzeige[17][2], ""+punkteAnzeige[17][3], ""+punkteAnzeige[17][4], ""+punkteAnzeige[17][5] },
	        { "Gesamtpunkte", ""+punkteAnzeige[18][0], ""+punkteAnzeige[18][1], ""+punkteAnzeige[18][2], ""+punkteAnzeige[18][3], ""+punkteAnzeige[18][4], ""+punkteAnzeige[18][5] }
	    };
	    // Update the JTable model with new data
		scoreTable = new JTable(data, columnNames);
	}
	
	public class CustomTableCellRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        // Überprüfe, ob der Zellenwert ein String ist
	        if (value instanceof String) {
	            String stringValue = (String) value;

	            // Überprüfen, ob die Zelle ausgewählt ist und die Länge des Strings 2 ist
	            //2 = gut 1 = schlecht
	            if (hasFocus) {
	                if (stringValue.length() == 2) {
	                    cellComponent.setBackground(Color.WHITE); 
	                    cellComponent.setForeground(Color.BLACK);
	                    cellComponent.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
	                } else if (stringValue.length() == 1) {
	                    cellComponent.setBackground(Color.WHITE); // Hintergrund bleibt Weiß
	                    cellComponent.setForeground(Color.GRAY);
	                }
	            } else if(stringValue.length() == 2){
	                cellComponent.setBackground(Color.WHITE); // Hintergrund auf Weiß zurücksetzen, wenn nicht ausgewählt
	                cellComponent.setForeground(Color.BLACK);
	            } else if(stringValue.length() == 1) {
	            	cellComponent.setBackground(Color.WHITE); // Hintergrund auf Weiß zurücksetzen, wenn nicht ausgewählt
	            	cellComponent.setForeground(Color.GRAY);
	            }
	        } else {
	            cellComponent.setBackground(Color.GREEN); // Hintergrund auf Weiß setzen, falls der Wert kein String ist
	        }

	       
	        return cellComponent;
	    }
	}
	
}
