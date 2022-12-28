package recipe.recipes_book.recipe_book.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import recipe.recipes_book.recipe_book.service.FilesService;

import java.io.*;

@RestController
@RequestMapping("/file")
@Tag(name = "Файлы.",description = "Методы для работы с файлами.")
public class FileController {
    private final FilesService filesService;

    public FileController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/exportRecipes")
    @Operation(
            summary = "Скачать все рецепты.",
            description = "Метод для скачивания всех рецептов в виде json-файла."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл загружен."
                    )
            }
    )
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesService.getRecipeFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"RecipeFile.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/importRecipes",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Замена рецептов.",
            description = "Принимает json-файл с рецептами и заменяет сохраненный на жестком (локальном) диске файл на новый."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл заменён."
                    )
            }
    )
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        File recipeFile = filesService.getRecipeFile();
        try (FileOutputStream fos = new FileOutputStream(recipeFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping(value = "/importIngredients",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Замена ингредиентов.",
            description = "Принимает json-файл с ингредиентами и заменяет сохраненный на жестком (локальном) диске файл на новый."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл заменён."
                    )
            }
    )
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        File ingredientFile = filesService.getIngredientFile();
        try (FileOutputStream fos = new FileOutputStream(ingredientFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}