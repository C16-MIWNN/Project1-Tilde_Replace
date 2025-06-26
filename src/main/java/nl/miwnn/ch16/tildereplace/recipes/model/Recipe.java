package nl.miwnn.ch16.tildereplace.recipes.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long recipeId;

    private String recipeName;

    @OneToMany (mappedBy = "recipe")
    private List<Ingredient> ingredients;
    private String preparationInstructions;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreperationInstructions() {
        return preparationInstructions;
    }

    public void setPreperationInstructions(String preperationInstructions) {
        this.preparationInstructions = preperationInstructions;
    }

}
