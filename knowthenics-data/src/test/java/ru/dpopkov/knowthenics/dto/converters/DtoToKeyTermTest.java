package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.model.KeyTerm;

import static org.junit.jupiter.api.Assertions.*;

class DtoToKeyTermTest {

    @Test
    void testConvert() {
        DtoToKeyTerm converter = new DtoToKeyTerm();
        KeyTermDTO dto = KeyTermDTO.builder().id(2L).name("name3").description("description3").build();
        KeyTerm keyTerm = converter.convert(dto);
        assertNotNull(keyTerm);
        assertEquals(2L, keyTerm.getId());
        assertEquals("name3", keyTerm.getName());
        assertEquals("description3", keyTerm.getDescription());
    }
}
