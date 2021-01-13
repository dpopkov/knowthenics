package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumToString implements Converter<Enum<?>, String> {

    @Override
    public String convert(Enum<?> enumSource) {
        return enumSource.name().toLowerCase().replace("_", " ");
    }
}
