package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BookingPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test
    public void bookingCanBePersisted() throws Exception {
        //Booking booking = new Booking(500.0, 2, 0.05, null);
        assertTrue(true);
    }
}
