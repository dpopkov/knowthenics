package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.services.AnswerService;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
public class AnswersAllController {

    private final AnswerService answerService;

    public AnswersAllController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping({"/answers", "/answers/", "/answers/list", "/answers/list.html", "/answers/index", "/answers/index.html"})
    public String list(Model model) {
        model.addAttribute("answers", answerService.findAll());
        return "answers/list";
    }

    @GetMapping("/questions/*/answers/{answerId}/view")
    public String show(@PathVariable String answerId, Model model) {
        Long id = Long.valueOf(answerId);
        log.debug("Showing details for Answer ID {}", id);
        Answer answer = answerService.findById(id);
        model.addAttribute(answer);
        return "answers/answer-details";
    }

    @GetMapping("/answers/find")
    public String find() {
        log.debug("Finding Answers is not implemented yet");
        return "notimplemented";
    }
}
