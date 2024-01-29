package edu.appstate.cs.projectname;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Various tests for RecipeAdd class.
 * 
 * RecipeDelete is used so the tests will work even if
 * a file with the same name already exists.
 *
 */
public class RecipeAddTest {
	RecipeDelete d = new RecipeDelete();
	
	/**
	 * Testing add recipes. This should return true since
	 * the name and filename should match.
	 *  
	 */
	@Test
	public void RecipeAddTest1() {
		d.recipeDeleteAll();
		final String path = ".\\savedrecipes\\";
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		try {
		r.recipeAdd();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		File f = new File(path + r.getName() + ".txt");
		assertTrue(f.exists());
	}
	
	/**
	 * Testing add recipes. Since the name and filename do not match the
	 * file the test is checking for should not exist.
	 */
	@Test
	public void RecipeAddTest2() {
		d.recipeDeleteAll();
		final String path = ".\\savedrecipes\\";
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		try {
		r.recipeAdd();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		File f = new File(path + "qwerty" + ".txt");
		assertTrue(!(f.exists()));
	}
	
	/**
	 * Testing add recipes. This should return true since
	 * two files with the same base name should be able to
	 * exist together (with the second recipe onwards having
	 * numbers added at the end of the filename).
	 *  
	 */
	@Test
	public void RecipeAddTest3() {
		d.recipeDeleteAll();
		final String path = ".\\savedrecipes\\";
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		try {
		r.recipeAdd();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		File f = new File(path + r.getName() + ".txt");
		try {
			r.recipeAdd();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		File g = new File(path + r.getName() + "1.txt");
		assertTrue(f.exists() && g.exists());
	}

}
