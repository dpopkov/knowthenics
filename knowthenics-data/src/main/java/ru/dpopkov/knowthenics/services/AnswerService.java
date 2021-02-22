package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;

import java.util.Set;

public interface AnswerService extends CrudService<Answer, Long> {

    Set<Answer> findAllByWordingLike(String searchString);

    Set<Answer> findByKeyTerm(KeyTerm keyTerm);
}
