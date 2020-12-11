package ru.dpopkov.knowthenics.services.map;

import ru.dpopkov.knowthenics.model.BaseEntity;
import ru.dpopkov.knowthenics.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity> implements CrudService<T, Long> {

    private final Map<Long, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public T findById(Long id) {
        return map.get(id);
    }

    @Override
    public T save(T entity) {
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
