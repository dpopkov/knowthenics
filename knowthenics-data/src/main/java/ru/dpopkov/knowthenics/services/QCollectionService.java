package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.QCollection;

import java.util.Set;

public interface QCollectionService {

    Set<QCollection> findAll();

    QCollection findById(Long id);

    void save(QCollection qCollection);

    void delete(QCollection qCollection);

    void deleteById(Long id);
}
