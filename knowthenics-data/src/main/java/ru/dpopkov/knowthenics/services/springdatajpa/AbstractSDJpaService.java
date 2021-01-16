package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import ru.dpopkov.knowthenics.model.BaseEntity;
import ru.dpopkov.knowthenics.services.CrudService;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSDJpaService<T extends BaseEntity> implements CrudService<T, Long> {

    protected final CrudRepository<T, Long> crudRepository;

    public AbstractSDJpaService(CrudRepository<T, Long> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Set<T> findAll() {
        Set<T> set = new HashSet<>();
        crudRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public T findById(Long id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        return crudRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        crudRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }
}
