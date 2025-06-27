package nl.miwnn.ch16.tildereplace.recipes.service;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipeDTO;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.mapper.NewRecipeMapper;

public class RecipeService {

    RecipeRepository recipeRepository;
    NewRecipeMapper recipeMapper;

    public RecipeService(RecipeRepository recipeRepository, NewRecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    public void save(NewRecipeDTO newRecipeDTO) {
        recipeRepository.save(recipeMapper.fromDto(newRecipeDTO));
    }
}
