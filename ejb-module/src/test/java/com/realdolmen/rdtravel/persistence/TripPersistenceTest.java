package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Period;
import com.realdolmen.rdtravel.domain.Trip;
import org.junit.Test;

public class TripPersistenceTest extends DataSetPersistenceTest {
    @Test
    public void tripCanBePersisted() {
        Location location = entityManager().find(Location.class, 1000l);
        Period period = entityManager().find(Period.class, 1000l);
        Trip trip = new Trip(45.00, location, period);
        period.addTrip(trip);
        entityManager().persist(period);
        entityManager().persist(trip);
        entityManager().flush();
        entityManager().refresh(period);
        entityManager().refresh(location);
        entityManager().refresh(trip);
        assertNotNull(period);
        assertNotNull(location);
        assertNotNull(trip);
        assertNotNull(trip.getDestination());
        assertNotNull(trip.getPeriod());
    }

    @Test
    public void tripCanBeFound() {
        Trip trip = entityManager().find(Trip.class, 1000l);
        assertNotNull(trip);
        assertEquals(1000l, (long) trip.getId());
    }
}
