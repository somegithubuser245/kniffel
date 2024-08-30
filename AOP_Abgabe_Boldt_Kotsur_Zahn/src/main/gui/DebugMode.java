package main.gui;

import main.logic.*;
import main.data.*;

import javax.swing.*;
import java.awt.*;

public class DebugMode extends JDialog {

	int[] userInput = new int[5];

	public DebugMode(JFrame parentFrame) {
		super(parentFrame, "Würfel Test Cheat", true);

		setSize(200, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JTextField input = new JTextField("5 zahlen eintippen");

		JButton click = new JButton("bestätige");
		click.addActionListener(e -> {
			String wuerfel = input.getText();
			for (int i = 0; i < 5; i++) {
				userInput[i] = Character.getNumericValue(wuerfel.charAt(i));
			}
			// sys out test
			for (int num : userInput) {
				System.out.print(num + " ");
			}
			dispose();
		});
		add(input, BorderLayout.NORTH);
		add(click, BorderLayout.SOUTH);
		setLocationRelativeTo(parentFrame);
		setVisible(true);
	}

	public int[] getUserInput() {
		System.out.println("GUI [DEBUGMODE] gebe aus userInput");
		for (int num : userInput) {
			System.out.print(num + " ");
		}
		return userInput;
	}
}
