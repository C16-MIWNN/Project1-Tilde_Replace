package nl.miwnn.ch16.tildereplace.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping({"/"})
    private String testOverview(Model datamodel) {
        return "test-util";
    }
}
