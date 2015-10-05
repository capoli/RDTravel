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
    public List<Location> findLocations() {
        return em.createQuery("select t from Location t", Location.class).getResultList();
    }

    @Override
    public Location findLocationById(Long id) {
        return em.find(Location.class, id);
    }

    @Override
    public Location createLocation(Location location) {
        em.persist(location);
        return location;
    }

    @Override
    public void deleteLocation(Location location) {
        em.remove(updateLocation(location));
    }

    @Override
    public Location updateLocation(Location location) {
        return em.merge(location);
    }

}
