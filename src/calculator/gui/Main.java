package calculator.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	// Set static for global access
	public static MainGUI mainGUI;
	public static void main(String[] args) {
		
		mainGUI = new MainGUI();
		mainGUI.setTitle("Fitness Calculator");
		mainGUI.setBounds(400, 400, 500, 500);
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setVisible(true);
	}

}
