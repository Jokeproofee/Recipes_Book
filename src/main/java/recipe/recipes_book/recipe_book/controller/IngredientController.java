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
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.impl.IngredientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты.",description = "CRUD- методы для работы с ингредиентами.")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление ингредиента.",
            description = "Метод для добавления ингредиента в книгу."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был добавлен.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Ingredient.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> createIngredient(@RequestBody Ingredient ingredient) {
        this.ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }
    @GetMapping
    @Operation(
            summary = "Получение всех ингредиентов.",
            description = "Метод для получениея всех ингредиентов из книги."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все ингредиенты были получены.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Ingredient.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<List<Ingredient>> getAllIngredient() {
        List<Ingredient> ingredientList = ingredientService.getAllIngredient();
        return ResponseEntity.ok(ingredientList);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента по id.",
            description = "Метод для получения ингредиента по id из книги."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был получен.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Ingredient.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение ингредиента по id.",
            description = "Метод для изменения ингредиента по id в книге."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был изменён.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Ingredient.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> editIngredientById(@PathVariable("id") Long id , @RequestBody Ingredient ingredient) {
        ingredientService.editIngredient(id , ingredient);
        return ResponseEntity.ok(id);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удание ингредиента по id.",
            description = "Метод для удаления ингредиента по id в книге."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был удалён.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Ingredient.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Long> deleteIngredientById(@PathVariable("id") Long id ) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(id);
    }
}