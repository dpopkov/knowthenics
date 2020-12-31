package ru.dpopkov.knowthenics.model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private final Question question = new Question();

    @Test
    void testAddKeyTerm() {
        KeyTerm jvm = KeyTerm.builder().name("JVM").build();
        KeyTerm jdk = KeyTerm.builder().name("JDK").build();
        question.addKeyTerm(jvm);
        question.addKeyTerm(jdk);
        Set<KeyTerm> keyTerms = question.getKeyTerms();
        assertEquals(2, keyTerms.size());
        assertTrue(keyTerms.contains(jvm));
        assertTrue(keyTerms.contains(jdk));
    }
}
