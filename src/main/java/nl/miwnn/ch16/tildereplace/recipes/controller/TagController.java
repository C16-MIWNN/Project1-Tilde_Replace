/**
 * @author Remi van Loenen
 * Controller that manages all mappings related to tags
 */

package nl.miwnn.ch16.tildereplace.recipes.controller;

import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.model.Tag;
import nl.miwnn.ch16.tildereplace.recipes.repository.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping({"/tagOverview"})
    public String showTagOverview(Model dataModel) {
        dataModel.addAttribute("tagForm", new Tag());
        dataModel.addAttribute("allTags", tagRepository.findAll());
        return "tagOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateTag(@ModelAttribute("tagForm") Tag savedTag,
                                   BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
        } else {
            tagRepository.save(savedTag);
        }
        return "redirect:/tags/tagOverview";
    }


}
