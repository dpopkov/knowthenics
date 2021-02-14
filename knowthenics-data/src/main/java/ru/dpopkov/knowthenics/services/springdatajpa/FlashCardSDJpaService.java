package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.FlashCard;
import ru.dpopkov.knowthenics.repositories.FlashCardRepository;
import ru.dpopkov.knowthenics.services.FlashCardService;

@Service
@Profile({"spring-data-jpa", "dev"})
public class FlashCardSDJpaService extends AbstractSDJpaService<FlashCard> implements FlashCardService {

    public FlashCardSDJpaService(FlashCardRepository flashCardRepository) {
        super(flashCardRepository);
    }
}
