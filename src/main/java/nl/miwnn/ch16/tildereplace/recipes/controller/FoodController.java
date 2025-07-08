package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.model.Allergy;
import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.dto.FoodDTO;
import nl.miwnn.ch16.tildereplace.recipes.dto.AllergyProxyObject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.AllergyRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.FoodService;

import java.util.HashSet;
import java.util.Optional;

@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodRepository foodRepository;
    private final AllergyRepository allergyRepository;
    private final FoodService foodService;

    public FoodController(FoodRepository foodRepository, AllergyRepository allergyRepository, FoodService foodService) {
        this.foodRepository = foodRepository;
        this.allergyRepository = allergyRepository;
        this.foodService = foodService;
    }

    @GetMapping({"/overview"})
    private String showFoodOverview(Model datamodel) {
        datamodel.addAttribute("allFoods", foodRepository.findAll(Sort.by(Sort.Direction.ASC, "foodName")));
        datamodel.addAttribute("allAllergies", allergyRepository.findAll());
        datamodel.addAttribute("foodForm",  new FoodDTO());

        return "foodOverview";
    }


    @GetMapping("/edit/{foodId}")
    private String editFood(@PathVariable("foodId") Long foodId, Model datamodel) {
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (foodOptional.isPresent()) {
            datamodel.addAttribute("foodForm", foodService.toDTO(foodOptional.get()));
        }
        datamodel.addAttribute("allAllergies", allergyRepository.findAll());

        return "foodForm";
    }

    @PostMapping("/save")
    private String saveOrUpdateFood(@ModelAttribute("foodForm") FoodDTO toBeSavedFood,
                                          BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
        } else {
            foodService.save(toBeSavedFood);
        }

        return "redirect:/food/overview";
    }

    @GetMapping("/delete/{foodId}")
    private String deleteFood(@PathVariable("foodId") Long foodId) {
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (foodOptional.isPresent()) {
            foodRepository.deleteById(foodId);
        }

        return "redirect:/food/overview";
    }

    @GetMapping("/detail/{foodName}")
    private String showFoodDetail(@PathVariable("foodName") String name, Model datamodel) {

        Optional<Food> foodOptional = foodRepository.findFoodByFoodName(name);

        if (foodOptional.isEmpty()) {
            return "redirect:/food/overview";
        }

        datamodel.addAttribute("food", foodOptional.get());

        return "foodDetails";
    }

}
