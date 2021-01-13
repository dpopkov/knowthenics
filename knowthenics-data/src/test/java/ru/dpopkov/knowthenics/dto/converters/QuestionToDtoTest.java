package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.QuestionDTO;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuestionToDtoTest {
    CategoryToDto categoryToDto;
    AnswerToDto answerToDto;
    KeyTermToDto keyTermToDto;

    @BeforeEach
    void setUp() {
        categoryToDto = new CategoryToDto();
        keyTermToDto = new KeyTermToDto();
        EnumToString enumToString = new EnumToString();
        answerToDto = new AnswerToDto(enumToString, new SourceToDto(enumToString), new KeyTermToDto());
    }

    @Test
    void testConvert() {
        Answer preferred = Answer.builder().id(30L).build();
        Set<Answer> answers = Set.of(
                preferred,
                Answer.builder().id(31L).build()
        );
        Set<KeyTerm> keyTerms = Set.of(
                KeyTerm.builder().id(41L).build(),
                KeyTerm.builder().id(42L).build()
        );
        Question question = Question.builder()
                .id(10L)
                .category(Category.builder().id(20L).build())
                .wordingEn("en").wordingRu("ru")
                .shortAnswerEn("answer-en").shortAnswerRu("answer-ru")
                .preferredAnswer(preferred)
                .answers(answers)
                .comment("comment")
                .keyTerms(keyTerms)
                .interviewUsageCount(123)
                .build();
        QuestionToDto converter = new QuestionToDto(categoryToDto, answerToDto, keyTermToDto);
        QuestionDTO dto = converter.convert(question);
        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals(20L, dto.getCategory().getId());
        assertEquals("en", dto.getWordingEn());
        assertEquals("ru", dto.getWordingRu());
        assertEquals("answer-en", dto.getShortAnswerEn());
        assertEquals("answer-ru", dto.getShortAnswerRu());
        assertEquals(30L, dto.getPreferredAnswer().getId());
        assertEquals(2, dto.getAnswers().size());
        assertEquals("comment", dto.getComment());
        assertEquals(2, dto.getKeyTerms().size());
        assertEquals(123, dto.getInterviewUsageCount());
    }
}
