package nl.miwnn.ch16.tildereplace.recipes.model;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long recipeId;

    private String recipeName;

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    private String preparationInstructions;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
