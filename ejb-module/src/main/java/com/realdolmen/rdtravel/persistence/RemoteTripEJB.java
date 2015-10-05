package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteTripEJB {
    List<Trip> findTrips();

    Trip findTripById(Long id);

    Trip createTrip(Trip trip);

    void deleteTrip(Trip trip);

    Trip updateTrip(Trip trip);
}
