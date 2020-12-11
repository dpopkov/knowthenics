package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Answer;

import java.util.Set;

public interface AnswerService {

    Set<Answer> findAll();

    Answer findById(Long id);

    void save(Answer answer);

    void delete(Answer answer);

    void deleteById(Long id);
}
