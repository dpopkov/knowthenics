package ru.dpopkov.knowthenics.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.model.QCollection;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.repositories.DeckRepository;
import ru.dpopkov.knowthenics.services.QCollectionService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeckSDJpaServiceTest {

    @Mock
    DeckRepository deckRepository;
    @Mock
    QCollectionService qCollectionService;

    DeckSDJpaService service;

    @BeforeEach
    void setup() {
        service = new DeckSDJpaService(deckRepository, qCollectionService);
    }

    @Test
    void testPopulateFrom() {
        Question jvm = Question.builder().id(1L).wordingEn("JVM").build();
        Question jre = Question.builder().id(2L).wordingEn("JRE").build();
        Question jdk = Question.builder().id(3L).wordingEn("JDK").build();
        final Long coreCollectionId = 1L;
        final Long baseCollectionId = 2L;
        QCollection coreCollection = new QCollection(coreCollectionId, "Core", "", Set.of(jvm, jre));
        QCollection baseCollection = new QCollection(baseCollectionId, "Base", "", Set.of(jre, jdk));
        when(qCollectionService.findById(coreCollectionId)).thenReturn(coreCollection);
        when(qCollectionService.findById(baseCollectionId)).thenReturn(baseCollection);

        Deck deck = new Deck();
        service.populateFrom(deck, coreCollectionId);
        assertEquals(2, deck.getFlashCards().size());

        service.populateFrom(deck, baseCollectionId);
        assertEquals(3, deck.getFlashCards().size());
    }
}
