package ru.dpopkov.knowthenics.utils;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class FieldValueValidatorImpl implements FieldValueValidator {
    private static final String[] SCHEMES = {"http", "https"};

    private final UrlValidator urlValidator = new UrlValidator(SCHEMES);

    public boolean urlIsValid(String url) {
        return urlValidator.isValid(url);
    }
}
