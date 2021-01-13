package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.CategoryDTO;
import ru.dpopkov.knowthenics.model.Category;

@Component
public class CategoryToDto implements Converter<Category, CategoryDTO> {
    @Override
    public CategoryDTO convert(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }
}
