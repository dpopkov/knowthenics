package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QuestionService;

import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("SameReturnValue")
@Controller
@RequestMapping("/questions")
public class QuestionController {

    private static final String QUESTIONS_FIND_QUESTIONS = "questions/find-questions";

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
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
}
