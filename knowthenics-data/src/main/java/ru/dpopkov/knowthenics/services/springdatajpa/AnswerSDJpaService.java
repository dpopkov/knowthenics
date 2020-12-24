package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.repositories.AnswerRepository;
import ru.dpopkov.knowthenics.services.AnswerService;

@Service
@Profile("spring-data-jpa")
public class AnswerSDJpaService extends AbstractSDJpaService<Answer> implements AnswerService {

    public AnswerSDJpaService(AnswerRepository answerRepository) {
        super(answerRepository);
    }
}
