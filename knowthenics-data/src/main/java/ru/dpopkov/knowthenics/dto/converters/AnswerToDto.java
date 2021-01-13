package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.model.*;

import java.util.Set;

@Component
public class AnswerToDto implements Converter<Answer, AnswerDTO> {

    private final EnumToString enumToString;
    private final SourceToDto sourceToDto;
    private final KeyTermToDto keyTermToDto;

    public AnswerToDto(EnumToString enumToString, SourceToDto sourceToDto, KeyTermToDto keyTermToDto) {
        this.enumToString = enumToString;
        this.sourceToDto = sourceToDto;
        this.keyTermToDto = keyTermToDto;
    }

    @Override
    public AnswerDTO convert(Answer answer) {
        AnswerDTO dto = AnswerDTO.builder()
                .id(answer.getId())
                .wordingEn(answer.getWordingEn())
                .wordingRu(answer.getWordingRu())
                .sourceDetails(answer.getSourceDetails())
                .comment(answer.getComment())
                .build();

        Question question = answer.getQuestion();
        dto.setQuestionId(question != null ? question.getId() : null);

        AnswerType type = answer.getAnswerType();
        if (type != null) {
            dto.setAnswerType(enumToString.convert(type));
        }
        Source source = answer.getSource();
        if (source != null) {
            dto.setSource(sourceToDto.convert(source));
        }
        Set<KeyTerm> keyTerms = answer.getKeyTerms();
        if (keyTerms != null && keyTerms.size() != 0) {
            keyTerms.forEach(kt -> dto.getKeyTerms().add(keyTermToDto.convert(kt)));
        }
        return dto;
    }
}
