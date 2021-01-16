package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.repositories.QuestionRepository;
import ru.dpopkov.knowthenics.services.QuestionService;

import java.util.Set;

@Service
@Profile("spring-data-jpa")
public class QuestionSDJpaService extends AbstractSDJpaService<Question> implements QuestionService {

    public QuestionSDJpaService(QuestionRepository questionRepository) {
        super(questionRepository);
    }

    @Override
    public Set<Question> findAllByWordingEnLike(String searchPattern) {
        QuestionRepository questionRepository = (QuestionRepository) super.crudRepository;
        return questionRepository.findAllByWordingEnLike(searchPattern);
    }
}
