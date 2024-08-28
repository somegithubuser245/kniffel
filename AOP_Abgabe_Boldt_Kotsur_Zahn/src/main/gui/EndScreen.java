package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends JFrame {
    public static Object startButton;

	public EndScreen(String winner) {
        setTitle("Spiel Beendet");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel winnerLabel = new JLabel("Der Gewinner ist: " + winner, SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(winnerLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("Neues Spiel");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Starte den Startscreen neu
                new StartScreen().setVisible(true);
                dispose(); // Schließt das Endscreen-Fenster
            }
        });
        add(restartButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
