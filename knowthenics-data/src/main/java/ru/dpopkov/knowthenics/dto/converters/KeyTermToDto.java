package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.model.KeyTerm;

@Component
public class KeyTermToDto implements Converter<KeyTerm, KeyTermDTO> {
    @Override
    public KeyTermDTO convert(KeyTerm keyTerm) {
        return new KeyTermDTO(keyTerm.getId(), keyTerm.getName(), keyTerm.getDescription());
    }
}
