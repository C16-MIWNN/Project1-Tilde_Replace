package nl.miwnn.ch16.tildereplace.recipes.repository;

import nl.miwnn.ch16.tildereplace.recipes.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wouter Stegeman
 */

public interface UnitRepository extends JpaRepository<Unit, Long> {

}
