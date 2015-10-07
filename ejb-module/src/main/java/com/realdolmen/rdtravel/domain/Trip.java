package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pricePerDay;

    @OneToMany(mappedBy = "trip")
    private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.PERSIST)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "trip_location_fk")
    private Location destination;

    @ManyToOne
    @JoinColumn(name = "period_fk")
    private Period period;

    public Trip() {
    }

    public Trip(Double pricePerDay, Location destination, Period period) {
        this.pricePerDay = pricePerDay;
        this.destination = destination;
        this.period = period;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }
}
