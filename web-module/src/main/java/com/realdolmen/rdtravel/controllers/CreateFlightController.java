package com.realdolmen.rdtravel.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CreateFlightController {
    private long arrivalLocationId;
    private long departureLocationId;

    public long getArrivalLocationId() {
        return arrivalLocationId;
    }

    public void setArrivalLocationId(long arrivalLocationId) {
        this.arrivalLocationId = arrivalLocationId;
    }

    public long getDepartureLocationId() {
        return departureLocationId;
    }

    public void setDepartureLocationId(long departureLocationId) {
        this.departureLocationId = departureLocationId;
    }
}
