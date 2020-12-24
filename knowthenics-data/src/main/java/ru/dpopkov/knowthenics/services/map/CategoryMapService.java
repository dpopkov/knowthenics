package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.services.CategoryService;

@Service
@Profile({"default", "map-service"})
public class CategoryMapService extends AbstractMapService<Category> implements CategoryService {
}
