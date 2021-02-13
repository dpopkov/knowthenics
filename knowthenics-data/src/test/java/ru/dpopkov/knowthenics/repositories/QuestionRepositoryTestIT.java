package ru.dpopkov.knowthenics.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuestionRepositoryTestIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // fixed by adding DataModuleTestConfiguration
    @Autowired
    QuestionRepository questionRepository;

    @Test
    void findAllByWordingEnLike() {
        Question question1 = Question.builder().id(1L).wordingEn("abc_test").build();
        Question question2 = Question.builder().id(2L).wordingEn("not_found").build();
        Question question3 = Question.builder().id(3L).wordingEn("ab_test_c").build();
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        Set<Question> found = questionRepository.findAllByWordingEnLike("%test%");
        assertNotNull(found);
        assertEquals(2, found.size());
        assertTrue(found.contains(question1));
        assertFalse(found.contains(question2));
        assertTrue(found.contains(question3));
    }
}
