package ru.dpopkov.knowthenics.services.map;

import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QuestionService;

@Service
public class QuestionServiceMap extends AbstractMapService<Question> implements QuestionService {
}
