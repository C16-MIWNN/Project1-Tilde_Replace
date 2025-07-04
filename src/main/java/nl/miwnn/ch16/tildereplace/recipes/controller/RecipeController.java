package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipeDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.IngredientRepository;

import nl.miwnn.ch16.tildereplace.recipes.repository.UnitRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final FoodRepository foodRepository;
    private final UnitRepository unitRepository;
    private final RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
                            FoodRepository foodRepository, UnitRepository unitRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.foodRepository = foodRepository;
        this.unitRepository = unitRepository;
        this.recipeService = recipeService;
    }

    private String setupRecipeDetail(Model datamodel, Recipe recipeToShow) {
        datamodel.addAttribute("recipe", recipeToShow);
        List<Ingredient> allIngredients = recipeToShow.getIngredients();
        datamodel.addAttribute("myIngredients", allIngredients);

        return "recipeDetails";
    }

    @GetMapping({"/", "/recipeOverview"})
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }

    @GetMapping("/recipe/myRecipes/{username}")
    private String showUserRecipes(@PathVariable("username") String username ,Model datamodel) {
        Optional<List<Recipe>> optionalRecipes = recipeRepository.findRecipeByAuthorUsername(username);
        if (!optionalRecipes.isPresent()) {
            return "recipeOverview";
        }

        datamodel.addAttribute("isMyRecipes", true);
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

    @GetMapping("/recipe/new")
    private String newRecipe(Model datamodel, Principal principal) {
        NewRecipeDTO newRecipeDTO = new NewRecipeDTO();
        newRecipeDTO.setAuthorUsername(principal.getName());
        datamodel.addAttribute("recipeForm", newRecipeDTO);
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("allFoods", foodRepository.findAll());
        datamodel.addAttribute("allUnits",unitRepository.findAll());

        return "recipeForm";
    }

    @GetMapping("/recipe/edit/{recipeId}")
    private String editRecipe(@PathVariable("recipeId") Long recipeId, Model dataModel) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            dataModel.addAttribute("recipeForm", recipeService.recipeEdit(recipeOptional.get()));
            dataModel.addAttribute("allIngredients", ingredientRepository.findAll());
            dataModel.addAttribute("allFoods", foodRepository.findAll());
            dataModel.addAttribute("allUnits",unitRepository.findAll());
            dataModel.addAttribute("isEditRecipe", true);
        }

        return "recipeForm";
    }

    @PostMapping("/recipe/save")
    private String saveOrUpdateRecipe(@ModelAttribute("recipeForm") NewRecipeDTO toBeSavedRecipe,
                                      BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
        } else {
            recipeService.save(toBeSavedRecipe);
        }

        return "redirect:/recipe/detail/" + toBeSavedRecipe.getRecipeName();
    }

    @GetMapping("/recipe/delete/{recipeId}")
    private String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            recipeRepository.deleteById(recipeId);
        }

        return "redirect:/";
    }

    @PostMapping("/recipe/search")
    private String searchRecipe(@ModelAttribute("searchString") String searchString, Model dataModel) {
        Optional<List<Recipe>> recipeOptional = recipeRepository.findRecipeByRecipeNameContaining(searchString);
        if (recipeOptional.isPresent()) {
            dataModel.addAttribute("allRecipes", recipeOptional.get());
            return "recipeOverview";
        }

        return "recipeOverview";
    }

}
