package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class TripEJB implements RemoteTripEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Trip> findTrips() {
        return em.createQuery("select t from Trip t", Trip.class).getResultList();
    }

    @Override
    public Trip findTripById(Long id) {
        return em.find(Trip.class, id);
    }

    @Override
    public Trip createTrip(Trip trip) {
        em.persist(trip);
        return trip;
    }

    @Override
    public void deleteTrip(Trip trip) {
        em.remove(updateTrip(trip));
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return em.merge(trip);
    }
}
