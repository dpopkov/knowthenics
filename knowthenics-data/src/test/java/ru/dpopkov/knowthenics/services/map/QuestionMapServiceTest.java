package ru.dpopkov.knowthenics.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuestionMapServiceTest {
    public static final long ID = 1L;

    private QuestionMapService service;
    private Question question;

    @BeforeEach
    void setUp() {
        service = new QuestionMapService();
        question = Question.builder().id(ID).build();
        service.save(question);
    }

    @Test
    void testFindAll() {
        Question q2 = Question.builder().id(2L).build();
        service.save(q2);
        Set<Question> all = service.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(question));
        assertTrue(all.contains(q2));
    }

    @Test
    void testFindById() {
        Question found = service.findById(ID);
        assertNotNull(found);
        assertEquals(ID, found.getId());
    }

    @Test
    void testSaveNoId() {
        Question q2 = Question.builder().wordingEn("test").build();
        Question saved = service.save(q2);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void testDelete() {
        service.delete(question);
        assertNull(service.findById(question.getId()));
        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void testDeleteById() {
        service.deleteById(ID);
        assertNull(service.findById(ID));
        assertTrue(service.findAll().isEmpty());
    }
}
