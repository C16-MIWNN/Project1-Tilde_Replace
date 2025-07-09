package nl.miwnn.ch16.tildereplace.recipes.service;

import nl.miwnn.ch16.tildereplace.recipes.model.Image;
import nl.miwnn.ch16.tildereplace.recipes.model.Recipe;
import nl.miwnn.ch16.tildereplace.recipes.repository.ImageRepository;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final RecipeRepository recipeRepository;

    public ImageService(ImageRepository imageRepository, RecipeRepository recipeRepository) {
        this.imageRepository = imageRepository;
        this.recipeRepository = recipeRepository;
    }

    public Image saveImage(MultipartFile file, Long recipeId) throws IOException {
        Image image = new Image();
        image.setName(String.format(recipeId + "_" + file.getOriginalFilename()));
        image.setType(file.getContentType());
        image.setData(file.getBytes());

        imageRepository.save(image);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            recipeOptional.get().setImage(image);
            recipeRepository.save(recipeOptional.get());
        }

        return image;
    }

    public void saveFile(String folderName) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();

        if (files == null) {
            throw new RuntimeException("Folder is empty");
        }

        try {
            for (File file : files) {
                if (file.isFile()) {
                    Image image = new Image();
                    image.setName(file.getName());
                    image.setType(Files.probeContentType(file.toPath()));
                    image.setData(Files.readAllBytes(file.toPath()));

                    imageRepository.save(image);
                }
            }
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

    }

    public Image getImage(String imageName) {
        Optional<Image> imageOptional = imageRepository.findImageByName(imageName);
        if (imageOptional.isEmpty()) {
            Optional<Image> defaultImageOptional = imageRepository.findImageByName("default_recipe_image");

            if (defaultImageOptional.isEmpty()) {
                throw new RuntimeException("Image and default image not found");
            } else {
                return defaultImageOptional.get();
            }

        }
        return imageOptional.get();
    }

}
