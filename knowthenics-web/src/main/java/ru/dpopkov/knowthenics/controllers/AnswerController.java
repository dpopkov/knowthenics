package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.exceptions.web.NotFoundException;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.AnswerType;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.services.AnswerService;
import ru.dpopkov.knowthenics.services.QuestionService;
import ru.dpopkov.knowthenics.services.SourceService;

import javax.validation.Valid;
import java.util.Set;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
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

    @RequestMapping({"/answers", "/answers/", "/answers/list", "/answers/list.html", "/answers/index", "/answers/index.html"})
    public String list(Model model) {
        model.addAttribute("answers", answerService.findAll());
        return "answers/list";
    }

    @GetMapping("/questions/*/answers/{answerId}/view")
    public String show(@PathVariable String answerId, Model model) {
        Long id = Long.valueOf(answerId);
        log.debug("Showing details for Answer ID {}", id);
        Answer answer = findAnswer(id);
        model.addAttribute(answer);
        return "answers/answer-details";
    }

    @GetMapping("/questions/{questionId}/answers/new")
    public String initCreateForm(@PathVariable String questionId, Model model) {
        Answer answer = new Answer();
        answer.setQuestion(findQuestion(Long.valueOf(questionId)));
        model.addAttribute("answer", answer);
        model.addAttribute("answerTypes", AnswerType.values());
        Set<Source> allSources = sourceService.findAll();
        model.addAttribute("allSources", allSources);
        return ANSWERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/questions/{questionId}/answers/new")
    public String processCreateForm(@Valid Answer answer, BindingResult result, @PathVariable String questionId) {
        if (result.hasErrors()) {
            return ANSWERS_CREATE_OR_UPDATE_FORM;
        }
        Long id = Long.valueOf(questionId);
        log.debug("Processing creation Answer for Question ID {}", id);
        answer.setQuestion(findQuestion(id));
        Answer saved = answerService.save(answer);
        return "redirect:/questions/" + id + "/answers/" + saved.getId() + "/view";
    }

    @GetMapping("/questions/*/answers/{answerId}/edit")
    public String initUpdateForm(@PathVariable String answerId, Model model) {
        Long answerIdLong = Long.valueOf(answerId);
        Answer answer = findAnswer(answerIdLong);
        model.addAttribute(answer);
        model.addAttribute("answerTypes", AnswerType.values());
        Set<Source> allSources = sourceService.findAll();
        model.addAttribute("allSources", allSources);
        return ANSWERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/questions/{questionId}/answers/{answerId}/edit")
    public String processUpdateForm(@Valid Answer answer, BindingResult result,
                                    @PathVariable String questionId, @PathVariable String answerId) {
        if (result.hasErrors()) {
            return ANSWERS_CREATE_OR_UPDATE_FORM;
        }
        Long answerIdLong = Long.valueOf(answerId);
        Answer found = findAnswer(answerIdLong);
        found.updateSimpleFieldsFrom(answer);
        Answer saved = answerService.save(found);
        return "redirect:/questions/" + questionId + "/answers/" + saved.getId() + "/view";
    }

    @RequestMapping("/answers/find")
    public String find() {
        return "notimplemented";
    }

    private Question findQuestion(Long id) {
        try {
            return questionService.findById(id);
        } catch (NotFoundInRepositoryException ex) {
            throw new NotFoundException("Question not found for ID " + id, ex);
        }
    }

    private Answer findAnswer(Long id) {
        try {
            return answerService.findById(id);
        } catch (NotFoundInRepositoryException ex) {
            throw new NotFoundException("Answer not found for ID " + id, ex);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST);
    }

    private ModelAndView handleException(Exception exception, HttpStatus status) {
        log.error("Handling {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/" + status.value() + "error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
