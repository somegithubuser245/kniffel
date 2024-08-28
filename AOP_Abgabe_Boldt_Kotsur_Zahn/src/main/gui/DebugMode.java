package main.gui;

import main.logic.*;
import main.data.*;

import javax.swing.*;
import java.awt.*;

public class DebugMode extends JFrame {
    public static Object startButton;

	public DebugMode() {
        setTitle("Spiel Beendet");
        setSize(200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       JTextField eingabe = new JTextField("5 zahlen eintippen");
       
       

        JButton klick = new JButton("bestätige");
        klick.addActionListener(e -> {
            String wuerfel = eingabe.getText();
         
            int[] numbers = new int[wuerfel.length()];
            
           
            for (int i = 0; i < wuerfel.length(); i++) {
                numbers[i] = Character.getNumericValue(wuerfel.charAt(i));
            }
            
            // Output the int array to verify the result
            for (int num : numbers) {
                System.out.print(num + " ");
            }
            
           
            
        });
        add(eingabe, BorderLayout.WEST);
        add(klick, BorderLayout.EAST);

        setVisible(true);
    }
}
