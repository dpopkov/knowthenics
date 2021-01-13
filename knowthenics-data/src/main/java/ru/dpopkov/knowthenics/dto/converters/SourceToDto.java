package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.SourceDTO;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.model.SourceType;

@Component
public class SourceToDto implements Converter<Source, SourceDTO> {

    private final EnumToString enumToString;

    public SourceToDto(EnumToString enumToString) {
        this.enumToString = enumToString;
    }

    @Override
    public SourceDTO convert(Source source) {
        SourceDTO dto = SourceDTO.builder().id(source.getId())
                .shortTitle(source.getShortTitle())
                .fullTitle(source.getFullTitle())
                .url(source.getUrl())
                .description(source.getDescription()).build();
        SourceType sourceType = source.getSourceType();
        if (sourceType != null) {
            dto.setSourceType(enumToString.convert(sourceType));
        }
        return dto;
    }
}
