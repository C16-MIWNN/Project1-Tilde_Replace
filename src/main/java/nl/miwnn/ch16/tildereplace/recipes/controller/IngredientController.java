package nl.miwnn.ch16.tildereplace.recipes.controller;


import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final FoodRepository foodRepository;

    public IngredientController(IngredientRepository ingredientRepository, FoodRepository foodRepository) {
        this.ingredientRepository = ingredientRepository;
        this.foodRepository = foodRepository;
    }

    @GetMapping("/ingredientOverview")
    public String ingredientOverview(Model dataModel) {
        dataModel.addAttribute("allIngredients", foodRepository.findAll());
        return "ingredientOverview";
    }

}
