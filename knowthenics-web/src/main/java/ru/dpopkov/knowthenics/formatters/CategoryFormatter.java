package ru.dpopkov.knowthenics.formatters;

import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.services.CategoryService;

import java.util.Locale;
import java.util.Set;

@Component
public class CategoryFormatter implements Formatter<Category> {

    private final CategoryService categoryService;

    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @NonNull
    public Category parse(@NonNull String text, @SuppressWarnings("NullableProblems") Locale locale) {
        Set<Category> all = categoryService.findAll();
        for (Category category : all) {
            if (category.getName().equals(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Category not found for text " + text);
    }

    @Override
    @NonNull
    public String print(Category object, @SuppressWarnings("NullableProblems") Locale locale) {
        return object.getName();
    }
}
