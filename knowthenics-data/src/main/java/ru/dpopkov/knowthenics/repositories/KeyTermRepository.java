package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.KeyTerm;

public interface KeyTermRepository extends CrudRepository<KeyTerm, Long> {
}
