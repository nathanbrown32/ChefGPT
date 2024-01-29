package edu.appstate.cs.projectname;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The RecipeSearch class.
 *
 * Given a string, the class will check the file directory
 * to see how many filenames match that given string.
 *
 */
class RecipeSearch implements FilenameFilter {
	/**
	 * The init field.
	 *
	 * Holds the string to search for.
	 */
	private String init;
	/**
	 * The searchResults field.
	 *
	 * Holds the search results.
	 */
	private String searchResults;
	/**
	 * The NUM_OF_LINES final variable.
	 *
	 * The number of lines in a saved file.
	 */
	public static final int NUM_OF_LINES = 7;

	/**
	 * Constructor for RecipeSearch.
	 *
	 * @param init the string to search for
	 */
	RecipeSearch(final String init) {
		this.init = init;
	}

	/**
	 * Method for accepting a certain String.
	 *
	 * @param dir
	 * @param name
	 * @return boolean
	 */
	public boolean accept(final File dir, final String name) {
		return init.startsWith(name);
	}

	/**
	 * Method for matching the searched thing with
	 * a certain String.
	 *
	 * @param search
	 * @param match
	 * @return whether the string matches or not
	 */
	public boolean match(final String search, final String match) {
		return (match.toLowerCase().startsWith(search));
	}

	/**
	 * Method for getting the results.
	 *
	 * @return String
	 */
	public String getResults() {
		return searchResults;
	}

	/**
	 * Method for setting the results.
	 *
	 * @param searchResults
	 */
	public void setResults(final String searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * The recipeSearch method.
	 * Searches for an existing file in the savedrecipes folder.
	 *
	 */
	public void recipeSearch() {
		String search = init;
		String[] readSearchFile = new String[0];
		//File dir = new File(".\\savedrecipes\\");
		/*
		 * load files into string array, read 2nd line for name
		 */
		RecipeLoad l = new RecipeLoad();
		try {
			ArrayList<String> searchFile = l.recipeLoad();
			readSearchFile = l.recipeRead(searchFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//RecipeSearch rs = new RecipeSearch(search);
		String[] nameList = new
				String[readSearchFile.length / NUM_OF_LINES];
		if (nameList.length == 0) {
			setResults("No recipes saved.");
		} else {
			for (int i = 0;
					i < readSearchFile.length
					/ NUM_OF_LINES; i++) {
				nameList[i]
						= readSearchFile
						[(i * NUM_OF_LINES) + 1];
				//1 for name, since it is on file's second line
			}
			int count = 0;
			for (int j = 0; j < nameList.length; j++) {
				boolean boo = match(search, nameList[j]);
				if (boo) {
					count++;
				}
			}
			if (count == 1) {
				setResults(count + " recipe found.");
			} else {
				setResults(count + " recipes found.");
			}
		}
	}
}
