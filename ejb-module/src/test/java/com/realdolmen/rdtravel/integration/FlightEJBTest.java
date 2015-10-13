package com.realdolmen.rdtravel.integration;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.persistence.RemoteFlightEJB;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.Date;
import java.util.List;

import static com.realdolmen.rdtravel.utils.TestDataUtil.hawai;
import static com.realdolmen.rdtravel.utils.TestDataUtil.london;

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
    public void FlightCanBeFoundById() {
        Flight flight = repo.findFlightById(flightId);
        assertNotNull(flight);
    }

    @Test
    public void FlightCanBeCreated() {
        Flight flight = new Flight(new Date(), new Date(), 200, 99.99, hawai(), london(), 0.05, 200);
        repo.createFlight(flight);
        assertNotNull(flight.getId());
    }

    @Test
    public void flightCanBeDeleted() {
        Flight flight = repo.findFlightById(flightId);
        repo.deleteFlight(flight);
        assertNull(repo.findFlightById(flightId));
    }

}
