package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pricePerDay;

    @Temporal(TemporalType.TIMESTAMP)
    private Date periodStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodEnd;

    @OneToMany
    private List<Flight> flights = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "trip_location_fk")
    private Location destination;

    public Trip() {
    }

    public Trip(Double pricePerDay, Date periodStart, Date periodEnd, Location destination) {
        this.pricePerDay = pricePerDay;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}