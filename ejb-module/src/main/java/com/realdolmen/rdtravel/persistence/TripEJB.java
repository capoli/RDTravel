package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        TypedQuery query = em.createQuery("select t from Trip t where t.destination.id = :destId", Trip.class);
        query.setParameter("destId", destinationId);
//        query.setParameter("pStart", periodStart);
//        query.setParameter("pEnd", periodEnd);
        return query.getResultList();
    }
}
