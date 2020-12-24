package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.services.AnswerService;

@Service
@Profile({"default", "map-service"})
public class AnswerMapService extends AbstractMapService<Answer> implements AnswerService {
}
