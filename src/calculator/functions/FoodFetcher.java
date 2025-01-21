package calculator.functions;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class FoodFetcher {
	// Initialization
	private String food_list_addr;
	private List<List<String>> food_list;
	private List<String> header;
	private List<String> names_list;
	
	public FoodFetcher() {
		// Import datasets preprocessed with python code
		names_list = new ArrayList<String>();
		food_list_addr = "food_db.csv";
		food_list = read_food_list();
		header = food_list.get(0);
		food_list.remove(0);
		
		for (List<String>row: food_list) {
			names_list.add(row.get(0));
		}
		System.out.println(names_list);
	}
	
	// Get calorie for food
	public double get_calorie(int food_id, double quantity) {
		double calorie;
		// Calorie in unit kcal
		calorie = (Double.parseDouble(food_list.get(food_id).get(1)) / 100) * quantity;
		return calorie;
	}
	
	// Fetch and get list of food names
	public List<String> get_food_names(){
		return names_list;
	}
	
	// Read nutrition data from local csv file "food_data.csv" (data preprocessed with python code)
	public List<List<String>> read_food_list(){
		List<List<String>> food = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(food_list_addr));
			String line;
			while ((line=br.readLine())!=null) {
				String[] row = line.split(",");
				food.add(Arrays.asList(row));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return food;
	}
	
}
