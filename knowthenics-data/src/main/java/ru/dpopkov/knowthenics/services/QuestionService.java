package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

public interface QuestionService extends CrudService<Question, Long> {

    Set<Question> findAllByWordingEnLike(String searchPattern);

    Set<Question> findByKeyTerm(KeyTerm keyTerm);
}
