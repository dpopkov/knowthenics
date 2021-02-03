package ru.dpopkov.knowthenics.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QCollectionTest {

    @Test
    void testUpdateFrom() {
        Question q1 = Question.builder().id(1L).wordingEn("w1").build();
        Question q2 = Question.builder().id(2L).wordingEn("w2").build();
        Question q3 = Question.builder().id(3L).wordingEn("w3").build();
        QCollection oldCollection = new QCollection();
        oldCollection.addQuestion(q1);
        oldCollection.addQuestion(q2);

        QCollection newCollection = new QCollection();
        newCollection.addQuestion(q2);
        newCollection.addQuestion(q3);

        oldCollection.updateFrom(newCollection);
        assertFalse(oldCollection.contains(q1));
        assertTrue(oldCollection.contains(q2));
        assertTrue(oldCollection.contains(q3));
    }
}
