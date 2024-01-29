package edu.appstate.cs.projectname;

import java.io.File;

/**
 * The RecipeDelete class.
 *
 * Mostly used for testing, deleting
 * all files from savedrecipes directory.
 */
public class RecipeDelete {
/**
 * The path field.
 *
 * Directory files are deleted from.
 */
private File path = new File(".\\savedrecipes\\");

/**
 * The recipeDeleteAll method.
 *
 * Deletes every recipe.
 */
public void recipeDeleteAll() {
	File[] recipefiles = path.listFiles();
	if (recipefiles.length > 0) {
		for (File file : recipefiles) {
			file.delete();
		}
	}
}
}
