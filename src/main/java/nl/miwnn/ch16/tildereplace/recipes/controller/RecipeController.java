package nl.miwnn.ch16.tildereplace.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
