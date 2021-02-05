package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.repositories.DeckRepository;
import ru.dpopkov.knowthenics.services.DeckService;

@Service
@Profile("spring-data-jpa")
public class DeckSDJpaService extends AbstractSDJpaService<Deck> implements DeckService {

    public DeckSDJpaService(DeckRepository deckRepository) {
        super(deckRepository);
    }
}
