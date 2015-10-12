package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Flight;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CreateFlightController extends BaseFlightController {

    public String submit() {
        return createFlight();
    }

    public String createFlight() {
        doCreate();
        return "/index.faces?faces.redirect=true";
    }

    private void doCreate() {
        Flight flight = new Flight();
        completeFlight(flight);
        flightEJB.createFlight(flight);
    }
}
