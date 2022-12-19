package recipe.recipes_book.recipe_book.model;

import java.util.List;
import java.util.Objects;

public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredientList;
    private List<String> cookingSteps;

    public Recipe(String name, int cookingTime, List<Ingredient> ingredientList, List<String> cookingSteps) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredientList = ingredientList;
        this.cookingSteps = cookingSteps;
    }

    public String getName() {
        return name;
    }
    public int getCookingTime() {
        return cookingTime;
    }
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
    public List<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public void setCookingSteps(List<String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return cookingTime == recipe.cookingTime && Objects.equals(name, recipe.name) && Objects.equals(ingredientList, recipe.ingredientList) && Objects.equals(cookingSteps, recipe.cookingSteps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cookingTime, ingredientList, cookingSteps);
    }

    @Override
    public String toString() {
        return  "Рецепт:{" +
                "Название- " + name +
                ", Время приготовления- " + cookingTime + " минут" +
                ", Ингредиенты- " + ingredientList +
                ", Шаги приготовления- " + cookingSteps +
                "}";
    }
}
