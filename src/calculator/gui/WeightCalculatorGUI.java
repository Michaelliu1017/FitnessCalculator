package calculator.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculator.functions.*;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JButton;

public class WeightCalculatorGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textHeight;
	private JTextField textAge;
	private JComboBox comboGender;
	private JLabel lblHeight;
	private JLabel lblAge;
	private JLabel lblGender;
	private JLabel lblTitle;
	private JLabel lblUnit;
	private JLabel lblResultTitle;
	private JLabel lblResult;
	private JButton btnCalculate;
	private JButton btnBack;

	public WeightCalculatorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textHeight = new JTextField();
		textHeight.setBounds(91, 59, 86, 20);
		contentPane.add(textHeight);
		textHeight.setColumns(10);
		
		textAge = new JTextField();
		textAge.setBounds(91, 108, 86, 20);
		contentPane.add(textAge);
		textAge.setColumns(10);
		
		String [] gender = {"Male", "Female"};
		comboGender = new JComboBox(gender);
		comboGender.setBounds(91, 158, 86, 22);
		contentPane.add(comboGender);
		
		lblHeight = new JLabel("Height");
		lblHeight.setBounds(43, 62, 38, 14);
		contentPane.add(lblHeight);
		
		lblAge = new JLabel("Age");
		lblAge.setBounds(53, 111, 28, 14);
		contentPane.add(lblAge);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds(35, 162, 46, 14);
		contentPane.add(lblGender);
		
		lblTitle = new JLabel("Ideal Weight Calculator");
		lblTitle.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblTitle.setBounds(175, 10, 186, 33);
		contentPane.add(lblTitle);
		
		lblUnit = new JLabel("cm");
		lblUnit.setBounds(180, 62, 46, 14);
		contentPane.add(lblUnit);
		
		lblResultTitle = new JLabel("Ideal Weight Range:");
		lblResultTitle.setBounds(248, 85, 124, 14);
		contentPane.add(lblResultTitle);
		
		lblResult = new JLabel("");
		lblResult.setBounds(248, 118, 124, 30);
		contentPane.add(lblResult);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_calculate();
			}
		});
		btnCalculate.setBounds(109, 255, 89, 23);
		contentPane.add(btnCalculate);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_back();
			}
		});
		
		btnBack.setBounds(262, 255, 89, 23);
		contentPane.add(btnBack);
	}
	
	public void click_back() {
		Main.mainGUI.setVisible(true);
		this.dispose();
	}
	
	public void click_calculate() {
		double height;
		int age;
		String sex;
		sex = (String) comboGender.getSelectedItem();
		try {
			height = Double.parseDouble(textHeight.getText());
			age = Integer.parseInt(textAge.getText());
			functionGL gl = new functionGL();
			String result = gl.BMI_Calculator(height);
			if (result.equalsIgnoreCase("")) {
				throw new IllegalArgumentException();
			}
			lblResult.setText(result);
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Invalid input");
		}

	}
}
