package nl.miwnn.ch16.tildereplace.recipes.dto;


import java.util.ArrayList;
import java.util.List;

public class NewRecipeDTO {
    private static final int DEFAULT_NUMBER_RECIPE_FIELDS = 3;

    private Long id;
    private String recipeName;
    private String preparationInstruction;

    private ArrayList<Long> foodIds = new ArrayList<>();
    private ArrayList<Integer>  ingredientQuantities = new ArrayList<>();
    private ArrayList<Long> unitIds = new ArrayList<>();

    public NewRecipeDTO() {
       InitializeDefaultRecipe();
    }

    public void InitializeDefaultRecipe(){

        for (int index = 0; index < DEFAULT_NUMBER_RECIPE_FIELDS; index++) {
            foodIds.add(index,getId());
            ingredientQuantities.add(index,0);
            unitIds.add(index,getId());
        }

    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
