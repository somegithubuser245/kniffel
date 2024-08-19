package main.gui;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
	public MainScreen() {
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
       wuerfeln.setPreferredSize(new Dimension(300, 100));
     
       
       leftPanel.add(dicePanel, BorderLayout.CENTER);

       // Tabelle rechts
       String[] columnNames = {"Kombination", "Spieler 1", "Spieler 2", "Spieler 3", "Spieler 4", "Spieler 5", "Spieler 6"};
       String[][] data = {
       		{"Name:", "", "", "", "", "", ""},
       		{"Einser", "", "", "", "", "", ""},
               {"Zweier", "", "", "", "", "", ""},
               {"Dreier", "", "", "", "", "", ""},
               {"Vierer", "", "", "", "", "", ""},
               {"Fünfer", "", "", "", "", "", ""},
               {"Sechser", "", "", "", "", "", ""},
               {"Summe", "", "", "", "", "", ""},
               {"Bonus (ab 63)", "", "", "", "", "", ""},
               {"Oben Gesamt", "", "", "", "", "", ""},
               {"Dreierpasch", "", "", "", "", "", ""},
               {"Viererpasch", "", "", "", "", "", ""},
               {"Full House", "", "", "", "", "", ""},
               {"Kleine Straße", "", "", "", "", "", ""},
               {"Große Straße", "", "", "", "", "", ""},
               {"Kniffel", "", "", "", "", "", ""},
               {"Chance", "", "", "", "", "", ""},
               {"Unten Gesamt", "", "", "", "", "", ""},
               {"Oben Gesamt", "", "", "", "", "", ""},
               {"Gesamtpunkte", "", "", "", "", "", ""}
       };
       JPanel comboAnzeige = new JPanel();
       comboAnzeige.setLayout(new GridLayout(2, 1)); 
       JTable scoreTable = new JTable(data, columnNames);
       JScrollPane scrollPane = new JScrollPane(scoreTable);
       scrollPane.setPreferredSize(new Dimension(600, 200)); // Setze Größe der Tabelle

       // Tabelle und Bestätigen-Knopf in comboAnzeige packen
       comboAnzeige.add(scoreTable);
       comboAnzeige.add(new JLabel("Bestätigen"));
       
       // Elemente in den JFrame packen
       add(leftPanel, BorderLayout.WEST);
       add(scrollPane, BorderLayout.CENTER);
       add(wuerfeln, BorderLayout.SOUTH);
       
       
       // GUI sichtbar machen
       setVisible(true);
   }

	


}
