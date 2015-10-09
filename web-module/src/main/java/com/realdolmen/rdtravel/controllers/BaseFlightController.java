package com.realdolmen.rdtravel.controllers;


import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.persistence.CrudEJB;
import com.realdolmen.rdtravel.persistence.FlightEJB;
import com.realdolmen.rdtravel.persistence.LocationEJB;
import com.realdolmen.rdtravel.persistence.PartnerEJB;
import com.realdolmen.rdtravel.viewmodels.FlightModel;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

public class BaseFlightController {
    @Inject
    protected FlightEJB flightEJB;

    @Inject
    protected CrudEJB crudEJB;

    @Inject
    protected PartnerEJB partnerEJB;

    @Inject
    protected LocationEJB locationEJB;

    @Inject
    protected Principal principal;

    protected FlightModel flightModel = new FlightModel();

    public String submit() {
        return "/index.faces";
    }


    public void completeFlight(Flight flight) {
        Partner partner = partnerEJB.findPartnerByName(principal.getName());
        Location arrivalLocation = (Location) crudEJB.findById(Location.class, flightModel.getArrivalLocationId());
        Location departureLocation = (Location) crudEJB.findById(Location.class, flightModel.getDepartureLocationId());
        flight.setAirline(partner.getAirline());
        flight.setArrivalLocation(arrivalLocation);
        flight.setDepartureLocation(departureLocation);
        flight.setDepartureTime(flightModel.getDepartureDateTime());
        flight.setArrivalTime(flightModel.getArrivalDateTime());
        flight.setNumberOfSeats(flightModel.getAvailableSeats());
        flight.setPrice(flightModel.getFlightPrice());
    }

    public List<Location> getAllLocations() {
        return locationEJB.findAllLocations();
    }

    public FlightModel getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(FlightModel flightModel) {
        this.flightModel = flightModel;
    }
}