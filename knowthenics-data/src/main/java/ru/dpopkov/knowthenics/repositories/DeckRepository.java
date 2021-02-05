package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Deck;

public interface DeckRepository extends CrudRepository<Deck, Long> {
}
