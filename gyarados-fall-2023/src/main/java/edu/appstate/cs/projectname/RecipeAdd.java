package edu.appstate.cs.projectname;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * The RecipeAdd Class.
 *
 * Gets the input from the main class
 * and saves it into a .txt file.
 */
public class RecipeAdd {
	/**
	 * The name field.
	 *
	 * For the name of recipe.
	 */
	private String name;
	/**
	 * The calories field.
	 *
	 * For calories of recipe.
	 */
	private String calories;
	/**
	 * The protein field.
	 *
	 * For protein of recipe.
	 */
	private String protein;
	/**
	 * The carbs field.
	 *
	 * For carbs of recipe.
	 */
	private String carbs;
	/**
	 * The totalFat field.
	 *
	 * For total fat of recipe.
	 */
	private String totalFat;
	/**
	 * The type field.
	 *
	 * For the type of recipe.
	 */
	private String type;
	/**
	 * The date field.
	 *
	 * For the date of file creation.
	 */
	private LocalDate date;

	/**
	 * Constructor, containing parameters for name,
	 * ingredients, type.
	 *
	 * @param name name of inputed food
	 * @param calories # of calories of food
	 * @param protein # of proteins of food
	 * @param carbs # of carbs of food
	 * @param totalFat # of totalFat of food
	 * @param type type of food (ex. lunch)
	 */
	public RecipeAdd(final String name, final String calories,
			final String protein, final String carbs,
			final String totalFat, final String type) {
		this.name = name;
		this.calories = calories;
		this.protein = protein;
		this.carbs = carbs;
		this.totalFat = totalFat;
		this.type = type;
	}

	/**
	 * The getName method.
	 *
	 * A getter for the variable name.
	 *
	 * @return name name of food
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * The setName method.
	 *
	 * A setter for the variable name.
	 *
	 * @param name name of food
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * The recipeAdd method.
	 *
	 * Creates file and puts values in fields
	 * in the file.
	 *
	 * @throws IOException if file can't be written to
	 */
	public void recipeAdd() throws IOException {
		String path = ".\\savedrecipes\\";
		File file = new File(path + name + ".txt");
		//to ensure duplicates with same name can exist
		int fileNumber = 1;
		while (file.exists()) {
			file = new File(path + name + fileNumber + ".txt");
			fileNumber++;
		}
		file.createNewFile();
		ZoneId est = ZoneId.of("America/New_York");
			//unknown if factors in dst or not
		date = LocalDate.now(est);
		FileWriter fw = new FileWriter(file);
		fw.write(date.toString() + "\n" + name
				+ "\n" + calories + "\n" + protein + "\n"
				+ carbs + "\n" + totalFat + "\n" + type + "\n");
		fw.close();
	}
}
