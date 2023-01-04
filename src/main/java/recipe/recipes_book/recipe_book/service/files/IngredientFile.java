package recipe.recipes_book.recipe_book.service.files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.recipes_book.recipe_book.model.Ingredient;

import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientFile {
    private long id;
    private TreeMap<Long, Ingredient> ingredientFileMap;
}