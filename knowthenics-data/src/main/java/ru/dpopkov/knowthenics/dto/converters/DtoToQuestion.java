package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.dto.QuestionDTO;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

@Component
public class DtoToQuestion implements Converter<QuestionDTO, Question> {

    private final DtoToCategory dtoToCategory;
    private final DtoToAnswer dtoToAnswer;

    public DtoToQuestion(DtoToCategory dtoToCategory, DtoToAnswer dtoToAnswer) {
        this.dtoToCategory = dtoToCategory;
        this.dtoToAnswer = dtoToAnswer;
    }

    @Override
    public Question convert(QuestionDTO dto) {
        Question question = Question.builder()
                .id(dto.getId())
                .wordingEn(dto.getWordingEn())
                .wordingRu(dto.getWordingRu())
                .shortAnswerEn(dto.getShortAnswerEn())
                .shortAnswerRu(dto.getShortAnswerRu())
                .comment(dto.getComment())
                .interviewUsageCount(dto.getInterviewUsageCount())
                .build();
        if (dto.getCategory() != null) {
            question.setCategory(dtoToCategory.convert(dto.getCategory()));
        }
        AnswerDTO preferredAnswerDto = dto.getPreferredAnswer();
        if (preferredAnswerDto != null) {
            question.setPreferredAnswer(dtoToAnswer.convert(preferredAnswerDto));
        }
        Set<AnswerDTO> dtoAnswers = dto.getAnswers();
        if (dtoAnswers != null && dtoAnswers.size() != 0) {
            dtoAnswers.forEach(da -> question.getAnswers().add(dtoToAnswer.convert(da)));
        }
        return question;
    }
}
