package recipe.recipes_book.recipe_book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.impl.RecipeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        this.recipeService.addRecipe(recipe);
        return ResponseEntity.ok().body(recipe);
    }
    @GetMapping
    public ResponseEntity<String> getAllRecipe() {
        List<Recipe> recipe = recipeService.getAllRecipe();
        return ResponseEntity.ok(recipe.toString());
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getRecipeById(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe.toString());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Long> editRecipeById(@PathVariable("id") Long id , @RequestBody Recipe recipe) {
        recipeService.editRecipe(id , recipe);
        return ResponseEntity.ok(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteRecipeById(@PathVariable("id") Long id ) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok(id);
    }
}