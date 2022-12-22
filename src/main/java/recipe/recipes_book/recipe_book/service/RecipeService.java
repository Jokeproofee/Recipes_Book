package recipe.recipes_book.recipe_book.service;

import recipe.recipes_book.recipe_book.model.Recipe;

import java.util.List;

public interface RecipeService {

    void addRecipe(Recipe recipe);
    Recipe getRecipe(Long id);
    void deleteRecipe(Long id);
    void editRecipe(Long id, Recipe recipe);
    List<Recipe> getAllRecipe();
}