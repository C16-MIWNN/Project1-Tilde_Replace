package nl.miwnn.ch16.tildereplace.recipes.service.mapper;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipesUserDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.RecipesUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class NewRecipeUserMapper {

    BCryptPasswordEncoder passwordEncoder;

    public static RecipesUser fromDto(NewRecipesUserDTO userDTO) {
        RecipesUser newUser = new RecipesUser();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());

        return newUser;
    }
}
