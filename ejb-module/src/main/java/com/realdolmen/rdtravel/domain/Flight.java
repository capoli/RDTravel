package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Flight extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    /* duration of the flight in minutes */
    @Transient
    private Integer duration;
    private Integer numberOfSeats;
    private Double price;

    @ManyToOne
    private Location departureLocation;
    @ManyToOne
    private Location arrivalLocation;

    public Flight() {
    }

    public Flight(Date departureTime, Date arrivalTime, Integer numberOfSeats, Double price,
                  Location departureLocation, Location arrivalLocation) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        //TODO: calculate duration
        // this.duration
    }

    public Long getId() {
        return id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Location arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }
}
