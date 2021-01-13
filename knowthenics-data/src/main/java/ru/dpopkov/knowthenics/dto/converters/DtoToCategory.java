package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.CategoryDTO;
import ru.dpopkov.knowthenics.model.Category;

@Component
public class DtoToCategory implements Converter<CategoryDTO, Category> {
    @Override
    public Category convert(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName(), dto.getDescription());
    }
}
