package nl.miwnn.ch16.tildereplace.recipes.repository;

import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;

import nl.miwnn.ch16.tildereplace.recipes.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findRecipeByRecipeName(String name);

    List<Recipe> findRecipesByTags(List<Tag> tags);

}