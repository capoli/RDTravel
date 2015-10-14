package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.persistence.CrudEJB;
import com.realdolmen.rdtravel.persistence.FlightEJB;
import com.realdolmen.rdtravel.persistence.LocationEJB;
import com.realdolmen.rdtravel.persistence.PartnerEJB;
import com.realdolmen.rdtravel.viewmodels.FlightModel;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public class BaseFlightController extends ExceptionHandlingController {
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
    protected Boolean isReadOnly = false;
    protected Boolean hasError = false;

    public String submit() {
        return "/index.faces?faces-redirect=true";
    }

    protected void completeFlight(Flight flight) {
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
        flight.setSeatsThreshold(flightModel.getSeatsThreshold());
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
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

    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

}
