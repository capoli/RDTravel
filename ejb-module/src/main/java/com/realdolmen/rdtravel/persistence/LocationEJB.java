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
    public List<Location> findLocationsWithTrips() {
        return em.createQuery("select l from Location l where l.trips.size > 0 order by l.name", Location.class).getResultList();
    }
}
