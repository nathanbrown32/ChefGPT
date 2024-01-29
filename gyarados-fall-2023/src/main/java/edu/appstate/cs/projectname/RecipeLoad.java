package edu.appstate.cs.projectname;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The RecipeLoad class.
 *
 * Loads recipe .txt files from
 * specific folder into program.
 *
 */
public class RecipeLoad {
/**
 * The NUM_OF_LINES final variable.
 *
 * Number of lines saved in files.
*/
public static final int NUM_OF_LINES = 7;
/**
 * The recipes field.
 *
 * Empty ArrayList to hold every part of recipes.
*/
private ArrayList<String> recipes = new ArrayList<String>(0);
/**
 * The linecount field.
 *
 * To count the line the file is reading.
 */
private int linecount = 0;
/**
 * The path field.
 *
 * The directory that is being read from.
 */
private File path = new File(".\\savedrecipes\\");
/**
 * The read field.
 *
 * To read the files.
 */
private Scanner read;

/**
 * The recipeLoad method.
 *
 * Loads the name of each recipe saved
 * and adds them to an ArrayList.
 *
 * @return recipes an ArrayList containing filenames holding recipes
 * @throws IOException if path can't be located
 */
public ArrayList<String> recipeLoad() throws IOException {
	File[] recipefiles = path.listFiles();
	for (File file : recipefiles) {
		recipes.add(file.getName());
	}
	return recipes;
}

/**
 * The recipeSearchLoad method.
 *
 * Takes the name string searched and loads the
 * recipes that contain that string in the filename.
 *
 * For searching for names.
 *
 * @param search the string being searched for
 * @return ArrayList of strings of recipes
 * @throws IOException if path can't be located
 */
public ArrayList<String> recipeSearchLoad(final String search) throws IOException {
	File[] recipefiles = path.listFiles();
	for (File file : recipefiles) {
		if (file.getName().toLowerCase().startsWith(search)) {
			recipes.add(file.getName());
		}
	}
	return recipes;
}

/**
 * The recipeRead method.
 *
 * Takes an ArrayList containing the names
 * of recipes and makes an array to store the
 * information contained in every recipe to
 * use when first opening the program.
 *
 * @param r ArrayList containing every part of food
 * @return data a string array containing food information
 * @throws IOException if file can't be read from
 */
public String[] recipeRead(final ArrayList<String> r) throws IOException {
String[] data = new String[NUM_OF_LINES * r.size()];
	for (int i = 0; i < r.size(); i++) {
		File newpath = new File(".\\savedrecipes\\" + r.get(i));
		read = new Scanner(newpath);
		linecount = 0;
		/*
		 * linecount count the current line in a file.
		 * The number of lines in a file will be 7.
		 * first line will be date, second line will be name,
		 * third line will be calories, fourth line will be protein,
		 * fifth line will be carbs, sixth line will be total fat,
		 * seventh line will be type.
		 */
		while (read.hasNext()) {
			data[(i * NUM_OF_LINES) + linecount] = read.nextLine();
			linecount++;
		}
		/*
		 * there is no accounting for
		 * files that aren't .txt right now
		 */
	}
	if (read != null) {
		/*
		 * to prevent a nullpointerexception
		 * when there is no saved recipes
		 */
		read.close();
	}
	return data; // holds recipe data
}
}
