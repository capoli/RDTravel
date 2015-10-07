package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
import com.realdolmen.rdtravel.domain.Customer;
import com.realdolmen.rdtravel.domain.PaymentType;
import com.realdolmen.rdtravel.domain.Trip;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookingPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test
    public void bookingCanBePersisted() throws Exception {
        Trip trip = entityManager().find(Trip.class, 1001l);
        Customer customer = entityManager().find(Customer.class, 1000l);
        Booking booking = new Booking(1500.00, 3, 0.10, PaymentType.CREDITCARD, trip, customer);
        trip.addBooking(booking);
        entityManager().persist(customer);
        entityManager().persist(trip);
        entityManager().flush();
        entityManager().refresh(trip);
        entityManager().refresh(booking);
        entityManager().refresh(customer);
        assertNotNull(customer);
        assertNotNull(trip);
        assertNotNull(booking);
        assertNotNull(booking.getId());
        assertEquals(1001l, (long) trip.getId());
        assertEquals(1, trip.getBookings().size());
//        assertEquals(1, customer.getBookings().size()); //TODO: FIX double addition
        assertNotNull(booking.getTrip());
    }

    @Test
    public void bookingCanBeFound() {
        Booking booking = entityManager().find(Booking.class, 1000l);
        assertNotNull(booking);
        assertEquals(1000l, (long) booking.getId());
    }
}
