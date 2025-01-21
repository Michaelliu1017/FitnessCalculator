package calculator.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculator.functions.DailyWeightRecord;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;


public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnIdealWeight;
	private JButton btnBMR;
	private JButton btnCalorieIntake;
	private JButton btnWeightRecord;
	private JButton btnDiet;
	private JLabel lblTitle;

	public MainGUI() {
		getContentPane().setLayout(null);
		// Title
		lblTitle = new JLabel("Fitness Calculator", JLabel.CENTER);
		lblTitle.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblTitle.setBounds(175, 10, 126, 33);
		getContentPane().add(lblTitle);
		contentPane = new JPanel();
		
		// Func1: Ideal Weight Calculator
		btnIdealWeight = new JButton("Ideal Weight Calculator");
		btnIdealWeight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_IdealWeight();
			}
		});
		btnIdealWeight.setBounds(135, 65, 200, 50);
		getContentPane().add(btnIdealWeight);
		
		// Func2: Daily Basal Metabolic Rate (BMR) Calculator
		btnBMR = new JButton("BMR Caculator");
		btnBMR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_BMR();
			}
		});
		btnBMR.setBounds(135, 130, 200, 50);
		getContentPane().add(btnBMR);
		
		// Func3: Daily Calorie Intake Planning
		btnCalorieIntake = new JButton("Calorie Intake Calculator");
		btnCalorieIntake.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_CalorieIntake();
			}
		});
		btnCalorieIntake.setBounds(135, 195, 200, 50);
		getContentPane().add(btnCalorieIntake);
		
		// Func4: Daily Weight Record
		btnWeightRecord = new JButton("Weight Record");
		btnWeightRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_WeightRecord();
			}
		});
		btnWeightRecord.setBounds(135, 260, 200, 50);
		getContentPane().add(btnWeightRecord);
		
		// Func5: Food Calories Calculator(Planner)
		btnDiet = new JButton("Diet Planner");
		btnDiet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_Diet();
			}
		});
		btnDiet.setBounds(135, 325, 200, 50);
		getContentPane().add(btnDiet);

	}
	// Switch to Ideal Weight Calculator GUI
	public void click_IdealWeight() {
		WeightCalculatorGUI wGUI = new WeightCalculatorGUI();
		wGUI.setTitle("Ideal Weight Calculator");
		wGUI.setBounds(400, 400, 500, 500);
		wGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wGUI.setVisible(true);
		this.setVisible(false);
	}
	// Switch to BMR Calculator GUI
	public void click_BMR() {
		BMRGUI bGUI = new BMRGUI();
		bGUI.setTitle("Basal Metabolic Rate Calculator");
		bGUI.setBounds(400, 400, 500, 500);
		bGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bGUI.setVisible(true);
		this.setVisible(false);
	}
	// Switch to Calorie Intake GUI
	public void click_CalorieIntake() {
		CalorieIntakeGUI cGUI = new CalorieIntakeGUI();
		cGUI.setTitle("Calorie Intake Calculator");
		cGUI.setBounds(400, 400, 500, 500);
		cGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cGUI.setVisible(true);
		this.setVisible(false);
	}
	// Switch to Weight Record GUI
	public void click_WeightRecord() {
        WeightRecordGUI rGUI = new WeightRecordGUI();
		rGUI.setTitle("Weight Record");
		rGUI.setBounds(400, 400, 500, 500);
		rGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rGUI.setVisible(true);
		this.setVisible(false);
	}
	// Switch to Diet Planner GUI
	public void click_Diet() {
		FoodCalorieGUI dGUI = new FoodCalorieGUI();
        dGUI.setTitle("Diet Planner");
        dGUI.setBounds(400, 400, 500, 500);
		dGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dGUI.setVisible(true);
		this.setVisible(false);
	}
}
