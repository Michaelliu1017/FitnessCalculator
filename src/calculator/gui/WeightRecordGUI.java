package calculator.gui;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;

import calculator.functions.DailyWeightRecord;

import java.text.*;


public class WeightRecordGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textWeight;
	private JButton btnDelete;
	private JLabel lblWeight;
	private JLabel lblKg;
	private JButton btnAdd;
	private JButton btnViewTrend;
	private JButton btnBack;
	private JTable table;
	private JLabel lblDate;
	private JTextField textDate;
	private DefaultTableModel tableModel;
    private DailyWeightRecord weightRecord;

	public WeightRecordGUI() {
		this.weightRecord = new DailyWeightRecord();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(100, 26, 46, 14);
		contentPane.add(lblWeight);
		
		textWeight = new JTextField();
		textWeight.setBounds(156, 23, 100, 20);
		contentPane.add(textWeight);
		textWeight.setColumns(10);
		
		lblKg = new JLabel("kg");
		lblKg.setBounds(263, 26, 22, 14);
		contentPane.add(lblKg);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click_add();
			}
		});
		btnAdd.setBounds(292, 36, 68, 23);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_delete();
			}
		});
		btnDelete.setBounds(177, 308, 89, 23);
		contentPane.add(btnDelete);
		
		btnViewTrend = new JButton("View Trend");
		btnViewTrend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_view();
			}
		});
		btnViewTrend.setBounds(57, 308, 99, 23);
		contentPane.add(btnViewTrend);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_back();
			}
		});
		btnBack.setBounds(286, 308, 89, 23);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 92, 318, 182);
		contentPane.add(scrollPane);
		
		String [] columnNames = {"Date", "Weight(kg)"};
		tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
		
		lblDate = new JLabel("Date:");
		lblDate.setBounds(110, 53, 33, 14);
		contentPane.add(lblDate);
		
		// You may also want add a date error checker here
		textDate = new JTextField();
		textDate.setText("yyyy-mm-dd");
		textDate.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textDate.getText().equals("yyyy-mm-dd")) {
					textDate.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textDate.getText().isEmpty()) {
					textDate.setText("yyyy-mm-dd");
				}
			}
		});

		textDate.setBounds(156, 54, 100, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);
	}
	
	
	public void click_delete() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String date = (String) tableModel.getValueAt(selectedRow, 0);
			weightRecord.deleteWeight(date);
			tableModel.removeRow(selectedRow);
		} 
	}
	
	public void click_back() {
		Main.mainGUI.setVisible(true);
		this.dispose();
	}
	
	public void click_view() {
		try {
			BufferedImage trendImage = weightRecord.generateTrendImage();
			ImageIcon icon = new ImageIcon(trendImage);
			JLabel lblGraph = new JLabel(icon);
	    	JDialog graphDialog = new JDialog(this, "Weight Trend Graph", true);
	    	graphDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    	graphDialog.setBounds(100, 100, 750, 600);
	    	graphDialog.getContentPane().add(lblGraph);
	    	graphDialog.setVisible(true);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Require at least 2 entries to generate trend");
		}
		
	}
	
	public void click_add() {
    	String date;
    	double weight;
    	try {
    		date = textDate.getText().trim();
        	weight = Double.parseDouble(textWeight.getText().trim());
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		Date input = format.parse(date);
     
        	weightRecord.addWeight(weight, date);
        	tableModel.addRow(new Object[]{date, weight});
        	textDate.setText("yyyy-mm-dd");
        	textWeight.setText("");
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Invalid input");
    	}
    	
	}
}

