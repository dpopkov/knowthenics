package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.StudySession;
import ru.dpopkov.knowthenics.repositories.StudySessionRepository;
import ru.dpopkov.knowthenics.services.StudySessionService;

@Service
@Profile({"spring-data-jpa", "dev"})
public class StudySessionSDJpaService extends AbstractSDJpaService<StudySession> implements StudySessionService {

    public StudySessionSDJpaService(StudySessionRepository studySessionRepository) {
        super(studySessionRepository);
    }
}
