package com.realdolmen.rdtravel.integration;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.persistence.RemoteFlightEJB;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;

public class FlightEJBTest extends RemoteIntegrationTest {
    private RemoteFlightEJB repo;
    private static final long flightId = 1000l;

    @Before
    public void init() throws NamingException {
        repo = lookup("ear-module-1.5/ejb-module-1.5/FlightEJB!com.realdolmen.rdtravel.persistence.RemoteFlightEJB");
    }

    @Test
    public void flightsCanBeFound() {
        List<Flight> flights = repo.findFlights();
        assertTrue(flights.size() > 10);
    }

    @Test
    public void flightCanBeFoundById() {
        Flight flight = repo.findFlightById(flightId);
        assertNotNull(flight);
    }

    @Test
    public void flightCanBeDeleted() {
        Flight flight = repo.findFlightById(flightId);
        repo.deleteFlight(flight);
        assertNull(repo.findFlightById(flightId));
    }

    @Test
    public void flightCanBeUpdated() {
        Flight flight = repo.findFlightById(flightId);
        int newNumberOfSeats = 444;
        flight.setNumberOfSeats(newNumberOfSeats);
        repo.updateFlight(flight);
        Flight flight1 = repo.findFlightById(flightId);
        assertEquals(newNumberOfSeats, flight1.getNumberOfSeats().intValue());
    }

    @Test
    public void flightsCanBeFoundByAirline() {
        long airlineId = 1000l;
        List<Flight> flights = repo.findFlightsByAirline(airlineId);
        for (Flight flight : flights) {
            assertEquals(airlineId, flight.getAirline().getId().longValue());
        }
    }

}
