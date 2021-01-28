package ru.dpopkov.knowthenics.services.springdatajpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.repositories.AnswerRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerSDJpaServiceTest {

    @Mock
    AnswerRepository answerRepository;
    @InjectMocks
    AnswerSDJpaService service;

    @Test
    void testFindAllByWordingEnLike() {
        Set<Answer> set = Set.of(
                Answer.builder().id(1L).build(),
                Answer.builder().id(2L).build());
        when(answerRepository.findAllByWordingEnLike(anyString())).thenReturn(set);
        Set<Answer> found = service.findAllByWordingEnLike("test");
        assertNotNull(found);
        assertEquals(2, found.size());
    }

    @Test
    void testFindAllByKeyTerm() {
        KeyTerm kt1 = KeyTerm.builder().id(1L).name("term1").build();
        KeyTerm kt2 = KeyTerm.builder().id(2L).name("term2").build();
        KeyTerm kt3 = KeyTerm.builder().id(3L).name("term3").build();
        KeyTerm kt4 = KeyTerm.builder().id(4L).name("term4").build();
        Answer answer12 = Answer.builder().id(12L).build();
        answer12.addKeyTerm(kt1);
        answer12.addKeyTerm(kt2);
        Answer answer1 = Answer.builder().id(1L).build();
        answer1.addKeyTerm(kt1);
        Answer answer3 = Answer.builder().id(3L).build();
        answer3.addKeyTerm(kt3);
        Set<Answer> allAnswers = Set.of(answer12 ,answer1, answer3);
        when(answerRepository.findAll()).thenReturn(allAnswers);

        Set<Answer> found1 = service.findByKeyTerm(kt1);
        assertNotNull(found1);
        assertEquals(2, found1.size());

        Set<Answer> found2 = service.findByKeyTerm(kt2);
        assertNotNull(found2);
        assertEquals(1, found2.size());

        Set<Answer> found3 = service.findByKeyTerm(kt3);
        assertNotNull(found3);
        assertEquals(1, found3.size());

        Set<Answer> found4 = service.findByKeyTerm(kt4);
        assertNotNull(found4);
        assertEquals(0, found4.size());
    }
}
