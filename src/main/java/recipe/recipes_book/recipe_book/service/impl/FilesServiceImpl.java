package recipe.recipes_book.recipe_book.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.service.FilesService;

import java.io.IOException;
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
}