package main.gui;

import main.logic.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
	public static int numberOfPlayers = 1;
	//string array für name! array länge wird bei spielstart festgesetzt
	String[] name;
	
	JPanel playerMenu;
	
	
	JButton counterMinus;
	JButton counterPlus;
	JLabel numberOfPlayerLabel;
	JLabel playerNumber;
	
	JTextField[] namesList = new JTextField[6];
	
	GridBagConstraints c;
	
    public StartScreen() {
        this.setTitle("Kniffel - Startscreen");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
    
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        
        JLabel titleLabel = new JLabel("Willkommen zu Kniffel!", SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0.0;
        this.add(titleLabel, c);

        
        counterMinus = new JButton(" - ");
        counterPlus = new JButton(" + ");
        numberOfPlayerLabel = new JLabel(" " + numberOfPlayers + " ");

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0.0;
        this.add(counterMinus, c);

        c.gridx = 1;
        this.add(numberOfPlayerLabel, c);

        c.gridx = 2;
        this.add(counterPlus, c);

        

        // Filler JPanel als Puffer fuer neue Elemente (mit weight)
        c.gridy = 2;
        c.weighty = 0.1; 
        c.fill = GridBagConstraints.BOTH;
        this.add(new JPanel(), c);
        
        // Neues JPanel mit SpielerEinstellungen
        playerMenu = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 3; 
        c.gridwidth = 4;
        c.weighty = 0.1; 
        this.add(playerMenu, c); 
		
        
		   playerNumber = new JLabel("Spieler 1");
		   namesList[numberOfPlayers-1] = new JTextField(); 
		   namesList[numberOfPlayers-1].setText("Name..?");
		   c.gridy = 0;
		   c.gridx = 0;
		   c.gridwidth = 1;
		   c.weighty = 0.0;
		   playerMenu.add(playerNumber, c);
		   c.gridx = 1;
		   c.gridwidth = 6;
		   playerMenu.add(namesList[numberOfPlayers-1], c);
    // ----Ende des inneren Containers----
		   
		   
	// Filler JPanel als Puffer fuer neue Elemente
	   c.gridy = 4;
	   c.weighty = 0.5; 
	   c.fill = GridBagConstraints.BOTH;
	   this.add(new JPanel(), c); 
		   
		   
     // Start 
        JButton startButton = new JButton("Spiel Starten");
        startButton.addActionListener(e -> {
            //name array mit länger der numberOfPlayers initialisieren und für jeden index den eingegebenen Text übernehmen
        	name = new String[numberOfPlayers];
            for(int i = 0; i<numberOfPlayers; i++) {   
            	if("Name..?".equalsIgnoreCase(namesList[i].getText())) {
            		namesList[i].setText("Spieler "+(i+1));
            	}
            	name[i] = namesList[i].getText();
            	System.out.println("" + name[i]);
                GameController.initGame(name, numberOfPlayers);
            }
     
        	// Mainscreen öffnen und Startscreen schließen
            new MainScreen().setVisible(true);
            dispose(); 
        });

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        this.add(startButton, c);
        
     // Filler JPanel fuer Padding nach unten
        c.gridy = 6;
        c.weighty = 0.5; 
        c.fill = GridBagConstraints.BOTH;
        this.add(new JPanel(), c); 
        
        
        this.setVisible(true);

        // Event listeners
        counterMinus.addActionListener(this);
        counterPlus.addActionListener(this);
    
    }

    
    
    
	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == counterMinus && numberOfPlayers > 1){
    		System.out.println("reduziere");
            numberOfPlayers = numberOfPlayers - 1;
    		numberOfPlayerLabel.setText(" " + numberOfPlayers + " ");
            // die beiden letzten loeschen (Spielername und Nr)
            int componentCount = playerMenu.getComponentCount();            
            //teste dass es mehr als 2 elemente gibt, da pro spieler 2 elemente (knopf, textfeld) und immer mind. 1 Spieler 
            if (componentCount >= numberOfPlayers * 2) {
                
                	//beide letzten elemente in der komponentenliste löschen (ein spieler weniger)
                    playerMenu.remove(componentCount - 1);
                    playerMenu.remove(componentCount - 2); 
                
            }
                    // GUI Refresh
            this.revalidate();
            this.repaint();
    	}else if(e.getSource() == counterPlus && numberOfPlayers < 6) {
    		System.out.println("fuege zu");
    		numberOfPlayers = numberOfPlayers + 1;
    		numberOfPlayerLabel.setText(" " + numberOfPlayers + " ");
    		playerNumber = new JLabel("Spieler " + numberOfPlayers);
    		namesList[numberOfPlayers-1] = new JTextField(); 
    		namesList[numberOfPlayers-1].setText("Name..?");
    	     
            c.gridx = 0;
            c.gridy = 1 + numberOfPlayers;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.WEST;
            c.weighty = 0.0;
            playerMenu.add(playerNumber, c);

            c.gridx = 1;
            c.gridwidth = 2;
            playerMenu.add(namesList[numberOfPlayers-1], c);
            // GUI Refresh
            playerMenu.revalidate();
            playerMenu.repaint();

    		
    	}
	}
	
    
}
