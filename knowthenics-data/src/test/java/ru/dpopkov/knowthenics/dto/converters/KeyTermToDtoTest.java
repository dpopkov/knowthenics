package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.model.KeyTerm;

import static org.junit.jupiter.api.Assertions.*;

class KeyTermToDtoTest {

    @Test
    void testConvert() {
        KeyTermToDto converter = new KeyTermToDto();
        KeyTerm keyTerm = KeyTerm.builder().id(1L).name("name2").description("description2").build();
        KeyTermDTO dto = converter.convert(keyTerm);
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("name2", dto.getName());
        assertEquals("description2", dto.getDescription());
    }
}
