package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.beans.SecurityBean;
import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.domain.Role;
import com.realdolmen.rdtravel.persistence.FlightEJB;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SearchFlightController {
    @Inject
    private FlightEJB flightEJB;

    @Inject
    private SecurityBean securityBean;
    private List<Flight> flights;
    private List<Flight> filteredFlights;


    @PostConstruct
    public void init() {
        if (isEmployee())
            this.flights = flightEJB.findFlights();
        if (isPartner()) {
            Partner partner = securityBean.getPartner();
            this.flights = flightEJB.findFlightsByAirline(partner.getAirline().getId());
        }
    }

    public String determineRoute() {
        if (isPartner()) {
            return "partner/updateFlight.faces";
        }
        if (isEmployee()) {
            return "employee/updateFlightCustomerPrice.faces";
        }
        return null;
    }

    public String getBaseNavigationRoute() {
        return determineRoute();
    }

    public boolean isPartner() {
        return securityBean.getRole().equals(Role.PARTNER);
    }

    public boolean isEmployee() {
        return securityBean.getRole().equals(Role.EMPLOYEE);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public void setFilteredFlights(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
    }
}