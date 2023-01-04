package recipe.recipes_book.recipe_book.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipe.recipes_book.recipe_book.model.Ingredient;
import recipe.recipes_book.recipe_book.service.FilesService;
import recipe.recipes_book.recipe_book.service.IngredientService;
import recipe.recipes_book.recipe_book.service.files.IngredientFile;

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
        checkIngredient(ingredient);
        ingredientMap.put(id++,ingredient);
        saveToFileIngredient();
    }
    @Override
    public Ingredient getIngredient(Long id) {
        checkId(id);
        return ingredientMap.get(id);
    }
    @Override
    public void deleteIngredient(Long id) {
        checkId(id);
        ingredientMap.remove(id);
    }
    @Override
    public void editIngredient(Long id, Ingredient ingredient) {
        checkId(id);
        checkIngredient(ingredient);
        ingredientMap.put(id,ingredient);
        saveToFileIngredient();
    }
    private void checkId(Long id) {
        if (!ingredientMap.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id не найден!");
        }
    }
    private void checkIngredient(Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Название ингредиента должно быть заполнено!");
        }
        if (ingredient.getWeight() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Укажите вес/количество корректно!");
        }
        if (StringUtils.isBlank(ingredient.getMeasureUnit())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Единица измерения должна быть указана!");
        }
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
}