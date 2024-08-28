package main.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

public class GUIWuerfel extends JButton {
    private int value;  // Wert des Würfels (1-6)
    private boolean gehalten;

    	    public GUIWuerfel(int value) {
    	        this.value = value;
    	        this.gehalten = false;
    	        this.setOpaque(true);
    	        this.getPreferredSize();
    	        updateDisplay();

    	       
    	        this.addActionListener(e -> halteWuerfel());
    	    }
    	   
    	    @Override
    	    public Dimension getPreferredSize() {
    	        
    	        return new Dimension(100, 100); 
    	    }
    	    
    	   
    	    private void halteWuerfel() {
    	        gehalten = !gehalten;
    	        updateDisplay();
    	    }

    	    
    	    private void updateDisplay() {
    	        this.setText(String.valueOf(value));
    	        if (gehalten) {
    	            this.setBackground(Color.RED);
    	        } else {
    	            this.setBackground(Color.LIGHT_GRAY); 
    	        }
    	    }
    	
    	    
    	    
    	    
    	    public void wuerfeln(int v) {
    	        if (!gehalten) { 
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