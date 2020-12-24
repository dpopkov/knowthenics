package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
