package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Continent;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class ContinentEJB implements RemoteContinentEJB {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Continent> findContinents() {
        return em.createQuery("select t from Continent t", Continent.class).getResultList();
    }

    @Override
    public Continent findContinentById(Long id) {
        return em.find(Continent.class, id);
    }

    @Override
    public Continent createContinent(Continent continent) {
        em.persist(continent);
        return continent;
    }

    @Override
    public void deleteContinent(Continent continent) {
        em.remove(updateContinent(continent));
    }

    @Override
    public Continent updateContinent(Continent continent) {
        return em.merge(continent);
    }

}
