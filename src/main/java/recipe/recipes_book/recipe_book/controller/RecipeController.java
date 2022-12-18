package recipe.recipes_book.recipe_book.controller;

import org.springframework.web.bind.annotation.*;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.impl.IngredientServiceImpl;
import recipe.recipes_book.recipe_book.service.impl.RecipeServiceImpl;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    private final IngredientServiceImpl ingredientService;

    public RecipeController(RecipeServiceImpl recipeService, IngredientServiceImpl ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping()
    public String project(){
        return "Приложение запущено";
    }

    @PostMapping("/createRecipe/{id}")
    public Recipe createRecipe(@PathVariable("id")String id, @RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(id,recipe);
    }
    @PostMapping("/createIngredient/{id}")
    public Ingredient createIngredient(@PathVariable("id")String id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(id,ingredient);
    }

    @GetMapping("/getRecipes/{id}")
    public Set<String> getAllRecipes(@PathVariable("id") String id ) {
        return Collections.singleton(this.recipeService.getRecipe(id).toString());
    }
    @GetMapping("/getIngredients/{id}")
    public Set<String> getAllIngredients(@PathVariable("id") String id ) {
        return Collections.singleton(this.ingredientService.getIngredient(id).toString());
    }

}
