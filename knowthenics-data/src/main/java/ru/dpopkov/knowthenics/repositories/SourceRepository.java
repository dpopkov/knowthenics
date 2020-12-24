package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.Source;

public interface SourceRepository extends CrudRepository<Source, Long> {
}
