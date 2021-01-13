package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.SourceDTO;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.model.SourceType;

import static org.junit.jupiter.api.Assertions.*;

class SourceToDtoTest {

    @Test
    void testConvert() {
        SourceToDto converter = new SourceToDto(new EnumToString());
        Source source = Source.builder().id(12L).shortTitle("short").fullTitle("full")
                .url("url").sourceType(SourceType.API_DOC)
                .description("text").build();
        SourceDTO dto = converter.convert(source);
        assertNotNull(dto);
        assertEquals("short", dto.getShortTitle());
        assertEquals("full", dto.getFullTitle());
        assertEquals("url", dto.getUrl());
        assertEquals("api doc", dto.getSourceType());
        assertEquals("text", dto.getDescription());
    }
}
