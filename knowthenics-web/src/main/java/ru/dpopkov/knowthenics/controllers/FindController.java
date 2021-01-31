package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.Question;

@Controller
public class FindController {

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute(new Question());
        model.addAttribute(new Answer());
        return "find-form";
    }
}
