package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.repository.RecipesUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RecipesUserController {

    private final RecipesUserRepository recipesUserRepository;

    public RecipesUserController(RecipesUserRepository recipesUserRepository) {
        this.recipesUserRepository = recipesUserRepository;
    }

    @GetMapping("/userOverview")
    public String showUserOverview(Model dataModel) {
        dataModel.addAttribute("allUsers", recipesUserRepository.findAll());
        return "userOverview";
    }



}
