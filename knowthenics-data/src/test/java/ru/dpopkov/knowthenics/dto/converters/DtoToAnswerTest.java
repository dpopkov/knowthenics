package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.dto.KeyTermDTO;
import ru.dpopkov.knowthenics.dto.SourceDTO;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.AnswerType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DtoToAnswerTest {

    StringToAnswerType stringToAnswerType;
    DtoToSource dtoToSource;
    DtoToKeyTerm dtoToKeyTerm;

    @BeforeEach
    void setUp() {
        stringToAnswerType = new StringToAnswerType();
        dtoToSource = new DtoToSource();
        dtoToKeyTerm = new DtoToKeyTerm();
    }

    @Test
    void testConvert() {
        final Long answerId = 10L;
        final Long questionId = 20L;
        Set<KeyTermDTO> keyTermDTOs = Set.of(
                KeyTermDTO.builder().id(51L).build(),
                KeyTermDTO.builder().id(52L).build()
        );
        AnswerDTO dto = AnswerDTO.builder()
                .id(answerId)
                .questionId(questionId)
                .wordingEn("en").wordingRu("ru")
                .answerType("original")
                .source(SourceDTO.builder().id(33L).sourceType("book").build())
                .sourceDetails("source-details")
                .comment("comment")
                .keyTerms(keyTermDTOs)
                .build();
        DtoToAnswer converter = new DtoToAnswer(stringToAnswerType, dtoToSource, dtoToKeyTerm);
        Answer answer = converter.convert(dto);
        assertNotNull(answer);
        assertEquals(answerId, answer.getId());
        assertEquals(questionId, answer.getQuestion().getId());
        assertEquals("en", answer.getWordingEn());
        assertEquals("ru", answer.getWordingRu());
        assertEquals(AnswerType.ORIGINAL, answer.getAnswerType());
        assertEquals(33L, answer.getSource().getId());
        assertEquals("source-details", answer.getSourceDetails());
        assertEquals("comment", answer.getComment());
        assertEquals(2, answer.getKeyTerms().size());
    }
}
