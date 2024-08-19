package main.gui;

public class GUI {
    import javax.swing.*;
import java.awt.*;

public class KniffelSpiel extends JFrame {

    public KniffelSpiel() {
        setTitle("Kniffel Spiel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel für den Spielername
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel playerName = new JLabel("Spielername: ");
        playerPanel.add(playerName);
        add(playerPanel, BorderLayout.NORTH);

        // Panel für die Würfel und den Würfel-Button
        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new GridLayout(2, 5)); // 2 Reihen, 5 Spalten

        // 5 Würfel
        for (int i = 0; i < 5; i++) {
            JButton diceButton = new JButton("Würfel " + (i + 1));
            dicePanel.add(diceButton);
        }

        // Platzhalter-Würfel
        for (int i = 0; i < 5; i++) {
            dicePanel.add(new JLabel(""));
        }

        // Würfeln-Button
        JPanel rollButtonPanel = new JPanel();
        JButton rollButton = new JButton("Würfeln");
        rollButtonPanel.add(rollButton);
        add(rollButtonPanel, BorderLayout.SOUTH);

        // Würfelbecher (rechte untere Ecke)
        JPanel cupPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(0, 0, 100, 100);  // Einfache Grafik für den Würfelbecher
                g.drawString("Würfelbecher", 10, 50);
            }
        };
        cupPanel.setPreferredSize(new Dimension(100, 100));
        add(cupPanel, BorderLayout.SOUTHWEST);

        // Tabelle für die Kniffel-Ergebnisse
        String[] columnNames = {"Kombination", "Spieler 1", "Spieler 2", "Spieler 3"};
        String[][] data = {
                {"Einser", "", "", ""},
                {"Zweier", "", "", ""},
                {"Dreier", "", "", ""},
                {"Vierer", "", "", ""},
                {"Fünfer", "", "", ""},
                {"Sechser", "", "", ""},
                {"Bonus", "", "", ""},
                {"Dreierpasch", "", "", ""},
                {"Viererpasch", "", "", ""},
                {"Full House", "", "", ""},
                {"Kleine Straße", "", "", ""},
                {"Große Straße", "", "", ""},
                {"Kniffel", "", "", ""},
                {"Chance", "", "", ""},
                {"Gesamtpunkte", "", "", ""}
        };

        JTable scoreTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(scoreTable);
        add(scrollPane, BorderLayout.EAST);

        // Füge das Panel mit den Würfeln hinzu
        add(dicePanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KniffelSpiel frame = new KniffelSpiel();
            frame.setVisible(true);
        });
    }
}

}
