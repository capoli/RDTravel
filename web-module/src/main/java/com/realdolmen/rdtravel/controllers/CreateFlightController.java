package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.persistence.CrudEJB;
import com.realdolmen.rdtravel.persistence.FlightEJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class CreateFlightController {
    @Inject
    private FlightEJB flightEJB;

    @Inject
    private CrudEJB crudEJB;

    private Long arrivalLocationId;
    private Long departureLocationId;
    private Date arrivalDateTime;
    private Date departureDateTime;
    private Double flightPrice;
    private Integer availableSeats;


    public String createFlight() {
        Location arrivalLocation = (Location) crudEJB.findById(Location.class, arrivalLocationId);
        Location departureLocation = (Location) crudEJB.findById(Location.class, departureLocationId);
        flightEJB.createFlight(new Flight(departureDateTime, arrivalDateTime, availableSeats, flightPrice, departureLocation, arrivalLocation));
        return "/index.faces";
    }

    public Long getArrivalLocationId() {
        return arrivalLocationId;
    }

    public void setArrivalLocationId(Long arrivalLocationId) {
        this.arrivalLocationId = arrivalLocationId;
    }

    public Long getDepartureLocationId() {
        return departureLocationId;
    }

    public void setDepartureLocationId(Long departureLocationId) {
        this.departureLocationId = departureLocationId;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
