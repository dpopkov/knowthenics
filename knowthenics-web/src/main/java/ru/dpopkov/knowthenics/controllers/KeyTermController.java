package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.KeyTermService;

import javax.validation.Valid;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
@RequestMapping("/keyterms")
public class KeyTermController {

    private static final String KEYTERMS_CREATE_OR_UPDATE_FORM = "keyterms/create-or-update-form";
    private static final String REDIRECT_KEYTERMS_LIST = "redirect:/keyterms/list";

    private final KeyTermService keyTermService;

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public KeyTermController(KeyTermService keyTermService) {
        this.keyTermService = keyTermService;
    }

    @RequestMapping({"", "/", "list", "list.html", "index", "index.html"})
    public String list(Model model) {
        model.addAttribute("keyterms", keyTermService.findAll());
        return "keyterms/list";
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute(new KeyTerm());
        return KEYTERMS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid KeyTerm keyTerm, BindingResult result) {
        if (result.hasErrors()) {
            logErrors(result);
            return KEYTERMS_CREATE_OR_UPDATE_FORM;
        }
        keyTermService.save(keyTerm);
        return REDIRECT_KEYTERMS_LIST;
    }

    @GetMapping("/{keytermId}/edit")
    public String initUpdateForm(@PathVariable String keytermId, Model model) {
        KeyTerm keyTerm = keyTermService.findById(Long.parseLong(keytermId));
        model.addAttribute(keyTerm);
        return KEYTERMS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{keytermId}/edit")
    public String processUpdateForm(@Valid KeyTerm keyTerm, BindingResult result, @PathVariable String keytermId) {
        keyTerm.setId(Long.parseLong(keytermId));
        if (result.hasErrors()) {
            logErrors(result);
            return KEYTERMS_CREATE_OR_UPDATE_FORM;
        }
        keyTermService.save(keyTerm);
        return REDIRECT_KEYTERMS_LIST;
    }

    private void logErrors(BindingResult result) {
        result.getAllErrors().forEach(err -> log.error(err.toString()));
    }
}
