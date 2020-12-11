package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Question;

import java.util.Set;

public interface QuestionService {

    Set<Question> findAll();

    Question findById(Long id);

    void save(Question question);

    void delete(Question question);

    void deleteById(Long id);
}
