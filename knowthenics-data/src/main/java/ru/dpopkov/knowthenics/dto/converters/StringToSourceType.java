package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.SourceType;

@Component
public class StringToSourceType implements Converter<String, SourceType> {

    @Override
    public SourceType convert(String stringSource) {
        String source = stringSource.replace(" ", "_");
        return SourceType.valueOf(source.toUpperCase());
    }
}
