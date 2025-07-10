package nl.miwnn.ch16.tildereplace.recipes.repository;

import nl.miwnn.ch16.tildereplace.recipes.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Wouter Stegeman
 */

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    Optional<Allergy> findAllergyByAllergyName(String name);

}
