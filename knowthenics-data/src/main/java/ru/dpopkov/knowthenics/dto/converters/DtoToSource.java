package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.SourceDTO;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.model.SourceType;

@Component
public class DtoToSource implements Converter<SourceDTO, Source> {

    @Override
    public Source convert(SourceDTO dto) {
        Source source = Source.builder()
                .id(dto.getId())
                .shortTitle(dto.getShortTitle())
                .fullTitle(dto.getFullTitle())
                .url(dto.getUrl())
                .description(dto.getDescription()).build();
        String typeStr = dto.getSourceType().toUpperCase().replace(" ", "_");
        SourceType type = SourceType.valueOf(typeStr);
        source.setSourceType(type);
        return source;
    }
}
