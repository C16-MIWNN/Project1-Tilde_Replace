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
        Recipe recipe = new Recipe();


        recipe.setRecipeName(newRecipeDTO.getRecipeName());
        recipe.setPreperationInstructions(newRecipeDTO.getPreparationInstruction());

        recipeRepository.save(recipe);

        int numberOfIngredients = newRecipeDTO.getFoodIds().size();
        for (int index = 0; index < numberOfIngredients; index++) {
            Ingredient ingredient = new Ingredient();
            Optional<Food> foodOptional = foodRepository.findById(newRecipeDTO.getFoodIds().get(index));
            Optional<Unit> unitOptional = unitRepository.findById(newRecipeDTO.getUnitIds().get(index));
            int quantity = newRecipeDTO.getIngredientQuantities().get(index);

            if (foodOptional.isPresent()) {
                ingredient.setFood(foodOptional.get());
            }

            if (unitOptional.isPresent()) {
                ingredient.setUnit(unitOptional.get());
            }

            ingredient.setAmount(quantity);
            ingredient.setRecipe(recipe);
            System.err.println(ingredient.getFood().getFoodName());
            System.err.println(ingredient.getUnit().getUnitName());
            ingredientRepository.save(ingredient);
        }

        return recipe;
    }

}
