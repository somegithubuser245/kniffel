package main.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

public class GUIWuerfel extends JButton {
    private int value;  // Der Wert des Würfels (1-6)
    private boolean gehalten;

    	    public GUIWuerfel(int value) {
    	        this.value = value;
    	        this.gehalten = false;
    	        this.setOpaque(true);
    	        this.getPreferredSize();
    	        updateDisplay();

    	        // Add action listener to handle hold/unhold on click
    	        this.addActionListener(e -> halteWuerfel());
    	    }
    	   
    	    @Override
    	    public Dimension getPreferredSize() {
    	        // Calculate and return the preferred size
    	        return new Dimension(100, 100); // Example dimensions
    	    }
    	    
    	    // Toggle the hold state of the dice
    	    private void halteWuerfel() {
    	        gehalten = !gehalten;
    	        updateDisplay();
    	    }

    	    // Update the button text and color based on the current state
    	    private void updateDisplay() {
    	        this.setText(String.valueOf(value));
    	        if (gehalten) {
    	            this.setBackground(Color.RED); // Held dice color
    	        } else {
    	            this.setBackground(Color.LIGHT_GRAY); // Default dice color
    	        }
    	    }
    	    //TODO augen als graphic anzeigen??
    	    
    	    
    	    // Roll the dice only if it's not held
    	    public void wuerfeln(int v) {
    	        if (!gehalten) { //TODO gehalten test nicht nötig, würfelergebnis beachtet gehaltene würfel!!!
    	            value = v;
    	            updateDisplay();
    	        }
    	    }

    	    public boolean gehalten() {
    	        return gehalten;
    	    }

    	    public int getValue() {
    	        return value;
    	    }
    	}