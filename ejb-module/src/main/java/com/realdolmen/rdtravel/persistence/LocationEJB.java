package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class LocationEJB implements RemoteLocationEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Location> findAllLocations() {
        return em.createQuery("select l from Location l", Location.class).getResultList();
    }

}
