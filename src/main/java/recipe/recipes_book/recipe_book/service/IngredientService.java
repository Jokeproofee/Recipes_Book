package recipe.recipes_book.recipe_book.service;

import recipe.recipes_book.recipe_book.model.Ingredient;

import java.util.List;

public interface IngredientService {

    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long id);

    void deleteIngredient(Long id);

    void editIngredient(Long id, Ingredient ingredient);

    List<Ingredient> getAllIngredient();
}