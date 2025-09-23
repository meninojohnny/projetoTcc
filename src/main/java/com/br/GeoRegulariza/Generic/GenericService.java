/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.Generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author johnny
 */

@Data
public class GenericService<T> {
    
    private static final Logger LOGGER = Logger.getLogger(GenericService.class.getName());
    
    private final Class<T> entityClass;
    
    @PersistenceContext
    private EntityManager entityManager;

    public GenericService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void save(T entity) {
        if (entity instanceof GenericEntity) {
            GenericEntity generic = (GenericEntity) entity;
            if (generic.getCreationDate() == null) {
                generic.setCreationDate(new Date());
            }
        }
        entityManager.persist(entity);
        entityManager.flush();
    }

    public void update(T entity) {
        if (entity instanceof GenericEntity) {
            GenericEntity generic = (GenericEntity) entity;
            generic.setUpdateDate(new Date());
        }

        entityManager.merge(entity);
        entityManager.flush();
    }
    
    public T updateWithReturn(T entity) {
        try {
            Method setUpdateDateMethod = entityClass.getSuperclass().getDeclaredMethod("setUpdateDate", Date.class);
            setUpdateDateMethod.setAccessible(true);
            setUpdateDateMethod.invoke(entity, new Date());
            return entity;
        } catch (NoSuchMethodException e) {
            LOGGER.log(Level.WARNING, "Método setUpdateDate não encontrado: {0}", e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "Erro ao definir a data de atualização", e);
        }

        entityManager.merge(entity);
        entityManager.flush();
        
        return null;
    }

    public T find(Long entityID) {
        T entity = entityManager.find(entityClass, entityID);
        if (entity != null) {
            entityManager.refresh(entity);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.active = true", entityClass)
                .getResultList();
    }

    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        entityManager.flush();
    }
    
}
