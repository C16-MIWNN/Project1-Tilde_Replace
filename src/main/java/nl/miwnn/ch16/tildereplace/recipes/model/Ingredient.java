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

    @ManyToOne
    private Unit unit;

    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount can't be smaller than 0");
        } else {
            this.amount = amount;
        }
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
