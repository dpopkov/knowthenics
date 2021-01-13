package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.SourceDTO;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.model.SourceType;

import static org.junit.jupiter.api.Assertions.*;

class DtoToSourceTest {

    @Test
    void testConvert() {
        DtoToSource converter = new DtoToSource();
        SourceDTO dto = SourceDTO.builder()
                .id(10L)
                .shortTitle("short").fullTitle("full")
                .url("url").description("text")
                .sourceType("web site").build();
        Source source = converter.convert(dto);
        assertNotNull(source);
        assertEquals(10L, source.getId());
        assertEquals("short", source.getShortTitle());
        assertEquals("full", source.getFullTitle());
        assertEquals("url", source.getUrl());
        assertEquals("text", source.getDescription());
        assertEquals(SourceType.WEB_SITE, source.getSourceType());
    }
}
