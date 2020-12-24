package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, Long> {
}
