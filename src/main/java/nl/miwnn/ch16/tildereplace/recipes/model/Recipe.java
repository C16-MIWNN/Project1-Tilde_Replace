package nl.miwnn.ch16.tildereplace.recipes.model;

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

    @Column(columnDefinition = "TEXT")
    private String preparationInstructions;

    @ManyToOne
    private RecipesUser author;

    private String imageUrl;

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

    public String getPreparationInstructions() {
        return preparationInstructions;
    }

    public void setPreparationInstructions(String preparationInstructions) {
        this.preparationInstructions = preparationInstructions;
    }

    public RecipesUser getAuthor() {
        return author;
    }

    public void setAuthor(RecipesUser author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
