package com.realdolmen.rdtravel.domain;

import javax.persistence.*;

@Entity
public class Booking extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;

    private Integer numberOfParticipants;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "trip_fk")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;

    public Booking() {
    }

    public Booking(Double totalPrice, Integer numberOfParticipants, PaymentType paymentType, Trip trip, Customer customer) {
        this.totalPrice = totalPrice;
        this.numberOfParticipants = numberOfParticipants;
        this.paymentType = paymentType;
        this.trip = trip;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
