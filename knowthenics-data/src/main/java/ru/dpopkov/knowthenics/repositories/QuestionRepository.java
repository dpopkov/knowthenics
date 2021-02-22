package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Category;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Set<Question> findAllByWordingEnLike(String searchString);

    Set<Question> findAllByWordingRuLike(String searchString);

    Set<Question> findByCategory(Category category);
}
