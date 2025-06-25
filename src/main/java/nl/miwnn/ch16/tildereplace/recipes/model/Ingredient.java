package nl.miwnn.ch16.tildereplace.recipes.model;


import jakarta.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long ingredientId;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Food food;

    private int grams;

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }
}
