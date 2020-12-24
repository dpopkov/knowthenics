package ru.dpopkov.knowthenics.services.map;

import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.services.CategoryService;

@Service
public class CategoryMapService extends AbstractMapService<Category> implements CategoryService {
}
