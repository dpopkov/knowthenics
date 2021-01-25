package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.AnswerType;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.services.AnswerService;
import ru.dpopkov.knowthenics.services.QuestionService;
import ru.dpopkov.knowthenics.services.SourceService;

import javax.validation.Valid;
import java.util.Set;

@SuppressWarnings({"SameReturnValue"})
@Slf4j
@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    private static final String ANSWERS_CREATE_OR_UPDATE_FORM = "answers/create-or-update-form";

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final SourceService sourceService;

    public AnswerController(AnswerService answerService, QuestionService questionService, SourceService sourceService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.sourceService = sourceService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("allSources")
    public Set<Source> populateSources() {
        return sourceService.findAll();
    }

    @ModelAttribute("answerTypes")
    public AnswerType[] populateAnswerTypes() {
        return AnswerType.values();
    }

    @GetMapping("/new")
    public String initCreateForm(@PathVariable String questionId, Model model) {
        Answer answer = new Answer();
        answer.setQuestion(questionService.findById(Long.valueOf(questionId)));
        model.addAttribute("answer", answer);
        return ANSWERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Answer answer, BindingResult result, @PathVariable String questionId) {
        Long id = Long.valueOf(questionId);
        log.debug("Processing creation Answer for Question ID {}", id);
        answer.setQuestion(questionService.findById(id));
        if (result.hasErrors()) {
            logErrors(result);
            return ANSWERS_CREATE_OR_UPDATE_FORM;
        }
        Answer saved = answerService.save(answer);
        return "redirect:/questions/" + id + "/answers/" + saved.getId() + "/view";
    }

    @GetMapping("/{answerId}/edit")
    public String initUpdateForm(@PathVariable String answerId, Model model) {
        Long answerIdLong = Long.valueOf(answerId);
        Answer answer = answerService.findById(answerIdLong);
        model.addAttribute(answer);
        return ANSWERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{answerId}/edit")
    public String processUpdateForm(@Valid Answer answer, BindingResult result,
                                    @PathVariable String questionId, @PathVariable String answerId) {
        Long id = Long.valueOf(questionId);
        Long answerIdLong = Long.valueOf(answerId);
        log.debug("Processing updating Answer ID {} for Question ID {}", id, answerIdLong);
        answer.setQuestion(questionService.findById(id));
        if (result.hasErrors()) {
            logErrors(result);
            return ANSWERS_CREATE_OR_UPDATE_FORM;
        }
        Answer found = answerService.findById(answerIdLong);
        found.updateSimpleFieldsFrom(answer);
        Answer saved = answerService.save(found);
        return "redirect:/questions/" + questionId + "/answers/" + saved.getId() + "/view";
    }

    private void logErrors(BindingResult result) {
        result.getAllErrors().forEach(err -> log.error(err.toString()));
    }
}
