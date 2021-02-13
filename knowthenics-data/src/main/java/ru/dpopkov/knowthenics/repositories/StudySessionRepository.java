package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.StudySession;

public interface StudySessionRepository extends CrudRepository<StudySession, Long> {
}
