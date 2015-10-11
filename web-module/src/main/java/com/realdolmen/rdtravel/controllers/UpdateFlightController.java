package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.beans.FlightIdBean;
import com.realdolmen.rdtravel.domain.Flight;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

@Named
@RequestScoped
public class UpdateFlightController extends BaseFlightController {
    protected Flight flightToUpdate;

    @ManagedProperty("#{param.flightId}")
    protected String flightIdParam;

    @Inject
    protected FlightIdBean flightIdBean;

    public void onLoad() {
        if (flightIdParam != null) {
            try {
                flightIdBean.setFlightId(Long.parseLong(flightIdParam));
            } catch (NumberFormatException e) {
                handleFlightError("flightId: " + flightIdParam + " could not be parsed correctly");
                return;
            }
        }
        if (flightIdBean.getFlightId() != null) {
            flightToUpdate = attemptFlightRetrieval(flightIdBean.getFlightId());
            if (flightToUpdate != null) {
                flightModel.setArrivalDateTime(flightToUpdate.getArrivalTime());
                flightModel.setDepartureDateTime(flightToUpdate.getDepartureTime());
                flightModel.setArrivalLocationId(flightToUpdate.getArrivalLocation().getId());
                flightModel.setDepartureLocationId(flightToUpdate.getDepartureLocation().getId());
                flightModel.setFlightPrice(flightToUpdate.getPrice());
                flightModel.setAvailableSeats(flightToUpdate.getNumberOfSeats());
            } else {
                handleFlightError("No Flight with Id: " + flightIdParam + " could be found");
            }
        } else {
            handleFlightError("Parameter \"flightId\" is missing");
        }
    }

    public String updateFlight() {
        flightToUpdate = attemptFlightRetrieval(flightIdBean.getFlightId());
        if (flightToUpdate != null) {
            completeFlight(flightToUpdate);
            flightEJB.updateFlight(flightToUpdate);
            flightIdBean.setFlightId(null);
            return "/index.faces?faces.redirect=true&includeViewParams=true";
        }
        return null;
    }

    public String submit() {
        return updateFlight();
    }


    public void setFlightIdParam(String flightIdParam) {
        this.flightIdParam = flightIdParam;
    }

    public String getFlightIdParam() {
        return flightIdParam;
    }

    public Flight getFlightToUpdate() {
        return flightToUpdate;
    }

    public void setFlightToUpdate(Flight flightToUpdate) {
        this.flightToUpdate = flightToUpdate;
    }

    private Flight attemptFlightRetrieval(Long id) {
        try {
            return flightEJB.findFlightById(id);
        } catch (PersistenceException e) {
            handleFlightError("Could not retrieve flight from database");
        }
        return null;
    }

    private void handleFlightError(String message) {
        setHasError(true);
        showErrorMessage(message);
    }
}
