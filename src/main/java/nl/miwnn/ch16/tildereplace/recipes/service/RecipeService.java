package nl.miwnn.ch16.tildereplace.recipes.service;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipeDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;
import nl.miwnn.ch16.tildereplace.recipes.repository.*;
import nl.miwnn.ch16.tildereplace.recipes.service.mapper.NewRecipeMapper;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;
    private final UnitRepository unitRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipesUserRepository recipesUserRepository;
    private final TagRepository tagRepository;

    public RecipeService(RecipeRepository recipeRepository,
                         FoodRepository foodRepository,
                         UnitRepository unitRepository,
                         IngredientRepository ingredientRepository,
                         RecipesUserRepository recipesUserRepository,
                         TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;
        this.unitRepository = unitRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
        this.recipesUserRepository = recipesUserRepository;
    }

    public void save(NewRecipeDTO newRecipeDTO) {
        NewRecipeMapper mapper = new NewRecipeMapper(foodRepository, unitRepository, ingredientRepository,
                recipeRepository, recipesUserRepository, tagRepository);
        recipeRepository.save(mapper.fromDto(newRecipeDTO));
    }

    public NewRecipeDTO recipeEdit(Recipe recipe) {
        NewRecipeMapper mapper = new NewRecipeMapper(foodRepository, unitRepository, ingredientRepository,
                recipeRepository, recipesUserRepository,tagRepository);
        return mapper.toDto(recipe);
    }

}
