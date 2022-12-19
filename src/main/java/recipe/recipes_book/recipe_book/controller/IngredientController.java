package recipe.recipes_book.recipe_book.controller;

import org.springframework.web.bind.annotation.*;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.impl.IngredientServiceImpl;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient createIngredient(String id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(id,ingredient);
    }

    @GetMapping("/{id}")
    public Set<String> getAllIngredients(@PathVariable("id") String id ) {
        return Collections.singleton(this.ingredientService.getIngredient(id).toString());
    }
}