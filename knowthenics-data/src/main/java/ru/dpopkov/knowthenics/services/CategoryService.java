package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void delete(Category category);

    void deleteById(Long id);
}
