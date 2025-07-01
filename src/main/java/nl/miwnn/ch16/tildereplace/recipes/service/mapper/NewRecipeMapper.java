package nl.miwnn.ch16.tildereplace.recipes.service.mapper;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipeDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;
import nl.miwnn.ch16.tildereplace.recipes.model.Unit;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.IngredientRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.UnitRepository;

import java.util.Optional;


public class NewRecipeMapper {

    private final FoodRepository foodRepository;
    private final UnitRepository unitRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public NewRecipeMapper(FoodRepository foodRepository,
                           UnitRepository unitRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.unitRepository = unitRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    public Recipe fromDto(NewRecipeDTO newRecipeDTO) {
        Recipe recipe = setRecipeDetails(newRecipeDTO);
        recipeRepository.save(recipe);

        int numberOfIngredients = newRecipeDTO.getFoodIds().size();
        for (int index = 0; index < numberOfIngredients; index++) {
            Ingredient ingredient = new Ingredient();
            setIngredientDetails(newRecipeDTO, index, ingredient, recipe);
            ingredientRepository.save(ingredient);
        }

        return recipe;
    }

    private Recipe setRecipeDetails(NewRecipeDTO newRecipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(newRecipeDTO.getRecipeName());
        recipe.setPreperationInstructions(newRecipeDTO.getPreparationInstruction());

        return recipe;
    }

    private void setIngredientDetails(NewRecipeDTO newRecipeDTO, int index, Ingredient ingredient, Recipe recipe) {
        ingredient.setRecipe(recipe);
        setFood(newRecipeDTO, index, ingredient);
        ingredient.setAmount(newRecipeDTO.getIngredientQuantities().get(index));
        setUnit(newRecipeDTO, index, ingredient);
    }

    private void setUnit(NewRecipeDTO newRecipeDTO, int index, Ingredient ingredient) {
        Optional<Unit> unitOptional = unitRepository.findById(newRecipeDTO.getUnitIds().get(index));
        if (unitOptional.isPresent()) {
            ingredient.setUnit(unitOptional.get());
        }
    }

    private void setFood(NewRecipeDTO newRecipeDTO, int index, Ingredient ingredient) {
        Optional<Food> foodOptional = foodRepository.findById(newRecipeDTO.getFoodIds().get(index));
        if (foodOptional.isPresent()) {
            ingredient.setFood(foodOptional.get());
        }
    }

}
