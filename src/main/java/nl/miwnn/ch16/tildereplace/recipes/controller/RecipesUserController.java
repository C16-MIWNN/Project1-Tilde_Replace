package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.dto.NewRecipesUserDTO;
import nl.miwnn.ch16.tildereplace.recipes.repository.RecipesUserRepository;
import nl.miwnn.ch16.tildereplace.recipes.service.RecipesUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RecipesUserController {

    private final RecipesUserRepository recipesUserRepository;
    private final RecipesUserService recipesUserService;

    public RecipesUserController(RecipesUserRepository recipesUserRepository, RecipesUserService recipesUserService, RecipesUserService recipesUserService1) {
        this.recipesUserRepository = recipesUserRepository;
        this.recipesUserService = recipesUserService1;
    }

    @GetMapping("/userOverview")
    public String showUserOverview(Model dataModel) {
        dataModel.addAttribute("allUsers", recipesUserRepository.findAll());
        return "userOverview";
    }

    @GetMapping("/new")
    private String newRecipesUser(Model dataModel) {
        dataModel.addAttribute("userForm", new NewRecipesUserDTO());
        return "userForm";
    }

    @PostMapping("/save")
    public String saveOrUpdateRecipesUser(@ModelAttribute("userForm") NewRecipesUserDTO newRecipesUserDTO, BindingResult result) {

        if (recipesUserService.usernameInUse(newRecipesUserDTO.getUsername())) {
            result.rejectValue("username", "duplicate username", "gebruikersnaam is al in gebruik");
        }

        if (newRecipesUserDTO.getUsername().isEmpty()) {
            result.rejectValue("username", "empty username", "gebruikersnaam mag niet leeg zijn");
        }

        if (newRecipesUserDTO.getPassword().isEmpty()) {
            result.rejectValue("password", "empty password", "wachtwoord mag niet leeg zijn");
        }

        if (!newRecipesUserDTO.getPassword().equals(newRecipesUserDTO.getPasswordConfirm())) {
            result.rejectValue("password", "no matching", "wachtwoorden komen niet overeen");
        }

        if (result.hasErrors()) {
            return "userForm";
        }

        recipesUserService.save(newRecipesUserDTO);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteRecipesUser(@ModelAttribute("userId") Long userId, BindingResult result) {
        if (result.hasErrors()) {
            return "userOverview";
        }

        recipesUserRepository.deleteById(userId);
        return "redirect:/user/userOverview";
    }

}
