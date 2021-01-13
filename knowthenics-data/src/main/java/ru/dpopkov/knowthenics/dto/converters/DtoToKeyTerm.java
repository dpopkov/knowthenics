package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.model.KeyTerm;

@Component
public class DtoToKeyTerm implements Converter<KeyTermDTO, KeyTerm> {
    @Override
    public KeyTerm convert(KeyTermDTO dto) {
        return KeyTerm.builder().id(dto.getId()).name(dto.getName()).description(dto.getDescription()).build();
    }
}
