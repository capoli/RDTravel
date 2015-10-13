package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Flight;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteFlightEJB {
    List<Flight> findFlights();

    Flight findFlightById(Long id);

    Flight createFlight(Flight flight);

    void deleteFlight(Flight flight);

    Flight updateFlight(Flight flight);

    List<Flight> findFlightsByAirline(long airLineId);
}
