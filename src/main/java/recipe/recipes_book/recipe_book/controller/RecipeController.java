package recipe.recipes_book.recipe_book.controller;

import org.springframework.web.bind.annotation.*;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.impl.RecipeServiceImpl;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe createRecipe(String id, @RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(id,recipe);
    }

    @GetMapping("/{id}")
    public Set<String> getRecipeById(@PathVariable("id") String id ) {
        return Collections.singleton(this.recipeService.getRecipe(id).toString());
    }
}