package ru.dpopkov.knowthenics.services.map;

import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.services.AnswerService;

@Service
public class AnswerServiceMap extends AbstractMapService<Answer> implements AnswerService {
}
