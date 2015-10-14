package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Trip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "trip-group")
@XmlAccessorType(XmlAccessType.FIELD)
public class TripGroup implements Serializable {
    @XmlElement(name = "trip")
    private List<Trip> trips = new ArrayList<>();

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }
}
