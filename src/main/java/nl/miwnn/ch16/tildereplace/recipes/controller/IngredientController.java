package nl.miwnn.ch16.tildereplace.recipes.controller;


import nl.miwnn.ch16.tildereplace.recipes.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public String ingredientOverview(Model dataModel) {
        dataModel.addAttribute("allIngredients", ingredientRepository.findAll());
        return "ingredientOverview";
    }
}
