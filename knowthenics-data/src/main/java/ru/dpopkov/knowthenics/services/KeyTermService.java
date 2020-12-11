package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.KeyTerm;

import java.util.Set;

public interface KeyTermService {

    Set<KeyTerm> findAll();

    KeyTerm findById(Long id);

    void save(KeyTerm keyTerm);

    void delete(KeyTerm keyTerm);

    void deleteById(Long id);
}
