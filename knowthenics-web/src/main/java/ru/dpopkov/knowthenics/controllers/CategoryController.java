package ru.dpopkov.knowthenics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.services.CategoryService;

import javax.validation.Valid;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final String CATEGORIES_CREATE_OR_UPDATE_FORM = "categories/create-or-update-form";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "list", "list.html", "index", "index.html"})
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute(new Category());
        return CATEGORIES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            logErrors(result);
            return CATEGORIES_CREATE_OR_UPDATE_FORM;
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{categoryId}/edit")
    public String initUpdateForm(@PathVariable String categoryId, Model model) {
        Category category = categoryService.findById(Long.parseLong(categoryId));
        model.addAttribute(category);
        return CATEGORIES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{categoryId}/edit")
    public String processUpdateForm(@Valid Category category, BindingResult result, @PathVariable String categoryId) {
        category.setId(Long.parseLong(categoryId));
        if (result.hasErrors()) {
            logErrors(result);
            return CATEGORIES_CREATE_OR_UPDATE_FORM;
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    private void logErrors(BindingResult result) {
        result.getAllErrors().forEach(err -> log.error(err.toString()));
    }
}
