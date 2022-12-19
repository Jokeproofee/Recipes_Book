package recipe.recipes_book.recipe_book.service.impl;

import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.RecipeService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<String, Recipe> recipeMap = new LinkedHashMap<>();

    @Override
    public Recipe addRecipe(String id,Recipe recipe) {
        if (!recipeMap.containsKey(id)){
            return recipeMap.put(id,recipe);

        } else {
            throw new RuntimeException("id уже существует!");
        }
    }

    @Override
    public Recipe getRecipe(String id) {
        if (recipeMap.containsKey(id)){
            return recipeMap.get(id);
        } else {
            throw new RuntimeException("id не найден!");
        }
    }
}
