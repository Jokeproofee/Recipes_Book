package recipe.recipes_book.recipe_book.model;

import java.util.Objects;

public class Ingredient {

    private String name;
    private int weight;
    private String measureUnit;

    public Ingredient(String name, int numberOfIngredients, String measureUnit) {
        this.name = name;
        this.weight = numberOfIngredients;
        this.measureUnit = measureUnit;
    }

    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return weight == that.weight && Objects.equals(name, that.name) && Objects.equals(measureUnit, that.measureUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, measureUnit);
    }

    @Override
    public String toString() {
        return "Ингредиент:{" +
                "Название- " + name +
                ", Количество- " + weight + " " + measureUnit +
                "}";
    }
}
