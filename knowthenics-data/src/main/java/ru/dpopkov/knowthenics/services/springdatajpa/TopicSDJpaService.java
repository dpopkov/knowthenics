package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Topic;
import ru.dpopkov.knowthenics.repositories.TopicRepository;
import ru.dpopkov.knowthenics.services.TopicService;

@Service
@Profile("spring-data-jpa")
public class TopicSDJpaService extends AbstractSDJpaService<Topic> implements TopicService {

    public TopicSDJpaService(TopicRepository topicRepository) {
        super(topicRepository);
    }
}
