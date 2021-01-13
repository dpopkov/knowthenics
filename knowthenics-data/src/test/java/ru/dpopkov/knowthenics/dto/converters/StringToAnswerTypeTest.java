package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.model.AnswerType;

import static org.junit.jupiter.api.Assertions.*;

class StringToAnswerTypeTest {

    @Test
    void testConvert() {
        StringToAnswerType converter = new StringToAnswerType();
        assertEquals(AnswerType.ORIGINAL, converter.convert("original"));
    }
}
