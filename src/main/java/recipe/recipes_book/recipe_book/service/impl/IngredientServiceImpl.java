package recipe.recipes_book.recipe_book.service.impl;

import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.IngredientService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    public Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();

    @Override
    public Ingredient addIngredient(String id,Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)){
            return ingredientMap.put(id,ingredient);
        } else {
            throw new RuntimeException("id уже существует!");
        }
    }

    @Override
    public Ingredient getIngredient(String id) {
        if (ingredientMap.containsKey(id)){
            return ingredientMap.get(id);
        } else {
            throw new RuntimeException("id не найден!");
        }
    }
}
