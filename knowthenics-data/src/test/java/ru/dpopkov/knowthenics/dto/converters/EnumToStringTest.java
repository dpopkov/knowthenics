package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.model.SourceType;

import static org.junit.jupiter.api.Assertions.*;

class EnumToStringTest {

    private final EnumToString converter = new EnumToString();

    @Test
    void testConvertToString() {
        Enum<?> enumValue = SourceType.WEB_SITE;
        String result = converter.convert(enumValue);
        assertEquals("web site", result);
    }
}
