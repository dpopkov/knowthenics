package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.model.SourceType;
import ru.dpopkov.knowthenics.services.SourceService;

import javax.validation.Valid;

@SuppressWarnings("SameReturnValue")
@Controller
@RequestMapping("/sources")
public class SourceController {

    private static final String SOURCES_CREATE_OR_UPDATE_FORM = "sources/create-or-update-form";
    private static final String REDIRECT_SOURCES = "redirect:/sources";

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "list", "list.html", "index", "index.html"})
    public String list(Model model) {
        model.addAttribute("sources", sourceService.findAll());
        return "sources/list";
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute(new Source());
        model.addAttribute("sourceTypes", SourceType.values());
        return SOURCES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Source source, BindingResult result) {
        if (result.hasErrors()) {
            return SOURCES_CREATE_OR_UPDATE_FORM;
        }
        sourceService.save(source);
        return REDIRECT_SOURCES;
    }

    @GetMapping("/{sourceId}/edit")
    public String initUpdateForm(@PathVariable String sourceId, Model model) {
        Source source = sourceService.findById(Long.parseLong(sourceId));
        model.addAttribute(source);
        model.addAttribute("sourceTypes", SourceType.values());
        return SOURCES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{sourceId}/edit")
    public String processUpdateForm(@Valid Source source, BindingResult result, @PathVariable String sourceId) {
        if (result.hasErrors()) {
            return SOURCES_CREATE_OR_UPDATE_FORM;
        }
        source.setId(Long.parseLong(sourceId));
        sourceService.save(source);
        return REDIRECT_SOURCES;
    }
}
