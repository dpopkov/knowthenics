package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Answer;

import java.util.Set;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    Set<Answer> findAllByWordingEnLike(String searchString);
}
