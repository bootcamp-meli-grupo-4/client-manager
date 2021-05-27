package com.mercadolivre.clientmanager.repository;

import com.mercadolivre.clientmanager.model.Entity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<T extends Entity> implements Repository<T> {
    private final Map<Integer, T> entities = new HashMap<>();
    private Integer nextId = 1;

    public T find(Integer id){
        return this.entities.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(this.entities.values());
    }

    public Integer save(T entity){
        Integer id = getNextId();
        entity.setId(id);
        this.entities.put (id, entity);
        return id;
    }

    public void update(T newEntity){
        T oldEntity = entities.get(newEntity.getId());
        BeanUtils.copyProperties(newEntity,oldEntity);
        entities.put(oldEntity.getId(), oldEntity);
    }

    public void delete(Integer id){
        entities.remove(id);
    }

    private Integer getNextId(){
        return nextId++;
    }
}
