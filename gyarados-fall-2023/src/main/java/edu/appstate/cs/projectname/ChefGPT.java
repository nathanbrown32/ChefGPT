package edu.appstate.cs.projectname;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;

public class ChefGPT {

	/**
	 * JFrame variable.
	 */
	private JFrame frame;
	/**
	 * JTextfield txtChefgpt variable.
	 */
	private JTextField txtChefgpt;
	/**
	 * JTextfield name variable.
	 */
	private JTextField name;
	/**
	 * JTable variable.
	 */
	private JTable table;
	/**
	 * JTextfield calories variable.
	 */
	private JTextField calories;
	/**
	 * JTextfield protein variable.
	 */
	private JTextField protein;
	/**
	 * JTextfield carbs variable.
	 */
	private JTextField carbs;
	/**
	 * JTextfield totalFat variable.
	 */
	private JTextField totalFat;
	/**
	 * JComboBox of strings for type.
	 */
	private JComboBox<String> type;
	/**
	 * String calorieValue variable.
	 */
	private String calorieValue = "";
	/**
	 * String proteinValue variable.
	 */
	private String proteinValue = "";
	/**
	 * String carbValue variable.
	 */
	private String carbValue = "";
	/**
	 * String fatValue variable.
	 */
	private String fatValue = "";
	/**
	 * int totalCalories variable.
	 */
	private int totalCalories = 0;
	/**
	 * int totalProtein variable.
	 */
	private int totalProtein = 0;
	/**
	 * int totalCarbs variable.
	 */
	private int totalCarbs = 0;
	/**
	 * int totalTotalFat variable.
	 */
	private int totalTotalFat = 0;
	/**
	 * the number of lines in a saved file.
	 */
	public static final int NUM_OF_LINES = 7;

/**
 * Launch the application.
 *
 * @param args launches application
 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefGPT window = new ChefGPT();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChefGPT() {
		initialize();
		load();
	}

	/**
	 * The clearTable method.
	 *
	 * Clears the data table in the GUI holding the recipes.
	 */
	public void clearTable() {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		tbl.setRowCount(0);
		resetDailyTotals();
	}
	
	/**
	 * The load method.
	 *
	 * Loads recipe information into data table in GUI.
	 */
	public void load() {
		RecipeLoad load = new RecipeLoad();
		try {
			ArrayList<String> recipeload = load.recipeLoad();
			String[] reciperead = load.recipeRead(recipeload);
			/*
			 * reciperead[0] is skipped since it contains the date.
			 */
			for (int i = 0; i < (reciperead.length / NUM_OF_LINES); i++) {
				String[] data = {reciperead[((i  * NUM_OF_LINES) + 1)],
						reciperead[((i  * NUM_OF_LINES) + 2)],
						reciperead[((i  * NUM_OF_LINES) + 3)],
						reciperead[((i  * NUM_OF_LINES) + 4)],
						reciperead[((i  * NUM_OF_LINES) + 5)],
						reciperead[((i  * NUM_OF_LINES) + 6)]};
				DefaultTableModel tbl = (DefaultTableModel) table.getModel();
				tbl.addRow(data);
			}
			
			calculateDailyTotals();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The searchLoad method.
	 *
	 * Loads specific recipe information into GUI table.
	 * @param search the string entered in the search bar
	 */
	public void searchLoad(final String search) {
		RecipeLoad load = new RecipeLoad();
		try {
			ArrayList<String> recipeload = load.recipeSearchLoad(search);
			String[] reciperead = load.recipeRead(recipeload);
			/*
			 * reciperead[0] is skipped since that contains the date.
			 */
			for (int i = 0; i < (reciperead.length / NUM_OF_LINES); i++) {
				String[] data = {reciperead[((i  * NUM_OF_LINES) + 1)],
						reciperead[((i  * NUM_OF_LINES) + 2)],
						reciperead[((i  * NUM_OF_LINES) + 3)],
						reciperead[((i  * NUM_OF_LINES) + 4)],
						reciperead[((i  * NUM_OF_LINES) + 5)],
						reciperead[((i  * NUM_OF_LINES) + 6)]};
				DefaultTableModel tbl = (DefaultTableModel) table.getModel();
				tbl.addRow(data);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to calculate the daily totals.
	 */
	public void calculateDailyTotals() {
		try {
			int i = 0;
			totalCalories = 0;
			totalProtein = 0;
			totalCarbs = 0;
			totalTotalFat = 0;
			while (i < table.getModel().getRowCount()) {
				calorieValue = (String) table.getModel().getValueAt(i, 1);
				proteinValue = (String) table.getModel().getValueAt(i, 2);
				carbValue = (String) table.getModel().getValueAt(i, 3);
				fatValue = (String) table.getModel().getValueAt(i, 4);
				try {
					int calorieInput = Integer.parseInt(calorieValue);
					totalCalories = totalCalories + calorieInput;

					int proteinInput = Integer.parseInt(proteinValue);
					totalProtein = totalProtein + proteinInput;

					int carbsInput = Integer.parseInt(carbValue);
					totalCarbs = totalCarbs + carbsInput;

					int totalFatInput = Integer.parseInt(fatValue);
					totalTotalFat = totalTotalFat + totalFatInput;
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to reset daily totals.
	 */
	public void resetDailyTotals() {
		totalCalories = 0;
		totalProtein = 0;
		totalCarbs = 0;
		totalTotalFat = 0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame(); //initializes the frame itself
		frame.setBounds(100, 100, 600, 650); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ChefGPT");
		frame.getContentPane().setLayout(null);

		txtChefgpt = new JTextField(); //initializes the logo
		txtChefgpt.setBounds(230, 10, 115, 40); 
		txtChefgpt.setForeground(Color.WHITE);
		txtChefgpt.setBackground(Color.BLACK);
		txtChefgpt.setEditable(false);
		txtChefgpt.setFont(new Font("Verdana", Font.BOLD, 21));
		txtChefgpt.setText("ChefGPT");
		frame.getContentPane().add(txtChefgpt);
		txtChefgpt.setColumns(10);

		JPanel panel = new JPanel(); //initializes add recipe section
		panel.setBounds(10, 55, 250, 270);
		panel.setBorder(new TitledBorder(null, "Add Recipe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name: "); //initializes name
		lblNewLabel.setBounds(10, 25, 75, 20);
		panel.add(lblNewLabel);

		JLabel lblCalories = new JLabel("Calories: "); //calories label
		lblCalories.setBounds(10, 60, 75, 20);
		panel.add(lblCalories);

		JLabel lblProtein = new JLabel("Protein(g): "); //protein label
		lblProtein.setBounds(10, 95, 75, 20);
		panel.add(lblProtein);

		JLabel lblCarbs = new JLabel("Carbs(g): "); //carbs label
		lblCarbs.setBounds(10, 130, 75, 20);
		panel.add(lblCarbs);

		JLabel lblTotalFat = new JLabel("Total Fat(g): "); //fat label
		lblTotalFat.setBounds(10, 165, 75, 20);
		panel.add(lblTotalFat);

		JLabel lblType = new JLabel("Type:\r\n"); //type label
		lblType.setBounds(10, 200, 75, 20);
		panel.add(lblType);

		name = new JTextField(); //initializes name input area
		name.setBounds(80, 25, 150, 20);
		panel.add(name);
		name.setColumns(10);

		calories = new JTextField(); //calories input area
		calories.setBounds(80, 60, 150, 20);
		panel.add(calories);

		protein = new JTextField(); //protein input area
		protein.setBounds(80, 95, 150, 20);
		panel.add(protein);

		carbs = new JTextField(); //carbs input area
		carbs.setBounds(80, 130, 150, 20);
		panel.add(carbs);

		totalFat = new JTextField(); //total fat input area
		totalFat.setBounds(80, 165, 150, 20);
		panel.add(totalFat);

		String[] mealType = {"", "Breakfast", "Lunch", "Dinner", "Desert", "Snack"};
		type = new JComboBox<>(mealType); //type dropdown
		type.setBounds(80, 200, 150, 20);
		panel.add(type);

		JButton btnClearButton = new JButton("Clear"); //add recipe clear button
		btnClearButton.setBounds(10, 235, 90, 25);
		panel.add(btnClearButton);

		JButton btnAdd = new JButton("Save"); //recipe save button
		btnAdd.setBounds(135, 235, 90, 25);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) { //save pressed
				if (name.getText().equals("")
					|| calories.getText().equals("")
					|| protein.getText().equals("")
					|| carbs.getText().equals("")
					|| totalFat.getText().equals("")
					|| type.getSelectedItem().toString().equals("")) 
				{
					JOptionPane.showMessageDialog(null, "Missing Data",
							"Missing Data", JOptionPane.ERROR_MESSAGE);
				}
				else if (!(calories.getText().matches("\\d+"))
						|| !(protein.getText().matches("\\d+"))
						|| !(carbs.getText().matches("\\d+"))
						|| !(totalFat.getText().matches("\\d+"))) {
					/*
					 * Invalid input if anything but an integer number is
					 * entered for these categories.
					 */
					JOptionPane.showMessageDialog(null,  "Invalid Input",
							"Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						RecipeAdd recipe = new RecipeAdd(name.getText(), calories.getText(), 
								protein.getText(), carbs.getText(), totalFat.getText(), 
								type.getSelectedItem().toString());
						recipe.recipeAdd();
					} catch (IOException e1) {
						e1.printStackTrace(); //this shouldn't happen but it wanted to check for IOException
					}
					String[] data = {name.getText(), calories.getText(), 
									protein.getText(), carbs.getText(), totalFat.getText(), 
									type.getSelectedItem().toString()};
					DefaultTableModel tbl = (DefaultTableModel) table.getModel();
					tbl.addRow(data);
					calculateDailyTotals();
					name.setText("");
					calories.setText("");
					protein.setText("");
					carbs.setText("");
					totalFat.setText("");
					type.setSelectedItem("");
				}
			}
		});
		btnClearButton.addActionListener(new ActionListener() { //what happens when clear button pressed
			public void actionPerformed(final ActionEvent e) {
				name.setText("");
				calories.setText("");
				protein.setText("");
				carbs.setText("");
				totalFat.setText("");
				type.setSelectedItem("");
			}
		});

		JPanel searchPanel = new JPanel(); //adds search section
		searchPanel.setBounds(10, 340, 250, 200);
		searchPanel.setBorder(new TitledBorder(null, "Search", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);

		JLabel searchNameLBL = new JLabel("Name: "); //search name lbl
		searchNameLBL.setBounds(10, 25, 150, 20);
		searchPanel.add(searchNameLBL);

		JTextField searchNameTF = new JTextField(); //name text input in search
		searchNameTF.setBounds(80, 25, 150, 20);
		searchNameTF.setColumns(10);
		searchPanel.add(searchNameTF);

		JButton searchBtn = new JButton("Search"); //adds search button
		searchBtn.setBounds(10, 95, 90, 25);
		searchPanel.add(searchBtn);

		JButton clearSearchBtn = new JButton("Clear"); //search clear
		clearSearchBtn.setBounds(130, 95, 90, 25);
		searchPanel.add(clearSearchBtn);

		JLabel searchPressedLBL = new JLabel(""); //search feedback
		searchPressedLBL.setBounds(10, 120, 500, 30);
		searchPanel.add(searchPressedLBL);

		JLabel searchResultLBL = new JLabel(""); //search result
		searchResultLBL.setBounds(10, 150, 300, 30);
		searchPanel.add(searchResultLBL);

		searchBtn.addActionListener(new ActionListener() { //search button pressed
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (searchNameTF.getText().equals("")) {
					searchPressedLBL.setText("No input.");
				}
				else {
					searchPressedLBL.setText("You searched for: " + searchNameTF.getText());
					String s = searchNameTF.getText();
					RecipeSearch search = new RecipeSearch(s);
					search.recipeSearch();
					searchResultLBL.setText(search.getResults());
					searchLoad(s);
					calculateDailyTotals();
					searchNameTF.setText("");
				}
			}
		});

		clearSearchBtn.addActionListener(new ActionListener() {	//search clear button pressed
			public void actionPerformed(final ActionEvent e) {
				searchNameTF.setText("");
				searchPressedLBL.setText("");
				searchResultLBL.setText("");
				clearTable();
				load();
			}
		});

		JPanel counterPanel = new JPanel(); //adds counter section
		counterPanel.setBounds(270, 400, 300, 200);
		counterPanel.setBorder(new TitledBorder(null, "Counter", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(counterPanel);
		counterPanel.setLayout(null);

		JLabel counterTotalsLBL = new JLabel("Daily Totals"); //daily total label
		counterTotalsLBL.setBounds(10, 20, 150, 20);
		counterPanel.add(counterTotalsLBL);

		JLabel counterCalLBL = new JLabel("Total Calories: "); //total calorie label
		counterCalLBL.setBounds(10, 55, 150, 20);
		counterPanel.add(counterCalLBL);
		
		JLabel counterCalTotalLBL = new JLabel("0"); //total calorie number
		counterCalTotalLBL.setBounds(250, 55, 100, 20);
		counterPanel.add(counterCalTotalLBL);

		JLabel counterProtLBL = new JLabel("Total Protein: "); //total protein label
		counterProtLBL.setBounds(10, 90, 150, 20);
		counterPanel.add(counterProtLBL);
		
		JLabel counterProtTotalLBL = new JLabel("0"); //total protein number
		counterProtTotalLBL.setBounds(250, 90, 100, 20);
		counterPanel.add(counterProtTotalLBL);

		JLabel counterCarbsLBL = new JLabel("Total Carbs: "); //total carbs label
		counterCarbsLBL.setBounds(10, 125, 150, 20);
		counterPanel.add(counterCarbsLBL);
		
		JLabel counterCarbsTotalLBL = new JLabel("0"); //total carbs number
		counterCarbsTotalLBL.setBounds(250, 125, 100, 20);
		counterPanel.add(counterCarbsTotalLBL);

		JLabel counterFatLBL = new JLabel("Total Fat: "); //total fat label
		counterFatLBL.setBounds(10, 160, 150, 20);
		counterPanel.add(counterFatLBL);
		
		JLabel counterFatTotalLBL = new JLabel("0"); //total fat number
		counterFatTotalLBL.setBounds(250, 160, 100, 20);
		counterPanel.add(counterFatTotalLBL);
		
		JButton refreshTotalsBTN = new JButton("Refresh"); //counter refresh button
		refreshTotalsBTN.setBounds(200, 20, 90, 25);
		counterPanel.add(refreshTotalsBTN);
		refreshTotalsBTN.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(final ActionEvent e) {
				counterCalTotalLBL.setText(Integer.toString(totalCalories));
				counterProtTotalLBL.setText(Integer.toString(totalProtein));
				counterCarbsTotalLBL.setText(Integer.toString(totalCarbs));
				counterFatTotalLBL.setText(Integer.toString(totalTotalFat));
			}
		});
		
		JButton btnClearTableButton = new JButton("Clear Table"); //clear table button
		btnClearTableButton.setBounds(370, 372, 100, 25);
		frame.add(btnClearTableButton); 
		btnClearTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				clearTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(); //scroll pane to view inputed recipes
		scrollPane.setBounds(270, 65, 300, 300);
		frame.getContentPane().add(scrollPane);

		table = new JTable(); //creates table to show inputed recipes
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new CompoundBorder());
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name: ", "Cals: ", "Prot: ", "Carbs: ",
				"Fat: ", "Type: " //Main Labels
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(final int row, final int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(55); 
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(55);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		scrollPane.setViewportView(table);
	}
}

