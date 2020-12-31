package ru.dpopkov.knowthenics.model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    private final Answer answer = new Answer();

    @Test
    void testAddKeyTerm() {
        KeyTerm jvm = KeyTerm.builder().name("JVM").build();
        KeyTerm jdk = KeyTerm.builder().name("JDK").build();
        answer.addKeyTerm(jvm);
        answer.addKeyTerm(jdk);
        Set<KeyTerm> keyTerms = answer.getKeyTerms();
        assertEquals(2, keyTerms.size());
        assertTrue(keyTerms.contains(jvm));
        assertTrue(keyTerms.contains(jdk));
    }
}
