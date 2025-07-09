package nl.miwnn.ch16.tildereplace.recipes.repository;


import nl.miwnn.ch16.tildereplace.recipes.model.RecipesUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipesUserRepository extends JpaRepository<RecipesUser, Long> {
    Optional<RecipesUser> findByUsername(String username);
    Optional<RecipesUser> findByUserId(Long userId);
    boolean existsByUsername(String username);
}
