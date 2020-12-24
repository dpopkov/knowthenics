package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QuestionService;

@Service
@Profile({"default", "map-service"})
public class QuestionMapService extends AbstractMapService<Question> implements QuestionService {
}
