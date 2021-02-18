package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.model.FlashCard;
import ru.dpopkov.knowthenics.model.QCollection;
import ru.dpopkov.knowthenics.model.Question;
import ru.dpopkov.knowthenics.repositories.DeckRepository;
import ru.dpopkov.knowthenics.services.DeckService;
import ru.dpopkov.knowthenics.services.QCollectionService;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class DeckSDJpaService extends AbstractSDJpaService<Deck> implements DeckService {

    private final QCollectionService qCollectionService;

    public DeckSDJpaService(DeckRepository deckRepository, QCollectionService qCollectionService) {
        super(deckRepository);
        this.qCollectionService = qCollectionService;
    }

    @Override
    public void populateFrom(Deck deck, Long collectionId) {
        QCollection qCollection = qCollectionService.findById(collectionId);
        for (Question question : qCollection.getQuestions()) {
            FlashCard flashCard = new FlashCard(question);
            deck.addCard(flashCard);
        }
        DeckRepository deckRepository = (DeckRepository) super.crudRepository;
        deckRepository.save(deck);
    }
}
