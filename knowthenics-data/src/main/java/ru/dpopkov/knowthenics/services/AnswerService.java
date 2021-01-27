package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Answer;

import java.util.Set;

public interface AnswerService extends CrudService<Answer, Long> {

    Set<Answer> findAllByWordingEnLike(String searchString);
}
