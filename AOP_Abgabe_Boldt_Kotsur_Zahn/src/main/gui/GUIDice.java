package main.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

public class GUIDice extends JButton {
    private int value;  // Wert des Würfels (1-6)
    private boolean saved;

    	    public GUIDice(int value) {
    	        this.value = value;
    	        this.saved = false;
    	        this.setOpaque(true);
    	        this.getPreferredSize();
    	        updateDisplay();

    	       
    	        this.addActionListener(e -> saveDice());
    	    }
    	   
    	    @Override
    	    public Dimension getPreferredSize() {
    	        
    	        return new Dimension(100, 100); 
    	    }
    	    
    	   
    	    private void saveDice() {
    	        saved = !saved;
    	        updateDisplay();
    	    }

    	    
    	    private void updateDisplay() {
    	        this.setText(String.valueOf(value));
    	        if (saved) {
    	            this.setBackground(Color.RED);
    	        } else {
    	            this.setBackground(Color.LIGHT_GRAY); 
    	        }
    	    }
    	
    	    
    	    
    	    
    	    public void roll(int v) {
    	        if (!saved) { 
    	            value = v;
    	            updateDisplay();
    	        }
    	    }

    	    public boolean isSaved() {
    	        return saved;
    	    }

    	    public int getValue() {
    	        return value;
    	    }
    	}