package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
import com.realdolmen.rdtravel.domain.PaymentType;
import com.realdolmen.rdtravel.domain.Report;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
@LocalBean
public class BookingEJB implements RemoteBookingEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> getPaymentTypesAsString() {
        return Stream.of(PaymentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentType[] getPaymentTypes() {
        return PaymentType.values();
    }

    @Override
    public Report getReportForPeriod(Date periodStart, Date periodEnd) {
        Timestamp start = new Timestamp(periodStart.getTime());
        Timestamp end = new Timestamp(periodEnd.getTime());
        String jpqlQueryAvg = "SELECT AVG(b.totalPrice) FROM Booking b WHERE b.trip.period.periodStart between :pStart and :pEnd " +
                "AND b.trip.period.periodEnd between :pStart and :pEnd";
        String jpqlQueryMin = "SELECT MIN(b.totalPrice) FROM Booking b WHERE b.trip.period.periodStart between :pStart and :pEnd " +
                "AND b.trip.period.periodEnd between :pStart and :pEnd";
        String jpqlQueryMax = "SELECT MAX(b.totalPrice) FROM Booking b WHERE b.trip.period.periodStart between :pStart and :pEnd " +
                "AND b.trip.period.periodEnd between :pStart and :pEnd";
        Query hqlAvg = em.createQuery(jpqlQueryAvg, Double.class)
                .setParameter("pStart", start)
                .setParameter("pEnd", end);
        Query hqlMin = em.createQuery(jpqlQueryMin, Double.class)
                .setParameter("pStart", start)
                .setParameter("pEnd", end);
        Query hqlMax = em.createQuery(jpqlQueryMax, Double.class)
                .setParameter("pStart", start)
                .setParameter("pEnd", end);
        Double avg = (Double) hqlAvg.getSingleResult();
        Double min = (Double) hqlMin.getSingleResult();
        Double max = (Double) hqlMax.getSingleResult();

        return new Report(avg, min, max);
    }

    @Override
    public List<Booking> getBookingsForPeriod(Date periodStart, Date periodEnd) {
        Timestamp start = new Timestamp(periodStart.getTime());
        Timestamp end = new Timestamp(periodEnd.getTime());
        String jpqlQuery = "SELECT distinct b FROM Booking b WHERE b.trip.period.periodStart between :pStart and :pEnd " +
                "AND b.trip.period.periodEnd between :pStart and :pEnd";
        Query hql = em.createQuery(jpqlQuery, Booking.class)
                .setParameter("pStart", start)
                .setParameter("pEnd", end);
        return hql.getResultList();
    }
}

