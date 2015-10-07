package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface RemoteTripEJB {
    //TODO: available seats ook in rekening brengen
    List<Trip> findTripsForCriteria(Long destinationId, Date periodStart, Date periodEnd);
    List<Location> findLocationsWithTrips();
}
