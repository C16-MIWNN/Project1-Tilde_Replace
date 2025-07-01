package nl.miwnn.ch16.tildereplace.recipes.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import nl.miwnn.ch16.tildereplace.recipes.service.RecipesUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import nl.miwnn.ch16.tildereplace.recipes.model.*;
import nl.miwnn.ch16.tildereplace.recipes.repository.*;

@Controller
public class InitializeController {

    private FoodRepository foodRepository;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private RecipesUserService recipesUserService;
    private UnitRepository unitRepository;

    private Map<String, Food> foodCache = new HashMap<String, Food>();
    private Map<String, Unit> unitCache = new HashMap<String, Unit>();

    public InitializeController(FoodRepository foodRepository,
                                IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository,
                                RecipesUserService recipesUserService,
                                UnitRepository unitRepository
                                ) {
        this.foodRepository = foodRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipesUserService = recipesUserService;
        this.unitRepository = unitRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (ingredientRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        try {
            RecipesUser user = new RecipesUser();
            user.setUsername("user");
            user.setPassword("userPW");
            recipesUserService.saveUser(user);

            loadUnits();
            loadFoods();
            loadRecipes();
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to initialize database from CSV files", e);
        }
    }

    private void loadUnits() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/units.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] unitLine : reader) {
                Unit unit = new Unit();
                unit.setUnitName(unitLine[0]);
                unit.setAbbreviation(unitLine[1]);
                unitRepository.save(unit);
                unitCache.put(unit.getUnitName(), unit);
            }
        }
    }


    private void loadFoods() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/foods.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] foodLine : reader) {
                Food food = new Food();
                food.setFoodName(foodLine[1]);
                foodRepository.save(food);
                foodCache.put(food.getFoodName(), food);
            }
        }
    }


    private void loadRecipes() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/recipe.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] recipeLine : reader) {
                Recipe recipe = setRecipeDetails(recipeLine);
                recipeRepository.save(recipe);

                loadIngredients(recipeLine[1], recipe);
            }
        }
    }

    private static Recipe setRecipeDetails(String[] recipeLine) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeLine[0]);
        recipe.setPreperationInstructions(recipeLine[2]);
        return recipe;
    }


    private void loadIngredients(String ingredientLine, Recipe recipe) throws IOException, CsvValidationException {
        String[] ingredients = ingredientLine.split(",");

        for (int index = 0; index + 2 < ingredients.length; index += 3) {
            Ingredient ingredient = setIngredientDetails(recipe, ingredients, index);
            ingredientRepository.save(ingredient);
        }
    }

    private Ingredient setIngredientDetails(Recipe recipe, String[] ingredients, int index) {
        Ingredient ingredient = new Ingredient();

        ingredient.setRecipe(recipe);

        ingredient.setFood(foodCache.get(ingredients[index]));
        ingredient.setAmount(Integer.parseInt(ingredients[index +1]));
        ingredient.setUnit(unitCache.get(ingredients[index +2]));
        return ingredient;
    }


}
