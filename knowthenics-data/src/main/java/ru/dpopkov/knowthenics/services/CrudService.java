package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.BaseEntity;

import java.util.Set;

public interface CrudService<T extends BaseEntity, ID> {

    Set<T> findAll();

    T findById(ID id);

    void save(T entity);

    void delete(T entity);

    void deleteById(ID id);
}
