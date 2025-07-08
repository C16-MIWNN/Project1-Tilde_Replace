package nl.miwnn.ch16.tildereplace.recipes.service;


import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipesUserDTO;
import nl.miwnn.ch16.tildereplace.recipes.model.RecipesUser;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipesUserRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.mapper.NewRecipeUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipesUserService implements UserDetailsService {

    private final RecipesUserRepository recipesUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RecipesUserService(RecipesUserRepository recipesUserRepository, PasswordEncoder passwordEncoder) {
        this.recipesUserRepository = recipesUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return recipesUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("UserDetail: %s was not found", username)));
    }

    public void saveUser(RecipesUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        recipesUserRepository.save(user); // mapper
    }

    public boolean usernameInUse(String username) {
        return recipesUserRepository.existsByUsername(username);
    }

    public void save(NewRecipesUserDTO newRecipesUserDTO) {
        saveUser(NewRecipeUserMapper.fromDto(newRecipesUserDTO));
    }

    public RecipesUser getRecipeUserByUsername(String username) {
        Optional<RecipesUser> recipesUserOptional = recipesUserRepository.findByUsername(username);
        if (!recipesUserOptional.isPresent()) {
            throw new UsernameNotFoundException("RecipeUser: %s was not found");
        }

        return recipesUserOptional.get();
    }

    public void updateRecipeUserPassword(String username, String password) {
        Optional<RecipesUser> passwordToBeUpdatedUser = recipesUserRepository.
                findByUsername(username);

        if (!passwordToBeUpdatedUser.isPresent()) {
            throw new UsernameNotFoundException("Username not found");
        }

        passwordToBeUpdatedUser.get().setPassword(passwordEncoder.encode(password));
        recipesUserRepository.save(passwordToBeUpdatedUser.get());

    }

    public boolean userExists(String username) {
        Optional<RecipesUser> recipesUserOptional = recipesUserRepository.findByUsername(username);
        return recipesUserOptional.isPresent();
    }

    public void setNewUsername(String username, String newUsername) {
        Optional<RecipesUser> recipesUserOptional = recipesUserRepository.findByUsername(username);
        if (recipesUserOptional.isPresent()) {
            recipesUserOptional.get().setUsername(newUsername);
            recipesUserRepository.save(recipesUserOptional.get());
        }
    }
}
