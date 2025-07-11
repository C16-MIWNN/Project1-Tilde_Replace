package nl.miwnn.ch16.tildereplace.recipes.service;

import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.dto.FoodDTO;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.AllergyRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.mapper.FoodMapper;
import org.springframework.stereotype.Service;

/**
 * @author Wouter Stegeman
 * Basic Food service
 */

@Service
public class FoodService {

    private final FoodMapper foodMapper;
    private final AllergyRepository allergyRepository;
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository, AllergyRepository allergyRepository) {
         this.foodRepository    = foodRepository;
         this.allergyRepository = allergyRepository;
         foodMapper = new FoodMapper(foodRepository, allergyRepository);
    }

    public FoodDTO toDTO(Food food) {
        return foodMapper.toDTO(food);
    }

    public void save(FoodDTO foodDTO) {
        foodRepository.save(foodMapper.fromDTO(foodDTO));
    }

}
