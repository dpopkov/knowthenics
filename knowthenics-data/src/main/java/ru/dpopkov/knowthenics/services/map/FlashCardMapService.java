package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.FlashCard;
import ru.dpopkov.knowthenics.services.FlashCardService;

@Service
@Profile({"default", "map-service"})
public class FlashCardMapService extends AbstractMapService<FlashCard> implements FlashCardService {
}
