package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
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
    public Question findById(Long id) {
        try {
            return super.findById(id);
        } catch (NotFoundInRepositoryException ex) {
            throw new NotFoundInRepositoryException("Question not found for ID " + id, ex);
        }
    }

    @Override
    public Set<Question> findAllByWordingEnLike(String searchPattern) {
        QuestionRepository questionRepository = (QuestionRepository) super.crudRepository;
        return questionRepository.findAllByWordingEnLike(searchPattern);
    }
}
