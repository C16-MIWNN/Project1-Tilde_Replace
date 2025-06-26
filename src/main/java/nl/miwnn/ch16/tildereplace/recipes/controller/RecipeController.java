package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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


}
