package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;

import java.util.Optional;

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

    @GetMapping("/new")
    private String newIngredient(Model dataModel) {
        dataModel.addAttribute("foodForm", new Food());

        return "foodForm";
    }

    @GetMapping("/edit/{foodId}")
    private String editFood(@PathVariable("foodId") Long foodId, Model dataModel) {
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (foodOptional.isPresent()) {
            dataModel.addAttribute("foodForm", foodOptional.get());
        }

        return "foodForm";
    }

    @PostMapping("/save")
    private String saveOrUpdateFood(@ModelAttribute("foodForm") Food toBeSavedFood,
                                          BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
        } else {
            foodRepository.save(toBeSavedFood);
        }

        return "redirect:/food/foodOverview";
    }

    @GetMapping("/delete/{foodId}")
    private String deleteFood(@PathVariable("foodId") Long foodId) {
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (foodOptional.isPresent()) {
            foodRepository.deleteById(foodId);
        }

        return "redirect:/food/foodOverview";
    }


}
