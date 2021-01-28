package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.services.QuestionService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map-service"})
public class QuestionMapService extends AbstractMapService<Question> implements QuestionService {
    @Override
    public Set<Question> findAllByWordingEnLike(String searchPattern) {
        return super.findAll().stream()
                .filter(question -> question.getWordingEn() != null && question.getWordingEn().contains(searchPattern))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Question> findByKeyTerm(KeyTerm keyTerm) {
        // todo: implement
        throw new RuntimeException("Not Implemented yet");
    }
}
