package recipe.recipes_book.recipe_book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.impl.IngredientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        this.ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(ingredient);
    }
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        List<Ingredient> ingredientList = ingredientService.getAllIngredient();
        return ResponseEntity.ok(ingredientList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Long> editIngredientById(@PathVariable("id") Long id , @RequestBody Ingredient ingredient) {
        ingredientService.editIngredient(id , ingredient);
        return ResponseEntity.ok(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteIngredientById(@PathVariable("id") Long id ) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(id);
    }
}