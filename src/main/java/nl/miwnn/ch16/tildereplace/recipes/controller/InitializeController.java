package nl.miwnn.ch16.tildereplace.recipes.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String, Food> foodCache = new HashMap<String, Food>();

    public InitializeController(FoodRepository foodRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (ingredientRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        try {
            loadFoods();
            loadRecipes();
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to initialize database from CSV files", e);
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
                System.out.println("=======");
                System.out.println(recipeLine[0]);
                System.out.println("========");

                Recipe recipe = new Recipe();
                recipe.setRecipeName(recipeLine[0]);
                recipe.setPreperationInstructions(recipeLine[2]);
                recipeRepository.save(recipe);

                loadIngredients(recipeLine[1], recipe);
            }
        }
    }


    private void loadIngredients(String ingredientLine, Recipe recipe) throws IOException, CsvValidationException {
        String[] ingredients = ingredientLine.split(",");

        for (int index = 0; index + 1 < ingredients.length; index += 2) {
                Ingredient ingredient = new Ingredient();

                ingredient.setRecipe(recipe);

                ingredient.setFood(foodCache.get(ingredients[index]));
                ingredient.setGrams(Integer.parseInt(ingredients[index+1]));

                ingredientRepository.save(ingredient);
        }

    }




}
