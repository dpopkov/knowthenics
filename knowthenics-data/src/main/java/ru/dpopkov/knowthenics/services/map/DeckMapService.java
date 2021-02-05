package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Deck;
import ru.dpopkov.knowthenics.services.DeckService;

@Service
@Profile({"default", "map-service"})
public class DeckMapService extends AbstractMapService<Deck> implements DeckService {
}
