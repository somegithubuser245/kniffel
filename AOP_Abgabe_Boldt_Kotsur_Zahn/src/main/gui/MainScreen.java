package main.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MainScreen extends JFrame {
	int anzahlSpieler = 6;
	String[] spielerNamen = {"david", "niclas", "dani","tobias", "lordi", "nora" };
	int[][] punkteAnzeige = {{0,0,0,0,0,0},
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
	
	
	public MainScreen() {
		//DataFunktion zum Test
		for(int x = 0; x < 21; x++) {
			for(int y = 0; y < 6; y++) {
				punkteAnzeige[x][y] = x+y;
			}
		}
		
		
		// Fenster-Eigenschaften setzen
		setTitle("Kniffel Spiel");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Linkes Panel für Spielername und Würfel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// Spielername oben links
		JLabel playerName = new JLabel("Spielername");
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
		dicePanel.setLayout(new GridLayout(5, 2)); // 3 Reihen, 2 Spalten
		dicePanel.add(new JLabel(""));
		dicePanel.add(new JLabel(""));
		// 5 Würfel in zwei Spalten
		for (int i = 0; i < 5; i++) {
			GUIWuerfel wuerfel = new GUIWuerfel(i + 1);
			dicePanel.add(wuerfel);
		}
		dicePanel.add(new JLabel(""));
		dicePanel.add(new JLabel(""));

		// Würfeln Knopf
		JButton wuerfeln = new JButton("Würfeln");
		wuerfeln.setPreferredSize(new Dimension(300, 50));

		leftPanel.add(dicePanel, BorderLayout.CENTER);

		// Tabelle rechts
		String[] columnNames = { "Kombination", "Spieler 1", "Spieler 2", "Spieler 3", "Spieler 4", "Spieler 5",
				"Spieler 6" };
		String[][] data = { 
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
		JPanel comboAnzeige = new JPanel(new BorderLayout());
		
		JTable scoreTable = new JTable(data, columnNames);
		scoreTable.setCellSelectionEnabled(true);
		scoreTable.setRowSelectionAllowed(false);
		scoreTable.setColumnSelectionAllowed(false);
        scoreTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		JScrollPane scrollPane = new JScrollPane(scoreTable);
		scrollPane.setPreferredSize(new Dimension(600, 400)); // Setze Größe der Tabelle

		// Tabelle in comboAnzeige packen
		comboAnzeige.add(scrollPane, BorderLayout.CENTER);

		// Bestätigen Knopf
		JButton confirm = new JButton("Als 'Kniffel' eintragen ('50' Punkte)");
		comboAnzeige.add(confirm, BorderLayout.SOUTH);
		//TODO JButton-Text dynamisch je nach Auswahl ändern - mit setText oder so
		
		// Elemente in den JFrame packen
		add(leftPanel, BorderLayout.WEST);
		add(comboAnzeige, BorderLayout.CENTER);
		add(wuerfeln, BorderLayout.SOUTH);

		// GUI sichtbar machen
		setVisible(true);
		
		// Add a selection listener to the table
        scoreTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Get selected row and column
                int row = scoreTable.getSelectedRow();
                int column = scoreTable.getSelectedColumn();

                // Check if selection is valid
                if (row >= 0 && column >= 0) {
                    Object value = scoreTable.getValueAt(row, column);
                    System.out.println("Gewählte Zelle: Row: " + row + " Column: "+column);
                }
            }
        });
	}

	public class CustomTableCellRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        // Überprüfe, ob der Zellenwert ein String ist
	        if (value instanceof String) {
	            String stringValue = (String) value;

	            // Überprüfen, ob die Zelle ausgewählt ist und die Länge des Strings 2 ist
	            if (hasFocus) {
	                if (stringValue.length() == 2) {
	                    cellComponent.setBackground(Color.RED); // Hintergrund auf Rot setzen
	                } else if (stringValue.length() == 1) {
	                    cellComponent.setBackground(Color.WHITE); // Hintergrund bleibt Weiß
	                }
	            } else {
	                cellComponent.setBackground(Color.BLUE); // Hintergrund auf Weiß zurücksetzen, wenn nicht ausgewählt
	            }
	        } else {
	            cellComponent.setBackground(Color.GREEN); // Hintergrund auf Weiß setzen, falls der Wert kein String ist
	        }

	       
	        return cellComponent;
	    }
	}
	
}
