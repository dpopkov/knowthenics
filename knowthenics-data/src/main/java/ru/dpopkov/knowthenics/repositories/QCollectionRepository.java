package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.QCollection;

public interface QCollectionRepository extends CrudRepository<QCollection, Long> {
}
