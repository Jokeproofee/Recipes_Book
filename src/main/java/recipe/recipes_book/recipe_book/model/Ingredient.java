package recipe.recipes_book.recipe_book.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int weight;
    private String measureUnit;
}