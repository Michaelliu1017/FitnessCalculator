package calculator.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import calculator.functions.*;

import java.awt.Color;

public class BMRGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textHeight;
	private JTextField textAge;
	private JComboBox comboGender;
	private JLabel lblHeight;
	private JLabel lblAge;
	private JLabel lblGender;
	private JLabel lblTitle;
	private JLabel lblCM;
	private JLabel lblResultTitle;
	private JLabel lblResult;
	private JButton btnCalculate;
	private JButton btnBack;
	private JTextField textWeight;
	private JLabel lblWeight;
	private JLabel lblKG;
//	private JComboBox comboExercise;
//	private JLabel lblExercise;

	public BMRGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textHeight = new JTextField();
		textHeight.setBounds(112, 59, 86, 20);
		contentPane.add(textHeight);
		textHeight.setColumns(10);
		
		textAge = new JTextField();
		textAge.setBounds(112, 142, 86, 20);
		contentPane.add(textAge);
		textAge.setColumns(10);
		
		String [] gender = {"Male", "Female"};
		comboGender = new JComboBox(gender);
		comboGender.setBounds(112, 190, 86, 22);
		contentPane.add(comboGender);
		
		lblHeight = new JLabel("Height");
		lblHeight.setBounds(43, 62, 38, 14);
		contentPane.add(lblHeight);
		
		lblAge = new JLabel("Age");
		lblAge.setBounds(53, 145, 28, 14);
		contentPane.add(lblAge);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds(43, 194, 46, 14);
		contentPane.add(lblGender);
		
		lblTitle = new JLabel("Basal Metabolic Rate Calculator");
		lblTitle.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblTitle.setBounds(137, 10, 259, 33);
		contentPane.add(lblTitle);
		
		lblCM = new JLabel("cm");
		lblCM.setBounds(208, 62, 46, 14);
		contentPane.add(lblCM);
		
		lblResultTitle = new JLabel("Daily BMR:");
		lblResultTitle.setForeground(new Color(0, 0, 0));
		lblResultTitle.setBounds(272, 101, 75, 14);
		contentPane.add(lblResultTitle);
		
		lblResult = new JLabel("");
		lblResult.setBounds(272, 126, 124, 30);
		contentPane.add(lblResult);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_calculate();
			}
		});
		btnCalculate.setBounds(109, 311, 89, 23);
		contentPane.add(btnCalculate);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_back();
			}
		});
		
		btnBack.setBounds(262, 311, 89, 23);
		contentPane.add(btnBack);
		
		textWeight = new JTextField();
		textWeight.setColumns(10);
		textWeight.setBounds(112, 98, 86, 20);
		contentPane.add(textWeight);
		
		lblWeight = new JLabel("Weight");
		lblWeight.setBounds(43, 101, 46, 14);
		contentPane.add(lblWeight);
		
		lblKG = new JLabel("kg");
		lblKG.setBounds(206, 101, 38, 14);
		contentPane.add(lblKG);
		
//		String [] habit = {"Sedentary", ""};
//		comboExercise = new JComboBox(habit);
//		comboExercise.setBounds(112, 239, 142, 22);
//		contentPane.add(comboExercise);
//		
//		lblExercise = new JLabel("Exercise habit");
//		lblExercise.setBounds(10, 243, 71, 14);
//		contentPane.add(lblExercise);
	}

	public void click_back() {
		Main.mainGUI.setVisible(true);
		this.dispose();
	}
	
	public void click_calculate() {
		double height;
		double weight;
		int age;
		String sex;
		double bmr;
		
		sex = (String) comboGender.getSelectedItem();
		try {
			weight = Double.parseDouble(textWeight.getText());
			height = Double.parseDouble(textHeight.getText());
			age = Integer.parseInt(textAge.getText());
			functionGL BMRcalc = new functionGL();
			bmr = BMRcalc.BMR_Calculator(weight, height, age, (sex.equalsIgnoreCase("Male")? 1:0));
			if (bmr < 0) {
				throw new IllegalArgumentException();
			}
			lblResult.setText(String.format("%.2f kcal", bmr));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Invalid input");
		}
		
	}

}
