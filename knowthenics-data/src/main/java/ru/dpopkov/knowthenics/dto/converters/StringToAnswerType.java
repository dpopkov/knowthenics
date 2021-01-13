package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.AnswerType;

@Component
public class StringToAnswerType implements Converter<String, AnswerType> {

    @Override
    public AnswerType convert(String stringSource) {
        String source = stringSource.replace(" ", "_");
        return AnswerType.valueOf(source.toUpperCase());
    }
}
