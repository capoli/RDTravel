package com.realdolmen.rdtravel.viewmodels;

import java.util.Date;

public class FlightModel {
    private Long arrivalLocationId;
    private Long departureLocationId;
    private Date arrivalDateTime;
    private Date departureDateTime;
    private Double flightPrice;
    private Integer availableSeats;
    private Integer seatsThreshold;

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

    public Integer getSeatsThreshold() {
        return seatsThreshold;
    }

    public void setSeatsThreshold(Integer seatsThreshold) {
        this.seatsThreshold = seatsThreshold;
    }
}
