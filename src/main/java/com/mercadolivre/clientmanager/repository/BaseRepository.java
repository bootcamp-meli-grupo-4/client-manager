package com.mercadolivre.clientmanager.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolivre.clientmanager.model.Entity;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.lang.model.element.NestingKind;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class BaseRepository<T extends Entity> implements Repository<T> {

    private Class<T> clazz;
    private Map<Integer, T> entities = new HashMap<>();
    private Integer nextId = 1;
    private String jsonName;

    public BaseRepository() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
         this.jsonName = clazz.getSimpleName();
    }

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

    @PostConstruct
    private void loadDataBase() throws IOException {
        File entityFile = getDabaBase();
        if(entityFile.length() != 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, clazz);
            this.entities = mapper.readValue(entityFile, mapType);
            this.nextId = this.entities.size();
        }
    }

    @PreDestroy
    private void saveToDataBase() throws IOException {
        File pedidoDataBase = getDabaBase();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(pedidoDataBase, this.entities);
    }

    private File getDabaBase() throws FileNotFoundException {
        String classPath = String.format("classpath:static/%s.json", this.jsonName.toLowerCase());
        return ResourceUtils.getFile(classPath);
    }
}
