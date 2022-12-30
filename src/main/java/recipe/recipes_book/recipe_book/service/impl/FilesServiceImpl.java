package recipe.recipes_book.recipe_book.service.impl;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import recipe.recipes_book.recipe_book.service.FilesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.file}")
    private String pathFile;
    @Value("${name.of.file.recipe}")
    private String nameRecipeFile;
    @Value("${name.of.file.ingredient}")
    private String nameIngredientFile;

    @Override
    public void saveFileRecipe(String json) {
        try {
            Files.writeString(Path.of(pathFile,nameRecipeFile),json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveFileIngredient(String json) {
        try {
            Files.writeString(Path.of(pathFile,nameIngredientFile),json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String readFileRecipe() {
        try {
            return Files.readString(Path.of(pathFile,nameRecipeFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String readFileIngredient() {
        try {
            return Files.readString(Path.of(pathFile,nameIngredientFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public File getRecipeFile() {
        return new File(pathFile + "/" + nameRecipeFile);
    }
    @Override
    public File getIngredientFile() {
        return new File(pathFile + "/" + nameIngredientFile);
    }
    @Override
    public ResponseEntity<InputStreamResource> downloadRecipe() throws FileNotFoundException {
        File file = getRecipeFile();
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
    @Override
    public ResponseEntity<Void> uploadRecipe(MultipartFile file) {
        File recipeFile = getRecipeFile();
        try (FileOutputStream fos = new FileOutputStream(recipeFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @Override
    public ResponseEntity<Void> uploadIngredient(MultipartFile file) {
        File ingredientFile = getIngredientFile();
        try (FileOutputStream fos = new FileOutputStream(ingredientFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}