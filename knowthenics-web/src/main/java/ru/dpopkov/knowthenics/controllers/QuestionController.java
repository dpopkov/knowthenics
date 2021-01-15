package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QuestionService;

@SuppressWarnings("SameReturnValue")
@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping({"", "/", "list", "list.html", "index", "index.html"})
    public String list(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "questions/list";
    }

    @GetMapping("/{questionId}")
    public ModelAndView showQuestion(@PathVariable String questionId) {
        Long id = Long.valueOf(questionId);
        ModelAndView modelAndView = new ModelAndView("questions/question-details");
        Question question = questionService.findById(id);
        modelAndView.addObject(question);
        return modelAndView;
    }

    @RequestMapping("/find")
    public String find() {
        return "notimplemented";
    }
}
