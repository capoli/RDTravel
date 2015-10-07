package com.realdolmen.rdtravel.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Trip extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "price-per-day")
    private Double pricePerDay;

    @ManyToMany
    @JoinTable(name = "trip_flight",
            joinColumns = @JoinColumn(name = "trip_fk"),
            inverseJoinColumns = @JoinColumn(name = "flight_fk"))
    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.PERSIST)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "trip_location_fk")
    @XmlElement(name = "destination")
    private Location destination;

    @ManyToOne
    @JoinColumn(name = "period_fk")
    @XmlElement(name = "period")
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

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
