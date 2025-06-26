package nl.miwnn.ch16.tildereplace.recipes.controller;

import jakarta.persistence.ManyToOne;
import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;

    public RecipeController(RecipeRepository recipeRepository, FoodRepository foodRepository) {
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;
    }

    private String setupRecipeDetail(Model datamodel, Recipe recipeToShow) {
        datamodel.addAttribute("recipe", recipeToShow);
        List<Ingredient> allIngredients = recipeToShow.getIngredients();
        PrintList();
        datamodel.addAttribute("myIngredients", allIngredients);

        return "recipeDetails";
    }

    public void PrintList() {
        System.out.println("TEST");
        //System.err.println(ing);
    }

    @GetMapping({"/", "/recipeOverview"})
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }

    @GetMapping("/recipe/detail/{recipeName}")
    private String showRecipeDetail(@PathVariable("recipeName") String name, Model datamodel) {

        Optional<Recipe> recipeOptional = recipeRepository.findRecipeByRecipeName(name);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipeOverview";
        }
        return setupRecipeDetail(datamodel, recipeOptional.get());
    }

    @GetMapping("/new")
    private String newRecipe(Model dataModel) {
        dataModel.addAttribute("recipeForm", new Recipe());
        dataModel.addAttribute("allFoods", foodRepository.findAll());

        return "recipeForm";
    }

    @GetMapping("/edit/{recipeId}")
    private String editRecipe(@PathVariable("recipeId") Long recipeId, Model dataModel) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            dataModel.addAttribute("recipeForm", recipeOptional.get());
        }

        return "recipeForm";
    }

    @PostMapping("/save")
    private String saveOrUpdateRecipe(@ModelAttribute("recipeForm") Recipe toBeSavedRecipe,
                                      BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
        } else {
            recipeRepository.save(toBeSavedRecipe);
        }

        return "redirect:/";
    }

}
