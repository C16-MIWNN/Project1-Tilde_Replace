package nl.miwnn.ch16.tildereplace.recipes.service;


import nl.miwnn.ch16.tildereplace.recipes.model.RecipesUser;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipesUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s was not found", username)));
    }

    public void saveUser(RecipesUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        recipesUserRepository.save(user);
    }

}
