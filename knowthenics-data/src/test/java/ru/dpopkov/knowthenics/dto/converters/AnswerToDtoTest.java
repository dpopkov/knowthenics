package ru.dpopkov.knowthenics.dto.converters;

import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.dto.AnswerDTO;
import ru.dpopkov.knowthenics.model.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnswerToDtoTest {

    @Test
    void testConvert() {
        AnswerToDto converter = new AnswerToDto(new EnumToString(),
                new SourceToDto(new EnumToString()), new KeyTermToDto());
        Set<KeyTerm> keyTerms = Set.of(
                KeyTerm.builder().id(21L).build(),
                KeyTerm.builder().id(22L).build()
        );
        Answer answer = Answer.builder().id(11L)
                .question(Question.builder().id(12L).build())
                .wordingEn("en").wordingRu("ru")
                .answerType(AnswerType.ORIGINAL)
                .source(Source.builder().id(1234L).build())
                .sourceDetails("details")
                .comment("comment")
                .keyTerms(keyTerms)
                .build();
        AnswerDTO dto = converter.convert(answer);
        assertNotNull(dto);
        assertEquals(11L, dto.getId());
        assertEquals(12L, dto.getQuestionId());
        assertEquals("en", dto.getWordingEn());
        assertEquals("ru", dto.getWordingRu());
        assertEquals("original", dto.getAnswerType());
        assertEquals(1234L, dto.getSource().getId());
        assertEquals("details", dto.getSourceDetails());
        assertEquals("comment", dto.getComment());
        assertEquals(2, dto.getKeyTerms().size());
    }
}
