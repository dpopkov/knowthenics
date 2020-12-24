package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
