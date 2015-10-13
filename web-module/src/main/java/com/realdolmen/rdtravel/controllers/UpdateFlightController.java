package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.beans.FlightIdBean;
import com.realdolmen.rdtravel.beans.SecurityBean;
import com.realdolmen.rdtravel.domain.Flight;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.domain.Role;

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

    @Inject
    protected SecurityBean securityBean;

    public void onLoad() {
        if (tryHandleNewFlightParam()) {
            if (tryContinueWithFlightIdBean()) {
                if (tryRetrieveFlightToUpdate()) {
                    if (partnerIsFromSameAirline()) {
                        populateFlightModel();
                    }
                }
            }
        }
    }

    public boolean tryHandleNewFlightParam() {
        if (flightIdParam != null) {
            try {
                flightIdBean.setFlightId(Long.parseLong(flightIdParam));
            } catch (NumberFormatException e) {
                handleFlightError("flightId: " + flightIdParam + " could not be parsed correctly");
                return false;
            }
        }
        return true;
    }

    public boolean tryContinueWithFlightIdBean() {
        if (flightIdBean.getFlightId() != null) {
            return true;
        } else {
            handleFlightError("Parameter \"flightId\" is missing");
            return false;
        }
    }

    public boolean tryRetrieveFlightToUpdate() {
        flightToUpdate = attemptFlightRetrieval(flightIdBean.getFlightId());
        if (flightToUpdate != null) {
            return true;

        } else {
            handleFlightError("No Flight with Id: " + flightIdParam + " could be found");
            return false;
        }
    }


    public boolean partnerIsFromSameAirline() {
        if (securityBean.getRole().equals(Role.PARTNER)) {
            Partner partner = securityBean.getPartner();
            if (partner != null) {
                if (flightToUpdate.getAirline() != null && partner.getAirline() != null)
                    return partner.getAirline().getId().equals(flightToUpdate.getAirline().getId());
            }
            handleFlightError("You are not allowed to alter this flight");
            return false;
        }
        return true;
    }

    public void populateFlightModel() {
        flightModel.setArrivalDateTime(flightToUpdate.getArrivalTime());
        flightModel.setDepartureDateTime(flightToUpdate.getDepartureTime());
        flightModel.setArrivalLocationId(flightToUpdate.getArrivalLocation().getId());
        flightModel.setDepartureLocationId(flightToUpdate.getDepartureLocation().getId());
        flightModel.setFlightPrice(flightToUpdate.getPrice());
        flightModel.setAvailableSeats(flightToUpdate.getNumberOfSeats());
        //TODO: flightModel.setSeatsThreshold(flightToUpdate.getSeatsThreshhold());
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

    public void setFlightIdParam(String flightIdParam) {
        this.flightIdParam = flightIdParam;
    }

    public String getFlightIdParam() {
        return flightIdParam;
    }

}
