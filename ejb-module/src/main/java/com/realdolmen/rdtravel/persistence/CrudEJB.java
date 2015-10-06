package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.AbstractEntity;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class CrudEJB implements CrudEJBService {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<AbstractEntity> findAll(Class type) {
        return em.createQuery(String.format("select t from %s t", type.getSimpleName())).getResultList();
    }

    @Override
    public AbstractEntity findById(Class type, Long id) {
        return (AbstractEntity) em.find(type, id);
    }

    @Override
    public AbstractEntity create(AbstractEntity entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    @Override
    public void deleteById(Class type, Long id) {
        Object ref = em.getReference(type, id);
        em.remove(ref);
    }

    @Override
    public AbstractEntity update(AbstractEntity entity) {
        return em.merge(entity);
    }
}
