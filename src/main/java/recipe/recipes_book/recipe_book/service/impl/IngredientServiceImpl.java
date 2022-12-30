package recipe.recipes_book.recipe_book.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.FilesService;
import recipe.recipes_book.recipe_book.service.IngredientService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final FilesService filesService;
    public TreeMap<Long, Ingredient> ingredientMap = new TreeMap<>();

    private static Long id = 1L;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }
    @PostConstruct
    private void init() {
        try {
            readToFileIngredient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++,ingredient);
        saveToFileIngredient();
    }
    @Override
    public Ingredient getIngredient(Long id) {
        if (!ingredientMap.containsKey(id)){
            throw new RuntimeException("id не найден!");
        }
        return ingredientMap.get(id);
    }
    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        ingredientMap.remove(id);
    }
    @Override
    public void editIngredient(Long id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("id не найден!");
        }
        ingredientMap.put(id,ingredient);
        saveToFileIngredient();
    }
    @Override
    public List<Ingredient> getAllIngredient() {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (Map.Entry<Long, Ingredient> ingredientEntry : ingredientMap.entrySet()) {
            ingredientList.add(ingredientEntry.getValue());
        }
        return ingredientList;
    }
    private void saveToFileIngredient() {
        try {
            IngredientFile ingredientFile = new IngredientFile(id,ingredientMap);
            String json = new ObjectMapper().writeValueAsString(ingredientFile);
            filesService.saveFileIngredient(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void readToFileIngredient() {
        try {
            String json = filesService.readFileIngredient();
            IngredientFile ingredientFile = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
            id = ingredientFile.getId();
            ingredientMap = ingredientFile.getIngredientFileMap();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class IngredientFile {
        private long id;
        private TreeMap<Long, Ingredient> ingredientFileMap;
    }
}