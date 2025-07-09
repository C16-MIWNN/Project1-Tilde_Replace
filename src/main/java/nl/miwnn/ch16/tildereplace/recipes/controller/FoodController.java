package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.model.Allergy;
import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.dto.FoodDTO;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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

    private String setupFoodOverview(Model datamodel, Allergy allergyForm, boolean formFoodModalHidden, boolean formAllergyModalHidden) {
        datamodel.addAttribute("allFoods", foodRepository.findAll(Sort.by(Sort.Direction.ASC, "foodName")));
        datamodel.addAttribute("allAllergies", allergyRepository.findAll());
        datamodel.addAttribute("foodForm", new FoodDTO());
        datamodel.addAttribute("allergyForm", allergyForm);
        datamodel.addAttribute("formFoodModalHidden", formFoodModalHidden);
        datamodel.addAttribute("formAllergyModalHidden", formAllergyModalHidden);

        return "foodOverview";
    }

    @GetMapping({"/overview"})
    public String showFoodOverview(Model datamodel) {
        return setupFoodOverview(datamodel, new Allergy(), true, true);
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

    @PostMapping("/allergy/save")
    private String saveOrUpdateFood(@ModelAttribute("allergyForm") Allergy allergyToBeSaved,
                                          BindingResult result,
                                          Model datamodel) {
        checkAllergyNameInUse(allergyToBeSaved, result);

        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
            return setupFoodOverview(datamodel, allergyToBeSaved, true, false);
        } else {
            allergyRepository.save(allergyToBeSaved);
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


    private void checkAllergyNameInUse(Allergy allergyToBeSaved, BindingResult result) {

        Optional<Allergy> optionalAllergy = allergyRepository.findAllergyByAllergyName(allergyToBeSaved.getAllergyName());
        if (optionalAllergy.isPresent() && !optionalAllergy.get().getAllergyName().equals(allergyToBeSaved.getAllergyName())) {
            result.addError(new FieldError("allergyForm", "allergyName", "Allergie naam bestaat al"));
        }
    }

}
