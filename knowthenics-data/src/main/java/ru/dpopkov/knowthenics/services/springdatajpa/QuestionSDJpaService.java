package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.repositories.QuestionRepository;
import ru.dpopkov.knowthenics.services.QuestionService;

@Service
@Profile("spring-data-jpa")
public class QuestionSDJpaService extends AbstractSDJpaService<Question> implements QuestionService {

    public QuestionSDJpaService(QuestionRepository questionRepository) {
        super(questionRepository);
    }
}
