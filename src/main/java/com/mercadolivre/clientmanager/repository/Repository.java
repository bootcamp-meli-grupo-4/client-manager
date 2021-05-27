package com.mercadolivre.clientmanager.repository;

import java.util.List;

public interface Repository<T> {
    T find(Integer id);
    List<T> findAll();
    Integer save(T entity);
    void update(T newEntity);
    void delete(Integer id);
}
