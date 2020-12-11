package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Topic;

import java.util.Set;

public interface TopicService {

    Set<Topic> findAll();

    Topic findById(Long id);

    void save(Topic topic);

    void delete(Topic topic);

    void deleteById(Long id);
}
