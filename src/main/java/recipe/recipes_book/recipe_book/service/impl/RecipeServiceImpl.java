package recipe.recipes_book.recipe_book.service.impl;

import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.RecipeService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Long, Recipe> recipeMap = new LinkedHashMap<>();

    private static Long id = 1L;

    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
    }
    @Override
    public Recipe getRecipe(Long id) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        return recipeMap.get(id);
    }
    @Override
    public void deleteRecipe(Long id) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        recipeMap.remove(id);
    }
    @Override
    public void editRecipe(Long id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        recipeMap.put(id, recipe);
    }
    @Override
    public List<Recipe> getAllRecipe() {
        List<Recipe> recipeList = new ArrayList<>();
        for (Map.Entry<Long, Recipe> recipeEntry : recipeMap.entrySet()) {
            recipeList.add(recipeEntry.getValue());
        }
        return recipeList;
    }
}