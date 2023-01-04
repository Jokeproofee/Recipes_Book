package recipe.recipes_book.recipe_book.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.impl.RecipeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты.", description = "CRUD- методы для работы с рецептами.")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление рецепта.",
            description = "Метод для добавления рецепта в книгу."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был добавлен.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Recipe.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) {
        this.recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех рецептов.",
            description = "Метод для получениея всех рецептов из книги."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все рецепты были получены.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Recipe.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        List<Recipe> recipe = recipeService.getAllRecipe();
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта по id.",
            description = "Метод для получения рецепта по id из книги."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был получен.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Recipe.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта по id.",
            description = "Метод для изменения рецепта по id в книге."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был изменён.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Recipe.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> editRecipeById(@PathVariable("id") Long id, @RequestBody Recipe recipe) {
        recipeService.editRecipe(id, recipe);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удание рецепта по id.",
            description = "Метод для удаления рецепта по id в книге."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был удалён.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Recipe.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Long> deleteRecipeById(@PathVariable("id") Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok(id);
    }
}