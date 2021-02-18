package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.repositories.AnswerRepository;
import ru.dpopkov.knowthenics.services.AnswerService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class AnswerSDJpaService extends AbstractSDJpaService<Answer> implements AnswerService {

    public AnswerSDJpaService(AnswerRepository answerRepository) {
        super(answerRepository);
    }

    @Override
    public Answer findById(Long id) {
        try {
            return super.findById(id);
        } catch (NotFoundInRepositoryException ex) {
            throw new NotFoundInRepositoryException("Answer not found for ID " + id, ex);
        }
    }

    @Override
    public Set<Answer> findAllByWordingEnLike(String searchString) {
        AnswerRepository answerRepository = (AnswerRepository) super.crudRepository;
        return answerRepository.findAllByWordingEnLike(searchString);
    }

    @Override
    public Set<Answer> findByKeyTerm(KeyTerm keyTerm) {
        Set<Answer> result = new HashSet<>();
        findAll().forEach(a -> {
            if (a.getKeyTerms().contains(keyTerm)) {
                result.add(a);
            }
        });
        return result;
    }
}
