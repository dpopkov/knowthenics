package ru.dpopkov.knowthenics.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.dpopkov.knowthenics.model.FlashCard;
import ru.dpopkov.knowthenics.model.StudySession;
import ru.dpopkov.knowthenics.repositories.FlashCardRepository;
import ru.dpopkov.knowthenics.repositories.StudySessionRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudySessionSDJpaServiceIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // fixed by DataModuleConfiguration
    @Autowired
    StudySessionRepository studySessionRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // fixed by DataModuleConfiguration
    @Autowired
    FlashCardRepository flashCardRepository;

    StudySessionSDJpaService service;

    @BeforeEach
    public void setup() {
        service = new StudySessionSDJpaService(studySessionRepository);
    }

    @Test
    void testSaveStudySession() {
        assertNotNull(studySessionRepository);
        assertNotNull(service);

        FlashCard card1 = new FlashCard();
        FlashCard card2 = new FlashCard();
        flashCardRepository.save(card1);
        flashCardRepository.save(card2);

        StudySession study1 = new StudySession();
        study1.addCard(card1);
        study1.addCard(card2);
        service.save(study1);
        long countStudies;
        long countCards;
        countStudies = service.count();
        assertEquals(1L, countStudies);
        countCards = flashCardRepository.count();
        assertEquals(2L, countCards);

        StudySession study2 = new StudySession();
        study2.addCard(card1);
        study2.addCard(card2);
        service.save(study2);
        countStudies = service.count();
        assertEquals(2L, countStudies);
        countCards = flashCardRepository.count();
        assertEquals(2L, countCards);
    }
}
