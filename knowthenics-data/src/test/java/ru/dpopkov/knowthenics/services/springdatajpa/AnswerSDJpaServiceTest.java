package ru.dpopkov.knowthenics.services.springdatajpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dpopkov.knowthenics.model.Answer;
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
}
