package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Location;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

public class FlightPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test
    public void flightCanBePersisted() {
        Location departure = entityManager().find(Location.class, 1000l);
        Location arrival = entityManager().find(Location.class, 1001l);
        Flight flight = new Flight(new Date(), new Date(), 180, 50.0, departure, arrival, 120, 120);
        entityManager().persist(flight);
        entityManager().flush();
        entityManager().refresh(flight);
        assertNotNull(flight);
        assertNotNull(flight.getId());
        assertEquals(180, (int) flight.getNumberOfSeats());
        assertEquals(new Double(50.0), flight.getPrice());
        assertNotNull(flight.getDepartureLocation());
        assertNotNull(flight.getArrivalLocation());
        assertNotNull(flight.getDepartureTime());
        assertNotNull(flight.getArrivalTime());
    }

    @Test
    public void flightCanBeFound() {
        Flight flight = entityManager().find(Flight.class, 1000l);
        assertNotNull(flight);
        assertEquals(1000l, (long) flight.getId());
    }
}
