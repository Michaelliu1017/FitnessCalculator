package calculator.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import calculator.functions.DailyWeightRecord;
import calculator.functions.FoodFetcher;

public class FoodCalorieGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFood;
	private JButton btnDelete;
	private JLabel lblFood;
	private JLabel lblg;
	private JLabel lblTotalCal;
	private JLabel lblResult;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnBack;
	private JTable table;
	private JLabel lblQuantity;
	private JTextField textQuantity;
	private JComboBox<String> boxSuggestion;
	private DefaultTableModel tableModel;
	private List<String> nameList;
	private double total_cal;
	private FoodFetcher ff;
	private String savePath;

	public FoodCalorieGUI() {
		ff = new FoodFetcher();
		total_cal = 0;
		savePath = "diet_data.csv";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFood = new JLabel("Food:");
		lblFood.setBounds(90, 26, 46, 14);
		contentPane.add(lblFood);
		
		textFood = new JTextField();
		textFood.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> makeSuggestion());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> makeSuggestion());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> makeSuggestion());
			}
		});
		textFood.setBounds(132, 23, 150, 20);
		contentPane.add(textFood);
		textFood.setColumns(10);
		
		nameList = ff.get_food_names();
        
		boxSuggestion = new JComboBox<>();
		boxSuggestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> insertSuggestion());
			}
		});
		boxSuggestion.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				boxSuggestion.setVisible(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				boxSuggestion.setVisible(false);
			}
		});
		boxSuggestion.setBounds(132, 43, 150, 20);
		contentPane.add(boxSuggestion);
		boxSuggestion.setVisible(false);
		boxSuggestion.setEditable(false);
		
		
		lblg = new JLabel("g");
		lblg.setBounds(234, 67, 22, 14);
		contentPane.add(lblg);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_add();
			}
		});
		btnAdd.setBounds(300, 42, 68, 23);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_delete();
			}
		});
		btnDelete.setBounds(311, 335, 89, 23);
		contentPane.add(btnDelete);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_save();
			}
		});
		btnSave.setBounds(57, 335, 99, 23);
		contentPane.add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_back();
			}
		});
		btnBack.setBounds(187, 375, 89, 23);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 102, 343, 190);
		contentPane.add(scrollPane);
		
		String [] columnNames = {"Food", "Quantity(g)", "Calories(kcal)"};
		tableModel = new DefaultTableModel(columnNames, 0);
                table = new JTable(tableModel) {
        		// Disable cell editing
	        	@Override
	        	public boolean isCellEditable(int row, int column) {                
	                	return false;               
        		};
        	};
        	scrollPane.setViewportView(table);
		
		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(74, 61, 59, 20);
		contentPane.add(lblQuantity);
		

		textQuantity = new JTextField();
		textQuantity.setBounds(132, 61, 100, 20);
		contentPane.add(textQuantity);
		textQuantity.setColumns(10);
		
		lblTotalCal = new JLabel("Total Calories: ");
		lblTotalCal.setBounds(57, 295, 99, 14);
		contentPane.add(lblTotalCal);
		
		lblResult = new JLabel("-");
		lblResult.setBounds(143, 295, 89, 14);
		contentPane.add(lblResult);
		
	}
	
	// Fill items in boxSuggestion and display if any matches
	public void makeSuggestion() {
		String input;
		input = textFood.getText();
		boxSuggestion.removeAllItems();
//		boxSuggestion.addItem("-");
		if (input.length() > 0){
			for (String item: nameList) {
				if (item.toLowerCase().contains(input.toLowerCase())) {
					boxSuggestion.addItem(item);
				}
			}
		}
		if (boxSuggestion.getItemCount() > 0) {
			boxSuggestion.setVisible(true);
			if (getMostRecentFocusOwner()!=textFood) {
				boxSuggestion.setVisible(false);
			}
			boxSuggestion.setSelectedIndex(-1);
		}else {
			boxSuggestion.setVisible(false);
		}
	}
	
	// Insert the selected suggestion to the Textfield
	public void insertSuggestion() {
		if (boxSuggestion.getSelectedItem() != null) {
			textFood.setText((String)boxSuggestion.getSelectedItem());
			boxSuggestion.setVisible(false);
		}
		
	}
	
	// Update total calorie in GUI
	public void updateTotalCal() {
		if (total_cal > 0) {
			lblResult.setText(String.format("%.2f", total_cal));
		}else {
			lblResult.setText("-");
		}
	}
	
	// Always to remember update total_cal
	public void click_add() {
		int food_id;
		String food_name;
		double quantity;
		double calorie;
		try {
			food_name = textFood.getText();
			food_id = nameList.indexOf(food_name);
			if (food_id == -1) {
				throw new ArrayIndexOutOfBoundsException();
			}
			quantity = Double.parseDouble(textQuantity.getText());
			if (quantity <= 0) {
				throw new IllegalArgumentException();
			}
			calorie = ff.get_calorie(food_id, quantity);
			total_cal += calorie;
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] {food_name, quantity, String.format("%.2f",calorie)});
			updateTotalCal();
		}catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Food type not found");
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Invalid quantity");
		}
	}
	
	public void click_delete() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {
			double calorie = Double.parseDouble((String)table.getValueAt(table.getSelectedRow(), 2));
			model.removeRow(table.getSelectedRow());
			total_cal -= calorie;
			updateTotalCal();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please select a valid row");
		}
	}
	
	public void click_save() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		File file = new File(savePath);
		
		try {
			if (model.getRowCount()==0) {
				throw new IllegalArgumentException();
			}
			FileWriter csv = new FileWriter(file);
			for (int i = 0; i<model.getColumnCount(); i++) {
				csv.write(model.getColumnName(i)+",");
			}
			csv.write("\n");
			
			for (int x = 0; x<model.getRowCount(); x++) {
				for (int y = 0; y<model.getColumnCount(); y++) {
					csv.write(model.getValueAt(x, y)+",");
				}
				csv.write("\n");
			}
			
			csv.close();
			JOptionPane.showMessageDialog(null, "Data successfully saved in "+"\""+savePath+"\"");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Cannot export empty list");
		}
	}
	
	public void click_back() {
		Main.mainGUI.setVisible(true);
		this.dispose();
	}
	
}
