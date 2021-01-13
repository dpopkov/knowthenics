package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.dto.QuestionDTO;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

@Component
public class DtoToAnswer implements Converter<AnswerDTO, Answer> {
    
    private final StringToAnswerType stringToAnswerType;
    private final DtoToSource dtoToSource;
    private final DtoToKeyTerm dtoToKeyTerm;

    public DtoToAnswer(StringToAnswerType stringToAnswerType, DtoToSource dtoToSource, DtoToKeyTerm dtoToKeyTerm) {
        this.stringToAnswerType = stringToAnswerType;
        this.dtoToSource = dtoToSource;
        this.dtoToKeyTerm = dtoToKeyTerm;
    }

    @Override
    public Answer convert(AnswerDTO dto) {
        Answer answer = Answer.builder()
                .id(dto.getId())
                .wordingEn(dto.getWordingEn())
                .wordingRu(dto.getWordingRu())
                .sourceDetails(dto.getSourceDetails())
                .comment(dto.getComment())
                .build();
        if (dto.getQuestionId() != null) {
            QuestionDTO questionDTO = QuestionDTO.builder().id(dto.getQuestionId()).build();
            Question question = Question.builder().id(questionDTO.getId()).build();
            answer.setQuestion(question);
        }
        if (dto.getAnswerType() != null) {
            answer.setAnswerType(stringToAnswerType.convert(dto.getAnswerType()));
        }
        if (dto.getSource() != null) {
            answer.setSource(dtoToSource.convert(dto.getSource()));
        }
        Set<KeyTermDTO> keyTermDTOs = dto.getKeyTerms();
        if (keyTermDTOs != null && keyTermDTOs.size() != 0) {
            dto.getKeyTerms().forEach(ktd -> answer.getKeyTerms().add(dtoToKeyTerm.convert(ktd)));
        }
        return answer;
    }
}
