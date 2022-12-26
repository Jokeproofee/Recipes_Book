package recipe.recipes_book.recipe_book.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.model.Recipe;
import recipe.recipes_book.recipe_book.service.FilesService;
import recipe.recipes_book.recipe_book.service.RecipeService;

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
        readToFileRecipe();
    }
    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        saveToFileRecipe();
    }
    @Override
    public Recipe getRecipe(Long id) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        return recipeMap.get(id);
    }
    @Override
    public void deleteRecipe(Long id) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        recipeMap.remove(id);
    }
    @Override
    public void editRecipe(Long id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        recipeMap.put(id, recipe);
        saveToFileRecipe();
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
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveFileRecipe(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void readToFileRecipe() {
        try {
            String json = filesService.readFileRecipe();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}