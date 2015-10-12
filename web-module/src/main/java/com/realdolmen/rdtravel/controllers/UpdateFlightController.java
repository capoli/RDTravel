package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.beans.FlightIdBean;
import com.realdolmen.rdtravel.domain.Flight;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UpdateFlightController extends BaseFlightController {
    private Flight flightToUpdate;

    @ManagedProperty("#{param.flightId}")
    String flightIdParam;

    @Inject
    private FlightIdBean flightIdBean;

    public void onLoad() {
        if (flightIdParam != null) {
            flightIdBean.setFlightId(Long.parseLong(flightIdParam));
        }
        flightToUpdate = flightEJB.findFlightById(flightIdBean.getFlightId());
        flightModel.setArrivalDateTime(flightToUpdate.getArrivalTime());
        flightModel.setDepartureDateTime(flightToUpdate.getDepartureTime());
        flightModel.setArrivalLocationId(flightToUpdate.getArrivalLocation().getId());
        flightModel.setDepartureLocationId(flightToUpdate.getDepartureLocation().getId());
        flightModel.setFlightPrice(flightToUpdate.getPrice());
        flightModel.setAvailableSeats(flightToUpdate.getNumberOfSeats());
    }

    public String updateFlight() {
        flightToUpdate = flightEJB.findFlightById(flightIdBean.getFlightId());
        completeFlight(flightToUpdate);
        flightEJB.updateFlight(flightToUpdate);
        flightIdBean.setFlightId(null);
        return "/index.faces?faces.redirect=true&includeViewParams=true";
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
}
