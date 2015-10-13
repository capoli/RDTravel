package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class TripEJB implements RemoteTripEJB {
    @PersistenceContext
    private EntityManager em;


    //TODO: available seats ook in rekening brengen
    @Override
    public List<Trip> findTripsForCriteria(Long destinationId, Date periodStart, Date periodEnd, Integer numberOfParticipants) {
        Timestamp start = new Timestamp(periodStart.getTime());
        Timestamp end = new Timestamp(periodEnd.getTime());
        String jpqlQuery = "SELECT distinct t FROM Trip t join fetch t.flights WHERE t.destination.id = :destId " +
                "AND t.period.periodStart between :pStart and :pEnd " +
                "AND t.period.periodEnd between :pStart and :pEnd ";
        TypedQuery<Trip> hql = em.createQuery(jpqlQuery, Trip.class)
                .setParameter("destId", destinationId)
                .setParameter("pStart", start)
                .setParameter("pEnd", end);
        List<Trip> trips = new ArrayList<>();
        for (Trip trip : hql.getResultList()) {
            boolean availableFlights = false;
            Trip newTrip = new Trip(trip.getPricePerDay(), trip.getDestination(), trip.getPeriod());
            for (Flight flight : trip.getFlights()) {
                if (flight.getAvailableSeats() >= numberOfParticipants) {
                    newTrip.addFlight(flight);
                    availableFlights = true;
                }
            }
            if (availableFlights) {
                trips.add(trip);
            }
        }
        return trips;
    }

    @Override
    public List<Location> findLocationsWithTrips() {
        return em.createQuery("select distinct t.destination from Trip t order by t.destination.name", Location.class)
                .getResultList();
    }

    @Override
    public List<String> findLocationNamesWithTrips() {
        return em.createQuery("select distinct lower(t.destination.name) from Trip t order by t.destination.name", String.class)
                .getResultList();
    }

    @Override
    public Trip findTripEagerLoaded(Long id) {
        return (Trip) em.createQuery("SELECT distinct t FROM Trip t join fetch t.flights WHERE t.id = :id").setParameter("id", id).getResultList().get(0);
    }
}
