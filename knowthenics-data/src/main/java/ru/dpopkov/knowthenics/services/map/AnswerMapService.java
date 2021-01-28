package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Answer;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.AnswerService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map-service"})
public class AnswerMapService extends AbstractMapService<Answer> implements AnswerService {

    @Override
    public Set<Answer> findAllByWordingEnLike(String searchString) {
        return super.findAll().stream()
                .filter(a -> a.getWordingEn() != null && a.getWordingEn().contains(searchString))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Answer> findByKeyTerm(KeyTerm keyTerm) {
        // todo: implement
        throw new RuntimeException("Not implemented yet");
    }
}
