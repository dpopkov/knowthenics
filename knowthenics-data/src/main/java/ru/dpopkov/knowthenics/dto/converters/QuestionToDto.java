package ru.dpopkov.knowthenics.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.dto.QuestionDTO;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

@Component
public class QuestionToDto implements Converter<Question, QuestionDTO> {

    private final CategoryToDto categoryToDto;
    private final AnswerToDto answerToDto;
    private final KeyTermToDto keyTermToDto;

    public QuestionToDto(CategoryToDto categoryToDto, AnswerToDto answerToDto, KeyTermToDto keyTermToDto) {
        this.categoryToDto = categoryToDto;
        this.answerToDto = answerToDto;
        this.keyTermToDto = keyTermToDto;
    }

    @Override
    public QuestionDTO convert(Question question) {
        QuestionDTO dto = QuestionDTO.builder()
                .id(question.getId())
                .wordingEn(question.getWordingEn())
                .wordingRu(question.getWordingRu())
                .shortAnswerEn(question.getShortAnswerEn())
                .shortAnswerRu(question.getShortAnswerRu())
                .comment(question.getComment())
                .interviewUsageCount(question.getInterviewUsageCount())
                .build();
        if (question.getCategory() != null) {
            dto.setCategory(categoryToDto.convert(question.getCategory()));
        }
        if (question.getPreferredAnswer() != null) {
            dto.setPreferredAnswer(answerToDto.convert(question.getPreferredAnswer()));
        }
        Set<Answer> answers = question.getAnswers();
        if (answers != null && answers.size() != 0) {
            Set<AnswerDTO> answerDTOs = dto.getAnswers();
            answers.forEach(a -> answerDTOs.add(answerToDto.convert(a)));
        }
        Set<KeyTerm> keyTerms = question.getKeyTerms();
        if (keyTerms != null && keyTerms.size() != 0) {
            Set<KeyTermDTO> keyTermDTOS = dto.getKeyTerms();
            keyTerms.forEach(kt -> keyTermDTOS.add(keyTermToDto.convert(kt)));
        }
        return dto;
    }
}
