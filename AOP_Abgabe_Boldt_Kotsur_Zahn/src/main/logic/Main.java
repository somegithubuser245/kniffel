package main.logic;

import javax.swing.SwingUtilities;
import main.ui.KniffelSpiel;  // Importiere die GUI-Klasse

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KniffelSpiel gui = new KniffelSpiel();
            gui.setVisible(true);
            gui.setDefaultCloseOperation(KniffelSpiel.EXIT_ON_CLOSE);  // Exit on Close aktivieren
        });
    }
}