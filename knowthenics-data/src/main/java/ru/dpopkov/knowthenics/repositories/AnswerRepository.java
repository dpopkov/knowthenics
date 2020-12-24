package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
