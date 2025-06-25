package nl.miwnn.ch16.tildereplace.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;

@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping({"/foodOverview"})
    private String showFoodOverview(Model datamodel) {
        datamodel.addAttribute("allFoods", foodRepository.findAll());
        return "foodOverview";
    }
}
