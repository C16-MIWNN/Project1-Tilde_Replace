package nl.miwnn.ch16.tildereplace.recipes.repository;


import nl.miwnn.ch16.tildereplace.recipes.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findImageByName(String name);
}
