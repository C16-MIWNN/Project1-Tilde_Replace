package nl.miwnn.ch16.tildereplace.recipes.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private AllergyRepository allergyRepository;


    private Map<String, Food> foodCache = new HashMap<String, Food>();
    private Map<String, Unit> unitCache = new HashMap<String, Unit>();
    private Map<String, Allergy> allergyCache = new HashMap<String, Allergy>();

    public InitializeController(FoodRepository foodRepository,
                                IngredientRepository ingredientRepository,
                                RecipeRepository recipeRepository,
                                RecipesUserService recipesUserService,
                                UnitRepository unitRepository,
                                AllergyRepository allergyRepository
                                ) {
        this.foodRepository = foodRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipesUserService = recipesUserService;
        this.unitRepository = unitRepository;
        this.allergyRepository = allergyRepository;
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

            loadAllergies();
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

    private void loadAllergies() throws IOException, CsvValidationException {

        try (CSVReader reader = new CSVReader(new InputStreamReader(
                        new ClassPathResource("/example_data/allergies.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] allergyLine : reader) {
                Allergy allergy = new Allergy();
                allergy.setAllergyName(allergyLine[0]);

                allergyRepository.save(allergy);
                allergyCache.put(allergy.getAllergyName(), allergy);
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
                food.setEnergy(Double.parseDouble(foodLine[2]));
                food.setProtein(Double.parseDouble(foodLine[3]));
                food.setFat(Double.parseDouble(foodLine[4]));
                food.setCarbohydrates(Double.parseDouble(foodLine[5]));
                food.setFiber(Double.parseDouble(foodLine[6]));
                food.setSalt(Double.parseDouble(foodLine[7]));

                Set<Allergy> allergies = getAllergyList(foodLine[8]);
                food.setAllergies(allergies);

                foodRepository.save(food);
                foodCache.put(food.getFoodName(), food);
            }
        }
    }

    private Set<Allergy> getAllergyList(String allergyLine) {
        Set<Allergy> allergies = new HashSet<Allergy>();
        for (String allergyString : allergyLine.split(",")) {
            System.out.println("allergy " + allergyString);
            Allergy allergy = allergyCache.get(allergyString);
            System.out.println(allergyString);
            allergies.add(allergy);
        }

        return allergies;
    }

    private void loadRecipes() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/recipe.csv").getInputStream()))) {

            // Skip header
            reader.skip(1);

            for (String[] recipeLine : reader) {
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

        for (int index = 0; index + 2 < ingredients.length; index += 3) {
                Ingredient ingredient = new Ingredient();

                ingredient.setRecipe(recipe);

                ingredient.setFood(foodCache.get(ingredients[index]));
                ingredient.setAmount(Integer.parseInt(ingredients[index+1]));
                ingredient.setUnit(unitCache.get(ingredients[index+2]));

                ingredientRepository.save(ingredient);
        }

    }
}
