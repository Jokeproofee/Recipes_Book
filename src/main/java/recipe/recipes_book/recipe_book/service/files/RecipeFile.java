package recipe.recipes_book.recipe_book.service.files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipe.recipes_book.recipe_book.model.Recipe;

import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeFile {
    private long id;
    private TreeMap<Long, Recipe> recipeFileMap;
}