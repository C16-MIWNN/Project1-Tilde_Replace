package nl.miwnn.ch16.tildereplace.recipes.service.mapper;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipeDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.*;
import nl.miwnn.ch16.tildereplace.recipes.repository.*;
import nl.miwnn.ch16.tildereplace.recipes.service.ImageService;

import java.util.Optional;


public class NewRecipeMapper {

    private final FoodRepository foodRepository;
    private final UnitRepository unitRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final RecipesUserRepository recipesUserRepository;
    private final TagRepository tagRepository;

    public NewRecipeMapper(FoodRepository foodRepository,
                           UnitRepository unitRepository,
                           IngredientRepository ingredientRepository,
                           RecipeRepository recipeRepository,
                           RecipesUserRepository recipesUserRepository,
                           TagRepository tagRepository) {

        this.foodRepository = foodRepository;
        this.unitRepository = unitRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipesUserRepository = recipesUserRepository;
        this.tagRepository = tagRepository;
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

        setRecipeTags(newRecipeDTO, recipe);

        return recipe;
    }

    private void setRecipeTags(NewRecipeDTO newRecipeDTO, Recipe recipe) {
        for (Long tagId : newRecipeDTO.getTagIds()) {
            tagRepository.findById(tagId)
                    .ifPresent(recipe.getTags()::add);
        }
    }

    private Recipe setRecipeDetails(NewRecipeDTO newRecipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(newRecipeDTO.getRecipeName());
        recipe.setPreperationInstructions(newRecipeDTO.getPreparationInstruction());

        Optional<RecipesUser> authorOptional = recipesUserRepository.findByUsername(newRecipeDTO.getAuthorUsername());
        if (authorOptional.isPresent()) {
            recipe.setAuthor(authorOptional.get());
        }

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

    public NewRecipeDTO toDto(Recipe recipe) {
        NewRecipeDTO recipeDTO = new NewRecipeDTO();

        recipeDTO.setRecipeName(recipe.getRecipeName());
        recipeDTO.setPreparationInstruction(recipe.getPreperationInstructions());

        for (int index = 0; index < recipe.getIngredients().size(); index++) {
            Ingredient currentIngredient = recipe.getIngredients().get(index);
            setIngredientIds(recipeDTO, currentIngredient);
        }

        return recipeDTO;
    }

    private static void setIngredientIds(NewRecipeDTO recipeDTO, Ingredient currentIngredient) {
        recipeDTO.getFoodIds().add(currentIngredient.getFood().getFoodId());
        recipeDTO.getUnitIds().add(currentIngredient.getUnit().getUnitId());
        recipeDTO.getIngredientQuantities().add(currentIngredient.getAmount());
    }

}
