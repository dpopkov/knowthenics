package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.CategoryService;
import ru.dpopkov.knowthenics.services.QuestionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
@RequestMapping("/questions")
public class QuestionController {

    private static final String QUESTIONS_FIND_QUESTIONS = "questions/find-questions";
    private static final String QUESTIONS_CREATE_OR_UPDATE_FORM = "questions/create-or-update-form";

    private final QuestionService questionService;
    private final CategoryService categoryService;

    public QuestionController(QuestionService questionService, CategoryService categoryService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("categories")
    public Set<Category> populateCategories() {
        return categoryService.findAll();
    }

    @GetMapping({"list", "list.html", "index", "index.html"})
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

    @GetMapping("/find")
    public String initFindQuestionForm(Model model) {
        model.addAttribute(new Question());
        return QUESTIONS_FIND_QUESTIONS;
    }

    @GetMapping("")
    public String processFindQuestionForm(Question question, BindingResult result, Model model) {
        if (question.getWordingEn() == null) {
            question.setWordingEn("");
        }
        String searchPattern = "%" + question.getWordingEn() + "%";
        Set<Question> questions = questionService.findAllByWordingEnLike(searchPattern);
        if (questions.isEmpty()) {
            result.rejectValue("wordingEn", "notFound", "not found");
            return QUESTIONS_FIND_QUESTIONS;
        } else if (questions.size() == 1) {
            Question one = new ArrayList<>(questions).get(0);
            return "redirect:/questions/" + one.getId();
        } else {
            model.addAttribute("questions", questions);
            return "questions/list";
        }
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute(new Question());
        return QUESTIONS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            logErrors(result);
            return QUESTIONS_CREATE_OR_UPDATE_FORM;
        }
        Question created = questionService.save(question);
        log.debug("Created question ID {}", created.getId());
        return "redirect:/questions/" + created.getId();
    }

    @GetMapping("/{questionId}/edit")
    public String initUpdateForm(@PathVariable String questionId, Model model) {
        Long id = Long.parseLong(questionId);
        Question question = questionService.findById(id);
        model.addAttribute(question);
        return QUESTIONS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{questionId}/edit")
    public String processUpdateForm(@Valid Question question, BindingResult result, @PathVariable String questionId) {
        if (result.hasErrors()) {
            logErrors(result);
            return QUESTIONS_CREATE_OR_UPDATE_FORM;
        }
        Question found = questionService.findById(Long.parseLong(questionId));
        found.updateSimpleFieldsFrom(question);
        Question updated = questionService.save(found);
        log.debug("Updated question ID {}", updated.getId());
        return "redirect:/questions/" + updated.getId();
    }

    private void logErrors(BindingResult result) {
        result.getAllErrors().forEach(err -> log.error(err.toString()));
    }
}
