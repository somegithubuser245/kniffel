package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

public class GUIWuerfel extends JButton {
    private int value;  // Der Wert des Würfels (1-6)

    public GUIWuerfel(int value) {
        this.value = value;
        setPreferredSize(new java.awt.Dimension(80, 80));  // Setze die Größe des Buttons
        setFocusPainted(false);  // Entferne den Fokus-Rahmen
        setContentAreaFilled(false);  // Entferne die Hintergrundfüllung
    }
 
    @Override
    public Dimension getPreferredSize() {
        // Höhe und Breite gleichsetzen, basierend auf der aktuellen Höhe oder Breite
        Dimension size = super.getPreferredSize();
        int dimension = Math.max(size.width, size.height);
        return new Dimension(dimension, dimension);
    }

	public void setValue(int value) {
        this.value = value;
        repaint();  // Neu zeichnen, wenn sich der Wert ändert
    }

    public int getValue() {
        return value;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Zeichne den Würfel (ein einfaches Quadrat)
        g.setColor(Color.WHITE);
        g.fillRect(10, 10, 60, 60);  // Zeichne das Quadrat (Würfel)

        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 60, 60);  // Zeichne den Rand des Würfels

        // Setze den Font für die Augenzahl
        g.setFont(new Font("Arial", Font.BOLD, 40));

        // Zeichne die Augenzahl in der Mitte des Würfels
        String text = String.valueOf(value);
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getAscent();
        g.drawString(text, 40 - textWidth / 2, 50 + textHeight / 4);
    }
}
