package ru.dpopkov.knowthenics.services.map;

import ru.dpopkov.knowthenics.model.BaseEntity;
import ru.dpopkov.knowthenics.services.CrudService;

import java.util.*;

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
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        if (entity.getId() == null) {
            entity.setId(calculateNextId());
        }
        map.put(entity.getId(), entity);
        return entity;
    }

    private Long calculateNextId() {
        if (!map.isEmpty()) {
            return Collections.max(map.keySet()) + 1L;
        }
        return 1L;
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
