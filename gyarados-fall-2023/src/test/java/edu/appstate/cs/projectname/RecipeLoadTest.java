package edu.appstate.cs.projectname;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Various tests for the RecipeLoad class.
 *
 *
 *
 */
public class RecipeLoadTest {
	RecipeDelete d = new RecipeDelete();
	final String path = ".\\savedrecipes\\";

	/**
	 * Testing loadRecipes.
	 * 
	 * This should assert equals since there should be
	 * an ArrayList of size 0 since there are no recipes
	 * saved in the savedrecipes folder.
	 */
	@Test
	public void RecipeLoadTest1() throws IOException{
		/*
		 * ArrayList with size 1 created to ensure that
		 * the ArrayList size is actually being changed
		 * to 0.
		 */
		d.recipeDeleteAll();
		RecipeLoad l = new RecipeLoad();
		ArrayList<String> results = l.recipeLoad();
		assertEquals(results.size(), 0);
	}
	
	/**
	 * Testing loadRecipes.
	 * 
	 * This test relies on the RecipeAdd class and its method
	 * for convenience.
	 * This should assert equals since there should be
	 * an ArrayList of size 25 since that is the number
	 * of recipes saved in the savedrecipes folder.
	 */
	@Test
	public void RecipeLoadTest2() throws IOException{
		d.recipeDeleteAll();
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		for (int i = 0; i < 25; i++) {
			r.recipeAdd();
		}
		RecipeLoad l = new RecipeLoad();
		ArrayList<String> results = l.recipeLoad();
		assertEquals(results.size(), 25);
	}
	
	/**
	 * Testing recipeRead.
	 *
	 * This should assert equals since there should be
	 * an array of size 0, given an ArrayList of size 0.
	 */
	@Test
	public void RecipeReadTest1() {
		ArrayList<String> input = new ArrayList<>(0);
		/*
		 * Array with size 1 created to ensure that
		 * the array size is actually being read
		 * to 0.
		 */
		String[] results = new String[1];
		RecipeLoad l = new RecipeLoad();
		try {
			results = l.recipeRead(input);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		assertEquals(results.length, 0);
	}
	
	/**
	 * Testing recipeRead.
	 *
	 * This test relies on the RecipeAdd class and its method
	 * for convenience.
	 * This should assert equals since there should be
	 * an array of size 175, given an ArrayList of size 25.
	 */
	@Test
	public void RecipeReadTest2() throws IOException {
		int testVariable = 25;
		ArrayList<String> input = new ArrayList<>();
		d.recipeDeleteAll();
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		for (int i = 0; i < testVariable; i++) {
			input.add(r.getName() + ".txt");
		}
		RecipeLoad l = new RecipeLoad();
		String[] results =  new String[l.recipeRead(input).length];
		assertEquals(results.length, 175);
	}
	
	/**
	 * Testing recipeSearchLoad.
	 *
	 * This test relies on the RecipeAdd class and its method
	 * for convenience.
	 * This should assert equals since the size of the created
	 * ArrayList given the saved recipes should be 3, with
	 * one file that matches exactly with the given string,
	 * one file that matches except for being in uppercase,
	 * one file that matches at the beginning with added
	 * characters at the end, and one file that is completely
	 * different (the file that isn't counted in recipeSearchLoad).
	 */
	@Test
	public void RecipeSearchLoadTest1() throws IOException{
		ArrayList<String> result = new ArrayList<>();
		d.recipeDeleteAll();
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		r = new RecipeAdd("testing", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		r = new RecipeAdd("TEST", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		r = new RecipeAdd("banana", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		RecipeLoad l = new RecipeLoad();
		result = l.recipeSearchLoad("test");
		assertEquals(result.size(), 3);
	}
	
	/**
	 * Testing recipeSearchLoad.
	 *
	 * This test relies on the RecipeAdd class and its method
	 * for convenience.
	 * This should return equals since the string
	 * passed to the recipeSearchLoad method does not
	 * match with the filename. The size of the
	 * ArrayList should be 0, in contrast to the
	 * 1 file that exists.
	 */
	@Test
	public void RecipeSearchLoadTest2() throws IOException{
		ArrayList<String> result = new ArrayList<>();
		d.recipeDeleteAll();
		RecipeAdd r = new RecipeAdd("test", "0", "0", "0", "0", "Snack");
		r.recipeAdd();
		RecipeLoad l = new RecipeLoad();
		result = l.recipeSearchLoad("no");
		assertEquals(result.size(), 0);
	}
	
}
