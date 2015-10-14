package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface RemoteTripEJB {
    List<Trip> findTripsForCriteria(Long destinationId, Date periodStart, Date periodEnd, Integer numberOfParticipants);

    List<Location> findLocationsWithTrips();

    List<String> findLocationNamesWithTrips();

    Trip findTripEagerLoaded(Long id);
}
