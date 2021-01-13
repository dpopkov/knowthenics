package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.dto.CategoryDTO;
import ru.dpopkov.knowthenics.dto.QuestionDTO;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DtoToQuestionTest {

    DtoToAnswer dtoToAnswer;
    DtoToCategory dtoToCategory;

    @BeforeEach
    void setUp() {
        dtoToCategory = new DtoToCategory();
        dtoToAnswer = new DtoToAnswer(new StringToAnswerType(), new DtoToSource(), new DtoToKeyTerm());
    }

    @Test
    void testConvert() {
        final Long answerId = 40L;
        Set<AnswerDTO> answerDTOs = Set.of(
                AnswerDTO.builder().id(51L).build(),
                AnswerDTO.builder().id(52L).build()
        );

        QuestionDTO dto = QuestionDTO.builder()
                .id(10L)
                .category(CategoryDTO.builder().id(20L).build())
                .wordingEn("en")
                .wordingRu("ru")
                .shortAnswerEn("short-en")
                .shortAnswerRu("short-ru")
                .preferredAnswer(AnswerDTO.builder().id(answerId).build())
                .answers(answerDTOs)
                .comment("comment")
                .interviewUsageCount(123)
                .build();
        DtoToQuestion converter = new DtoToQuestion(dtoToCategory, dtoToAnswer);
        Question question = converter.convert(dto);
        assertNotNull(question);
        assertEquals(10L, question.getId());
        assertEquals(20L, question.getCategory().getId());
        assertEquals("en", question.getWordingEn());
        assertEquals("ru", question.getWordingRu());
        assertEquals("short-en", question.getShortAnswerEn());
        assertEquals("short-ru", question.getShortAnswerRu());
        assertEquals(answerId, question.getPreferredAnswer().getId());
        assertEquals(2, question.getAnswers().size());
        assertEquals("comment", question.getComment());
        assertEquals(123, question.getInterviewUsageCount());
    }
}
