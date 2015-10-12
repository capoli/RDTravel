package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight extends AbstractEntity {
    private static final double MARGIN = 0.05;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(name = "departure-time")
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(name = "arrival-time")
    private Date arrivalTime;

    /* duration of the flight in minutes */
    @Transient
    private Integer duration;

    @XmlElement(name = "number-of-seats")
    private Integer numberOfSeats;

    @XmlElement(name = "price")
    private Double price;

    @XmlElement(name = "end-user-price")
    private Double customerPrice;

    @ManyToOne
    @JoinColumn(name = "flight_departure_location_fk")
    @XmlElement(name = "departure-location")
    private Location departureLocation;

    @ManyToOne
    @JoinColumn(name = "flight_arrival_location_fk")
    @XmlElement(name = "arrival-location")
    private Location arrivalLocation;

    //    @ManyToOne
//    @JoinColumn(name = "flight_trip_fk")
//    private Trip trip;
    @ManyToMany(mappedBy = "flights")
    @XmlElement(name = "trip")
    private List<Trip> trips = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "airline_fk")
    private Airline airline;

    private Double discount;

    private Integer availableSeats;

    public Flight() {
    }

    public Flight(Date departureTime, Date arrivalTime, Integer numberOfSeats, Double price,
                  Location departureLocation, Location arrivalLocation, Double discount, Integer availableSeats) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.setDefaultCustomerPrice();
        //TODO: calculate duration
        // this.duration
        this.discount = discount;
        this.availableSeats = availableSeats;
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.addTrip((Trip) parent);
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(Double customerPrice) {
        this.customerPrice = customerPrice;
    }

    public void setDefaultCustomerPrice() {
        this.customerPrice = price + (price * MARGIN);
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
