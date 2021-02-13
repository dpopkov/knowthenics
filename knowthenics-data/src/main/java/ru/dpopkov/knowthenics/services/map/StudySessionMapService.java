package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.StudySession;
import ru.dpopkov.knowthenics.services.StudySessionService;

import java.util.Set;

@Service
@Profile({"default", "map-service"})
public class StudySessionMapService extends AbstractMapService<StudySession> implements StudySessionService {
    @Override
    public Set<StudySession> findAll() {
        // todo: implement
        return null;
    }

    @Override
    public StudySession findById(Long aLong) {
        // todo: implement
        return null;
    }

    @Override
    public StudySession save(StudySession entity) {
        // todo: implement
        return null;
    }

    @Override
    public void delete(StudySession entity) {
        // todo: implement
    }

    @Override
    public void deleteById(Long aLong) {
        // todo: implement
    }
}
