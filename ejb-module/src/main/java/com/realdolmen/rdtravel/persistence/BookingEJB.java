package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
import com.realdolmen.rdtravel.domain.PaymentType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Stateless
@LocalBean
public class BookingEJB implements RemoteBookingEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Booking> findBookings() {
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }

    @Override
    public Booking findBookingById(Long id) {
        return em.find(Booking.class, id);
    }

    @Override
    public Booking createBooking(Booking booking) {
        em.persist(booking);
        return booking;
    }

    @Override
    public void deleteBooking(Booking booking) {
        em.remove(updateBooking(booking));
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return em.merge(booking);
    }

    @Override
    public List<String> getPaymentTypes() {
        return (List) Arrays.asList(PaymentType.values());
    }
}
