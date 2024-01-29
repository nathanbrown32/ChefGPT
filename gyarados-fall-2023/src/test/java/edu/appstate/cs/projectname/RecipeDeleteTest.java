package edu.appstate.cs.projectname;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Various tests for recipeDelete class.
 *
 * Since this class is used for tests of
 * other classes, these tests will not rely
 * on other classes.
 *
 */
public class RecipeDeleteTest {
	final String path = ".\\savedrecipes\\";
	
	/**
	 * Testing RecipeDelete. Creates a file then
	 * should delete it and assert true.
	 *
	 * @throws IOException
	 */
	@Test
	public void RecipeDeleteTest1() throws IOException {
		File file = new File(path + "delete.txt");
		file.createNewFile();
		
		RecipeDelete d = new RecipeDelete();
		d.recipeDeleteAll();
		assertTrue(!(file.exists()));
	}
	
	/**
	 * Testing RecipeDelete. Deletes the
	 * already existing files in directory.
	 * Should assert true.
	 *
	 * @throws IOException
	 */
	@Test
	public void RecipeDeleteTest2() throws IOException {
		RecipeDelete d = new RecipeDelete();
		d.recipeDeleteAll();
		
		File path = new File(".\\savedrecipes\\");
		File[] recipefiles = path.listFiles();	
		assertEquals(recipefiles.length, 0);
	}
	
	
}
