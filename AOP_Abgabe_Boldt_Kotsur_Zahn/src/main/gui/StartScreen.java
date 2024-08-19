package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
	int spielerAnzahl = 1;
	String name1, name2, name3, name4, name5, name6;
	int alter1, alter2, alter3, alter4, alter5, alter6;
	
    public StartScreen() {
        this.setTitle("Kniffel - Startscreen");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
        
        JLabel titleLabel = new JLabel("Willkommen zu Kniffel!", SwingConstants.CENTER);
        JButton anzahlMinus = new JButton(" - ");
        anzahlMinus.addActionListener(e ->)
        JButton anzahlPlus = new JButton(" + ");
        JLabel anzahlSpieler = new JLabel(" " + spielerAnzahl + " ");
        JButton startButton = new JButton("Spiel Starten");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mainscreen öffnen und Startscreen schließen
                new MainScreen().setVisible(true);
                dispose(); // Schließt das Startscreen-Fenster
            }
        });
        

     
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        this.add(titleLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(anzahlMinus, c);
        c.gridx = 1;
        this.add(anzahlSpieler, c);
        c.gridx = 2;
        this.add(anzahlPlus, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        this.add(startButton, c);


        this.setVisible(true);
    }


}
