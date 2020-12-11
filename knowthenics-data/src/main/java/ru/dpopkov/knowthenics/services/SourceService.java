package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Source;

import java.util.Set;

public interface SourceService {

    Set<Source> findAll();

    Source findById(Long id);

    void save(Source source);

    void delete(Source source);

    void deleteById(Long id);
}
