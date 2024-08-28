package main.gui;

import main.logic.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
	public static int anzahlSpieler = 1;
	//string array für namen! array länge wird bei spielstart festgesetzt
	String[] namen;
	
	JPanel spielerMenu;
	
	
	JButton anzahlMinus;
	JButton anzahlPlus;
	JLabel labelAnzahlSpieler;
	JLabel spielerNr;
	
	JTextField[] namensFelder = new JTextField[6];
	
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

        

        // Filler JPanel als Puffer fuer neue Elemente (mit weight)
        c.gridy = 2;
        c.weighty = 0.1; 
        c.fill = GridBagConstraints.BOTH;
        this.add(new JPanel(), c);
        
        // Neues JPanel mit SpielerEinstellungen
        spielerMenu = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 3; 
        c.gridwidth = 4;
        c.weighty = 0.1; 
        this.add(spielerMenu, c); 
		
        
		   spielerNr = new JLabel("Spieler 1");
		   namensFelder[anzahlSpieler-1] = new JTextField(); 
		   namensFelder[anzahlSpieler-1].setText("Name..?");
		   c.gridy = 0;
		   c.gridx = 0;
		   c.gridwidth = 1;
		   c.weighty = 0.0;
		   spielerMenu.add(spielerNr, c);
		   c.gridx = 1;
		   c.gridwidth = 6;
		   spielerMenu.add(namensFelder[anzahlSpieler-1], c);
    // ----Ende des inneren Containers----
		   
		   
	// Filler JPanel als Puffer fuer neue Elemente
	   c.gridy = 4;
	   c.weighty = 0.5; 
	   c.fill = GridBagConstraints.BOTH;
	   this.add(new JPanel(), c); 
		   
		   
     // Start 
        JButton startButton = new JButton("Spiel Starten");
        startButton.addActionListener(e -> {
            //namen array mit länger der anzahlSpieler initialisieren und für jeden index den eingegebenen Text übernehmen
        	namen = new String[anzahlSpieler];
            for(int i = 0; i<anzahlSpieler; i++) {   
            	if("Name..?".equalsIgnoreCase(namensFelder[i].getText())) {
            		namensFelder[i].setText("Spieler "+(i+1));
            	}
            	namen[i] = namensFelder[i].getText();
            	System.out.println("" + namen[i]);
                GameController.initGame(namen, anzahlSpieler);
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
            //teste dass es mehr als 2 elemente gibt, da pro spieler 2 elemente (knopf, textfeld) und immer mind. 1 Spieler 
            if (componentCount >= anzahlSpieler * 2) {
                
                	//beide letzten elemente in der komponentenliste löschen (ein spieler weniger)
                    spielerMenu.remove(componentCount - 1);
                    spielerMenu.remove(componentCount - 2); 
                
            }
                    // GUI Refresh
            this.revalidate();
            this.repaint();
    	}else if(e.getSource() == anzahlPlus && anzahlSpieler < 6) {
    		System.out.println("fuege zu");
    		anzahlSpieler = anzahlSpieler + 1;
    		labelAnzahlSpieler.setText(" " + anzahlSpieler + " ");
    		spielerNr = new JLabel("Spieler " + anzahlSpieler);
    		namensFelder[anzahlSpieler-1] = new JTextField(); 
    		namensFelder[anzahlSpieler-1].setText("Name..?");
    	     
            c.gridx = 0;
            c.gridy = 1 + anzahlSpieler;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.WEST;
            c.weighty = 0.0;
            spielerMenu.add(spielerNr, c);

            c.gridx = 1;
            c.gridwidth = 2;
            spielerMenu.add(namensFelder[anzahlSpieler-1], c);
            // GUI Refresh
            spielerMenu.revalidate();
            spielerMenu.repaint();

    		
    	}
	}
	
    
}
