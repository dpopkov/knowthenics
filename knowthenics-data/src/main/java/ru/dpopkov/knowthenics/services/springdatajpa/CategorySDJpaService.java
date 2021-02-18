package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.repositories.CategoryRepository;
import ru.dpopkov.knowthenics.services.CategoryService;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class CategorySDJpaService extends AbstractSDJpaService<Category> implements CategoryService {

    public CategorySDJpaService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}
