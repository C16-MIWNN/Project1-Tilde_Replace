package nl.miwnn.ch16.tildereplace.recipes.repository;


import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
