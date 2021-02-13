package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.exceptions.data.NotFoundInRepositoryException;
import ru.dpopkov.knowthenics.model.BaseEntity;

import java.util.Set;

public interface CrudService<T extends BaseEntity, ID> {

    Set<T> findAll();

    /**
     * Finds entity by id.
     * @param id id of the entity
     * @return found entity
     * @throws NotFoundInRepositoryException if cannot find entity
     */
    T findById(ID id);

    T save(T entity);

    void delete(T entity);

    void deleteById(ID id);

    long count();
}
