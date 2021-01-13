package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.CategoryDTO;
import ru.dpopkov.knowthenics.model.Category;

import static org.junit.jupiter.api.Assertions.*;

class DtoToCategoryTest {

    @Test
    void testConvert() {
        DtoToCategory converter = new DtoToCategory();
        CategoryDTO dto = CategoryDTO.builder().id(10L).name("name1").description("description1").build();
        Category category = converter.convert(dto);
        assertNotNull(category);
        assertEquals(10L, category.getId());
        assertEquals("name1", category.getName());
        assertEquals("description1", category.getDescription());
    }
}
