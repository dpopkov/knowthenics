package ru.dpopkov.knowthenics.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldValueValidatorImplTest {
    private final FieldValueValidatorImpl validator = new FieldValueValidatorImpl();

    @Test
    void testUrlIsValid() {
        assertTrue(validator.urlIsValid("http://example.com"));
        assertTrue(validator.urlIsValid("https://example.com"));
        assertFalse(validator.urlIsValid("htttttps:abracadabra"));
    }
}
