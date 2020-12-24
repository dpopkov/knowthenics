package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Topic;
import ru.dpopkov.knowthenics.services.TopicService;

@Service
@Profile({"default", "map-service"})
public class TopicMapService extends AbstractMapService<Topic> implements TopicService {
}
