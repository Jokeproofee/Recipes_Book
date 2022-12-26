package recipe.recipes_book.recipe_book.service;

public interface FilesService {
    void saveFileRecipe(String json);

    void saveFileIngredient(String json);

    String readFileRecipe();

    String readFileIngredient();
}