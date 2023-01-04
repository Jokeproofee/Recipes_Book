package recipe.recipes_book.recipe_book.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.FilesService;
import recipe.recipes_book.recipe_book.service.RecipeService;
import recipe.recipes_book.recipe_book.service.files.RecipeFile;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final FilesService filesService;
    private TreeMap<Long, Recipe> recipeMap = new TreeMap<>();

    private static Long id = 1L;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }
    @PostConstruct
    private void init() {
        try {
            readToFileRecipe();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void addRecipe(Recipe recipe) {
        checkRecipe(recipe);
        recipeMap.put(id++, recipe);
        saveToFileRecipe();
    }
    @Override
    public Recipe getRecipe(Long id) {
        checkId(id);
        return recipeMap.get(id);
    }
    @Override
    public void deleteRecipe(Long id) {
        checkId(id);
        recipeMap.remove(id);
    }
    @Override
    public void editRecipe(Long id, Recipe recipe) {
        checkId(id);
        checkRecipe(recipe);
        recipeMap.put(id, recipe);
        saveToFileRecipe();
    }
    private void checkId(Long id) {
        if (!recipeMap.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id не найден!");
        }
    }
    private void checkRecipe(Recipe recipe) {
        if (StringUtils.isBlank(recipe.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Название рецепта должно быть заполнено!");
        }
        if (recipe.getCookingTime() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Укажите время приготовления корректно!");
        }
    }
    @Override
    public List<Recipe> getAllRecipe() {
        List<Recipe> recipeList = new ArrayList<>();
        for (Map.Entry<Long, Recipe> recipeEntry : recipeMap.entrySet()) {
            recipeList.add(recipeEntry.getValue());
        }
        return recipeList;
    }
    private void saveToFileRecipe() {
        try {
            RecipeFile recipeFile = new RecipeFile(id,recipeMap);
            String json = new ObjectMapper().writeValueAsString(recipeFile);
            filesService.saveFileRecipe(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void readToFileRecipe() {
        try {
            String json = filesService.readFileRecipe();
            RecipeFile recipeFile = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
            id = recipeFile.getId();
            recipeMap = recipeFile.getRecipeFileMap();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}