package main.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUI extends JFrame {
	
	    public static void GUI() {
	         
	    }

	public void startScreen(){
		StartScreen startScreen = new StartScreen();
	}
	public void mainScreen(){
		MainScreen mainScreen = new MainScreen();
	}

	public void endScreen(String gewinner, int punktzahl){
		EndScreen endScreen = new EndScreen(gewinner);
	}

	}

