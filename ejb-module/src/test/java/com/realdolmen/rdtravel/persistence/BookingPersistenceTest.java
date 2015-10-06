package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
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
        Booking booking = new Booking(1500.00, 3, 0.10, PaymentType.CREDITCARD, trip);
        trip.addBooking(booking);
        entityManager().persist(trip);
        entityManager().flush();
        entityManager().refresh(trip);
        entityManager().refresh(booking);
        assertNotNull(trip);
        assertNotNull(booking);
        assertNotNull(booking.getId());
        assertEquals(1001l, (long) trip.getId());
        assertEquals(1, trip.getBookings().size());
        assertNotNull(booking.getTrip());
    }

    @Test
    public void bookingCanBeFound() {
        Booking booking = entityManager().find(Booking.class, 1000l);
        assertNotNull(booking);
        assertEquals(1000l, (long) booking.getId());
    }
}
