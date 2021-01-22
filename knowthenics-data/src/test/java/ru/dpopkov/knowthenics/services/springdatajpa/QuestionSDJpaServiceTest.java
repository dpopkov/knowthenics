package ru.dpopkov.knowthenics.services.springdatajpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionSDJpaServiceTest {

    @Mock
    private QuestionRepository repository;
    @InjectMocks
    private QuestionSDJpaService service;

    private final Long id1 = 10L;
    private final Question question1 = Question.builder().id(id1).build();
    private final Question question2 = Question.builder().id(11L).build();

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(question1, question2));

        Set<Question> all = service.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testFindById() {
        when(repository.findById(id1)).thenReturn(Optional.of(question1));
        assertEquals(id1, service.findById(id1).getId());
    }

    @Test
    void testFindByIdNotExisting() {
        when(repository.findById(id1)).thenReturn(Optional.empty());
        assertThrows(NotFoundInRepositoryException.class, () -> service.findById(id1));
        verify(repository).findById(id1);
    }

    @Test
    void testSave() {
        when(repository.save(question1)).thenReturn(question1);
        Question saved = service.save(question1);
        assertNotNull(saved);
        verify(repository).save(question1);
    }

    @Test
    void testDelete() {
        service.delete(question1);
        verify(repository).delete(question1);
    }

    @Test
    void testDeleteById() {
        service.deleteById(id1);
        verify(repository).deleteById(id1);
    }

    @Test
    void findAllByWordingEnLike() {
        Set<Question> set = Set.of(
                Question.builder().id(103L).wordingEn("_test1_").build(),
                Question.builder().id(103L).wordingEn("_test2_").build()
        );
        String searchString = "%test1%";
        when(repository.findAllByWordingEnLike(searchString)).thenReturn(set);

        Set<Question> found = service.findAllByWordingEnLike(searchString);
        assertNotNull(found);
        assertEquals(2, found.size());
        verify(repository).findAllByWordingEnLike(searchString);
    }
}
