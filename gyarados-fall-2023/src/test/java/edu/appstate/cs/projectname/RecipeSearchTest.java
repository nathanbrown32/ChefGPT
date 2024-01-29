package edu.appstate.cs.projectname;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Various tests for RecipeSearch.
 *
 *
 */
public class RecipeSearchTest {
	RecipeDelete d = new RecipeDelete();
	final String path = ".\\savedrecipes\\";
	
	/**
	 * Testing recipeSearch.
	 * 
	 * This test relies on the RecipeAdd class and its method
	 * for convenience.
	 * Should assert equals 1 since the string passed
	 * equals the one recipe name saved.
	 */
	@Test
	public void recipeSearchTest1() throws IOException {
		d.recipeDeleteAll();
		RecipeAdd r = new RecipeAdd("search", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		RecipeSearch s = new RecipeSearch("search");
		s.recipeSearch();
		assertEquals(s.getResults(), "1 recipe found.");
	}
}
