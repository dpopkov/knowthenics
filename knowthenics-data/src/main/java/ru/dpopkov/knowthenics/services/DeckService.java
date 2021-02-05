package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Deck;

public interface DeckService extends CrudService<Deck, Long> {

    void populateFrom(Deck deck, Long collectionId);
}
