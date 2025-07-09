package nl.miwnn.ch16.tildereplace.recipes.dto;


import nl.miwnn.ch16.tildereplace.recipes.model.RecipesUser;

import java.util.ArrayList;

public class NewRecipeDTO {

    private String recipeName;
    private String preparationInstruction;
    private String authorUsername;
    private String imageUrl;

    private ArrayList<Long> foodIds;
    private ArrayList<Integer> ingredientQuantities;
    private ArrayList<Long> unitIds;
    private ArrayList<Long> tagIds;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getPreparationInstruction() {
        return preparationInstruction;
    }

    public void setPreparationInstruction(String preparationInstruction) {
        this.preparationInstruction = preparationInstruction;
    }

    public ArrayList<Long> getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(ArrayList<Long> foodIds) {
        this.foodIds = foodIds;
    }

    public ArrayList<Integer> getIngredientQuantities() {
        return ingredientQuantities;
    }

    public void setIngredientQuantities(ArrayList<Integer> ingredientQuantities) {
        this.ingredientQuantities = ingredientQuantities;
    }

    public ArrayList<Long> getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(ArrayList<Long> unitIds) {
        this.unitIds = unitIds;
    }

    public ArrayList<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(ArrayList<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
