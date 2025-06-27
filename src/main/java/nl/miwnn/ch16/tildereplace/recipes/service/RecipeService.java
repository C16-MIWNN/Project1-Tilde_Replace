package nl.miwnn.ch16.tildereplace.recipes.service;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipeDTO;
import nl.miwnn.ch16.tildereplace.recipes.repository.FoodRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.IngredientRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.UnitRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.mapper.NewRecipeMapper;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;
    private final UnitRepository unitRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, FoodRepository foodRepository, UnitRepository unitRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;
        this.unitRepository = unitRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void save(NewRecipeDTO newRecipeDTO) {
        NewRecipeMapper mapper = new NewRecipeMapper(foodRepository, unitRepository, ingredientRepository);
        recipeRepository.save(mapper.fromDto(newRecipeDTO));
    }

}
