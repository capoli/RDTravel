package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<Trip> findTripsForCriteria(Long destinationId, Date periodStart, Date periodEnd) {
//        TypedQuery query = em.createQuery("select t from Trip t " +
//                "where t.destination.id = :destId " +
//                "and t.period.periodStart <= :pStart and t.period.periodEnd >= :pStart " +
//                "and t.period.periodStart <= :pEnd and t.period.periodEnd >= :pEnd " +
//                "order by t.period.periodStart", Trip.class);

        Timestamp start = new Timestamp(periodStart.getTime());
        Timestamp end = new Timestamp(periodEnd.getTime());
        String jpqlQuery = "SELECT t FROM Trip t join fetch t.flights WHERE t.destination.id = :destId " +
                "AND t.period.periodStart between :pStart and :pEnd " +
                "AND t.period.periodEnd between :pStart and :pEnd";
        TypedQuery<Trip> hql = em.createQuery(jpqlQuery, Trip.class)
                .setParameter("destId", destinationId)
                .setParameter("pStart", start)
                .setParameter("pEnd", end);

//        TypedQuery query = em.createQuery("select t from Trip t where t.destination.id = :destId", Trip.class);
//        query.setParameter("destId", destinationId);

//        query.setParameter("pStart", periodStart);
//        query.setParameter("pEnd", periodEnd);
        return hql.getResultList();
    }

    @Override
    public List<Location> findLocationsWithTrips() {
        return em.createQuery("select distinct t.destination from Trip t order by t.destination.name", Location.class)
                .getResultList();
    }
}
