package recipe.recipes_book.recipe_book.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

public interface FilesService {
    void saveFileRecipe(String json);

    void saveFileIngredient(String json);

    String readFileRecipe();

    String readFileIngredient();

    File getRecipeFile();

    File getIngredientFile();

    ResponseEntity<InputStreamResource> downloadRecipe() throws FileNotFoundException;

    ResponseEntity<Void> uploadRecipe(MultipartFile file);

    ResponseEntity<Void> uploadIngredient(MultipartFile file);
}