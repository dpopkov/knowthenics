package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.CategoryDTO;
import ru.dpopkov.knowthenics.model.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToDtoTest {

    @Test
    void testConvert() {
        CategoryToDto converter = new CategoryToDto();
        Category category = Category.builder().id(10L).name("name1").description("description1").build();
        CategoryDTO dto = converter.convert(category);
        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals("name1", dto.getName());
        assertEquals("description1", dto.getDescription());
    }
}
