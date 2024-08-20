package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
	int anzahlSpieler = 1;
	String name1, name2, name3, name4, name5, name6;
	int alter1, alter2, alter3, alter4, alter5, alter6;
	
	JPanel spielerMenu;
	
	
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
        
        //TODO Die GUI springt mit GridBagLayout furchtbar rum, lieber andere Layouts Probieren!
        // -> vielleicht lieber borderlayouts in borderlayouts wie im mainscreen?
        
        
        /* padding test:
        //jpanels fuer padding
        padding = new JPanel(new BorderLayout());
        content = new JPanel(new GridBagLayout());
        
        //padding
        this.add(new JLabel(""), BorderLayout.NORTH);
        this.add(new JLabel(""), BorderLayout.SOUTH);
        */
        // Initialize the GridBagConstraints object
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        // Title label
        JLabel titleLabel = new JLabel("Willkommen zu Kniffel!", SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0.0;
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

        

        // Filler JPanel als Puffer fuer neue Elemente
        c.gridy = 2;
        c.weighty = 0.1; // This component will take up all remaining space
        c.fill = GridBagConstraints.BOTH;
        this.add(new JPanel(), c); // Empty panel as filler
        
        // Neues JPanel mit SpielerEinstellungen
        spielerMenu = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 3; 
        c.gridwidth = 4;
        c.weighty = 0.1; 
        this.add(spielerMenu, c); //JPanel in das Grid (Grid im Grid)
		
        // Fuege Components in das spielerMenu ein
		   spielerNr = new JLabel("Spieler 1");
		   spielerName = new JTextField(); 
		   spielerName.setText("Name..?");
		   c.gridy = 0;
		   c.gridx = 0;
		   c.gridwidth = 1;
		   c.weighty = 0.0;
		   spielerMenu.add(spielerNr, c);
		   c.gridx = 1;
		   c.gridwidth = 6;
		   spielerMenu.add(spielerName, c);
    // ----Ende des inneren Containers----
		   
		   
	// Filler JPanel als Puffer fuer neue Elemente
	   c.gridy = 4;
	   c.weighty = 0.5; // This component will take up all remaining space
	   c.fill = GridBagConstraints.BOTH;
	   this.add(new JPanel(), c); // Empty panel as filler
		   
		   
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
        c.gridy = 5;
        c.gridwidth = 3;
        this.add(startButton, c);
        
     // Filler JPanel fuer Padding nach unten
        c.gridy = 6;
        c.weighty = 0.5; // This component will take up all remaining space
        c.fill = GridBagConstraints.BOTH;
        this.add(new JPanel(), c); // Empty panel as filler
        
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
            // die beiden letzten loeschen (Spielername und Nr)
            int componentCount = spielerMenu.getComponentCount();            
            if (componentCount > 0 && componentCount >= anzahlSpieler * 2) {
                // doppelt testen dass geloescht werden kann
                
                    spielerMenu.remove(componentCount - 1); // Remove last component
                    spielerMenu.remove(componentCount - 2); // Remove second last component
                
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
            c.gridy = 1 + anzahlSpieler; // Adjust based on the number of players
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.WEST;
            c.weighty = 0.0; // Keep new components from affecting others
            spielerMenu.add(spielerNr, c);

            c.gridx = 1;
            c.gridwidth = 2;
            spielerMenu.add(spielerName, c);
            // GUI Refresh
            spielerMenu.revalidate();
            spielerMenu.repaint();

    		
    	}
	}
	
    
}
