package nl.miwnn.ch16.tildereplace.recipes.controller;

import jakarta.servlet.http.HttpServletRequest;
import nl.miwnn.ch16.tildereplace.recipes.model.Image;
import nl.miwnn.ch16.tildereplace.recipes.service.ImageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public String saveImage(@RequestParam("image") MultipartFile file,
                             @RequestParam("recipeId") Long recipeId) {

        try {
            imageService.saveImage(file, recipeId);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        return "redirect:/recipeOverview";
    }


    @GetMapping("/{imageName}")
    private ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws IOException {
        Image image = imageService.getImage(imageName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getType())
                .body(image.getData());
    }

}
