package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.FlashCard;

public interface FlashCardRepository extends CrudRepository<FlashCard, Long> {
}
