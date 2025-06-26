package nl.miwnn.ch16.tildereplace.recipes.service.mapper;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipesUserDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.RecipesUser;

public class NewRecipeUserMapper {

    public static RecipesUser fromDto(NewRecipesUserDTO userDTO) {
        RecipesUser newUser = new RecipesUser();
        newUser.setUsername(newUser.getUsername());
        newUser.setPassword(newUser.getPassword());

        return newUser;
    }
}
