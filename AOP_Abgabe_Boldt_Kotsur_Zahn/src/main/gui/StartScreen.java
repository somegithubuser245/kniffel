package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
	int anzahlSpieler = 1;
	String name1, name2, name3, name4, name5, name6;
	int alter1, alter2, alter3, alter4, alter5, alter6;
	
	JButton anzahlMinus;
	JButton anzahlPlus;
	JLabel labelAnzahlSpieler;
	JLabel spielerNr;
	JTextField spielerName;
	GridBagConstraints c;
	
    public StartScreen() {
        this.setTitle("Kniffel - Startscreen");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
        
     // Initialize the GridBagConstraints object
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Willkommen zu Kniffel!", SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0.1;
        this.add(titleLabel, c);

        // Player controls
        anzahlMinus = new JButton(" - ");
        anzahlPlus = new JButton(" + ");
        labelAnzahlSpieler = new JLabel(" " + anzahlSpieler + " ");

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0.0;
        this.add(anzahlMinus, c);

        c.gridx = 1;
        this.add(labelAnzahlSpieler, c);

        c.gridx = 2;
        this.add(anzahlPlus, c);

        // Start button
        JButton startButton = new JButton("Spiel Starten");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mainscreen öffnen und Startscreen schließen
                new MainScreen().setVisible(true);
                dispose(); // Schließt das Startscreen-Fenster
            }
        });

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        this.add(startButton, c);

        // Set visibility
        this.setVisible(true);

        // Event listeners
        anzahlMinus.addActionListener(this);
        anzahlPlus.addActionListener(this);
        
        
    }
	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == anzahlMinus && anzahlSpieler > 1){
    		System.out.println("reduziere");
    		anzahlSpieler = anzahlSpieler - 1;
    		labelAnzahlSpieler.setText(" " + anzahlSpieler + " ");
    		// Greift auf alle Components der GUI zu
            Component[] components = this.getContentPane().getComponents();

            // die beiden letzten loeschen (Spielername und Nr)
            if (components.length > 0) {
                this.remove(components[components.length - 1]); // Remove last component
                this.remove(components[components.length - 2]); // Remove second last component
            }

            // GUI Refresh
            this.revalidate();
            this.repaint();
    	}else if(e.getSource() == anzahlPlus && anzahlSpieler < 6) {
    		System.out.println("fuege zu");
    		anzahlSpieler = anzahlSpieler + 1;
    		labelAnzahlSpieler.setText(" " + anzahlSpieler + " ");
    		spielerNr = new JLabel("Spieler " + anzahlSpieler);
    		spielerName = new JTextField(); 
    		spielerName.setText("Name..?");
    	     // Position the new components below the existing ones
            c.gridx = 0;
            c.gridy = 2 + anzahlSpieler; // Adjust based on the number of players
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.WEST;
            c.weighty = 0.0; // Keep new components from affecting others
            this.add(spielerNr, c);

            c.gridx = 1;
            c.gridwidth = 2;
            this.add(spielerName, c);
            // GUI Refresh
            this.revalidate();
            this.repaint();

    		
    	}
    }
	
    
}
