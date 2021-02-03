package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dpopkov.knowthenics.model.QCollection;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QCollectionService;
import ru.dpopkov.knowthenics.services.QuestionService;

import java.util.Set;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
@RequestMapping("/qcollections")
public class QCollectionController {

    private static final String QCOLLECTIONS_CREATE_OR_UPDATE_FORM = "qcollections/create-or-update-form";

    private final QCollectionService qCollectionService;
    private final QuestionService questionService;

    public QCollectionController(QCollectionService qCollectionService, QuestionService questionService) {
        this.qCollectionService = qCollectionService;
        this.questionService = questionService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("allCollections", qCollectionService.findAll());
        return "qcollections/list";
    }

    @GetMapping("/{qcollectionId}")
    public String show(@PathVariable String qcollectionId, Model model) {
        Long id = Long.valueOf(qcollectionId);
        model.addAttribute("qCollection", qCollectionService.findById(id));
        return "qcollections/qcollection-details";
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute("qCollection", new QCollection());
        Set<Question> allQuestions = questionService.findAll();
        model.addAttribute("allQuestions", allQuestions);
        return QCOLLECTIONS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processNewForm(QCollection qCollection) {
        QCollection saved = qCollectionService.save(qCollection);
        log.debug("Created new QCollection ID {}", saved.getId());
        return "redirect:/qcollections/" + saved.getId();
    }

    @GetMapping("/{qcollectionId}/edit")
    public String initUpdateForm(@PathVariable String qcollectionId, Model model) {
        Long id = Long.valueOf(qcollectionId);
        QCollection found = qCollectionService.findById(id);
        model.addAttribute("qCollection", found);
        Set<Question> allQuestions = questionService.findAll();
        model.addAttribute("allQuestions", allQuestions);
        return QCOLLECTIONS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{qcollectionId}/edit")
    public String processUpdateForm(@PathVariable String qcollectionId, QCollection qCollection) {
        Long id = Long.valueOf(qcollectionId);
        QCollection found = qCollectionService.findById(id);
        found.updateFrom(qCollection);
        QCollection saved = qCollectionService.save(found);
        log.debug("Updated QCollection ID {}", saved.getId());
        return "redirect:/qcollections/" + saved.getId();
    }
}
