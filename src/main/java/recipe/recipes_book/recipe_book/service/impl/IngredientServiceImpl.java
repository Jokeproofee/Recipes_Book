package recipe.recipes_book.recipe_book.service.impl;

import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.IngredientService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    public Map<Long, Ingredient> ingredientMap = new LinkedHashMap<>();

    private static Long id = 1L;

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++,ingredient);
    }
    @Override
    public Ingredient getIngredient(Long id) {
        if (!ingredientMap.containsKey(id)){
            throw new RuntimeException("id не найден!");
        }
        return ingredientMap.get(id);
    }
    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        ingredientMap.remove(id);
    }
    @Override
    public void editIngredient(Long id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        ingredientMap.put(id,ingredient);
    }
    @Override
    public List<Ingredient> getAllIngredient() {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (Map.Entry<Long, Ingredient> ingredientEntry : ingredientMap.entrySet()) {
            ingredientList.add(ingredientEntry.getValue());
        }
        return ingredientList;
    }
}