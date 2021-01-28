package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.AnswerService;
import ru.dpopkov.knowthenics.services.KeyTermService;

import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
public class AnswersAllController {

    private static final String ANSWERS_FIND_ANSWERS = "answers/find-answers";

    private final AnswerService answerService;
    private final KeyTermService keyTermService;

    public AnswersAllController(AnswerService answerService, KeyTermService keyTermService) {
        this.answerService = answerService;
        this.keyTermService = keyTermService;
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
    public String initFindAnswerForm(Model model) {
        model.addAttribute(new Answer());
        return ANSWERS_FIND_ANSWERS;
    }

    @PostMapping("/answers/find")
    public String processFindAnswerForm(Answer answer, BindingResult result, Model model) {
        if (answer.getWordingEn() == null) {
            answer.setWordingEn("");
        }
        String searchPattern = "%" + answer.getWordingEn() + "%";
        Set<Answer> found = answerService.findAllByWordingEnLike(searchPattern);
        if (found.isEmpty()) {
            result.rejectValue("wordingEn", "notFound", "not found");
            return ANSWERS_FIND_ANSWERS;
        } else if (found.size() == 1) {
            Answer single = new ArrayList<>(found).get(0);
            return "redirect:/questions/" + single.getQuestion().getId() + "/answers/" + single.getId() + "/view";
        } else {
            model.addAttribute("answers", found);
            return "answers/list";
        }
    }

    @GetMapping("/answers/findByKeyTerm/{keyTermId}")
    public String findByKeyTerm(@PathVariable Long keyTermId, Model model) {
        KeyTerm keyTerm = keyTermService.findById(keyTermId);
        Set<Answer> found = answerService.findByKeyTerm(keyTerm);
        model.addAttribute("answers", found);
        return "answers/list";
    }
}
