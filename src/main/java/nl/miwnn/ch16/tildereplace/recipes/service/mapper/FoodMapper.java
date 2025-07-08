package nl.miwnn.ch16.tildereplace.recipes.service.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import nl.miwnn.ch16.tildereplace.recipes.dto.FoodDTO;
import nl.miwnn.ch16.tildereplace.recipes.dto.AllergyProxyObject;
import nl.miwnn.ch16.tildereplace.recipes.model.Allergy;
import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import nl.miwnn.ch16.tildereplace.recipes.repository.AllergyRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;

public class FoodMapper {

    private final AllergyRepository allergyRepository;
    private final FoodRepository foodRepository;

    public FoodMapper(FoodRepository foodRepository, AllergyRepository allergyRepository) {
         this.foodRepository    = foodRepository;
         this.allergyRepository = allergyRepository;
    }

    public Food fromDTO(FoodDTO foodDTO) {
        Food food = new Food();
        food.setFoodId(foodDTO.getFoodId());
        food.setFoodName(foodDTO.getFoodName());

        food.setEnergy(foodDTO.getEnergy());
        food.setFat(foodDTO.getFat());
        food.setSalt(foodDTO.getSalt());
        food.setFiber(foodDTO.getFiber());
        food.setProtein(foodDTO.getProtein());
        food.setCarbohydrates(foodDTO.getCarbohydrates());
        food.setImageUrl(foodDTO.getImageUrl());

        Set<String> allergyNameSet = foodDTO.getAllergies();
        Set<Allergy> allergies = new HashSet<Allergy>();


        for (String allergyName : allergyNameSet) {
            Optional<Allergy> allergyOptional = allergyRepository.findAllergyByAllergyName(allergyName);

            if ( allergyOptional.isPresent() ) {
                allergies.add(allergyOptional.get());
            } else {
                System.err.println(allergyName + " not in allergy repository");
            }
        }

        food.setAllergies(allergies);

        return food;
    }


    public FoodDTO toDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodId(food.getFoodId());
        foodDTO.setFoodName(food.getFoodName());

        foodDTO.setEnergy(food.getEnergy());
        foodDTO.setFat(food.getFat());
        foodDTO.setSalt(food.getSalt());
        foodDTO.setFiber(food.getFiber());
        foodDTO.setProtein(food.getProtein());
        foodDTO.setCarbohydrates(food.getCarbohydrates());
        foodDTO.setImageUrl(food.getImageUrl());

        Set<String> allergies = new HashSet<String>();
        for (Allergy allergy : food.getAllergies()) {
            allergies.add(allergy.getAllergyName());
        }

        foodDTO.setAllergies(allergies);

        return foodDTO;
    }

}
