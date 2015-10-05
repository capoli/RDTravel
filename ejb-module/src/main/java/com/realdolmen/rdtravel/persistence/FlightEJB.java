package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class FlightEJB implements RemoteFlightEJB{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Flight> findFlights() {
        return em.createQuery("select t from Flight t", Flight.class).getResultList();
    }

    @Override
    public Flight findFlightById(Long id) {
        return em.find(Flight.class, id);
    }

    @Override
    public Flight createFlight(Flight flight) {
        em.persist(flight);
        return flight;
    }

    @Override
    public void deleteFlight(Flight flight) {
        em.remove(updateFlight(flight));
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return em.merge(flight);
    }

}
