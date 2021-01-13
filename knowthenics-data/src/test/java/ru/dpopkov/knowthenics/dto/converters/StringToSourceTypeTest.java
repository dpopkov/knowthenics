package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.model.SourceType;

import static org.junit.jupiter.api.Assertions.*;

class StringToSourceTypeTest {

    @Test
    void testConvert() {
        StringToSourceType converter = new StringToSourceType();
        assertEquals(SourceType.WEB_SITE, converter.convert("web site"));
    }
}
