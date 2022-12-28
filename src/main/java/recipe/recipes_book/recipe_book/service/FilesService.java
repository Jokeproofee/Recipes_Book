package recipe.recipes_book.recipe_book.service;

import java.io.File;

public interface FilesService {
    void saveFileRecipe(String json);

    void saveFileIngredient(String json);

    String readFileRecipe();

    String readFileIngredient();

    File getRecipeFile();

    File getIngredientFile();
}