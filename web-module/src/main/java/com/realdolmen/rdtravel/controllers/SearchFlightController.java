package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.persistence.FlightEJB;
import com.realdolmen.rdtravel.persistence.PartnerEJB;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Named
@RequestScoped
public class SearchFlightController {
    @Inject
    private FlightEJB flightEJB;

    @Inject
    private PartnerEJB partnerEJB;

    @Inject
    private Principal principal;
    private List<Flight> flights;
    private List<Flight> filteredFlights;


    @PostConstruct
    public void init() {
        if (isEmployee())
            this.flights = flightEJB.findFlights();
        if (isPartner()) {
            Partner partner = partnerEJB.findPartnerByName(principal.getName());
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

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public boolean isPartner() {
        return getRequest().isUserInRole("PARTNER");
    }

    public boolean isEmployee() {
        return getRequest().isUserInRole("EMPLOYEE");
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