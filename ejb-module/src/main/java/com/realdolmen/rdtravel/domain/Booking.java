package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import javax.persistence.Entity;
@Entity
public class Booking extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: calculatePrice
    @Transient
    private Double totalPrice;

    private Integer numberOfParticipants;

    private Double discount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "trip_fk")
    private Trip trip;

    public Booking() {
    }

    public Booking(Double totalPrice, Integer numberOfParticipants, Double discount, PaymentType paymentType, Trip trip) {
        this.totalPrice = totalPrice;
        this.numberOfParticipants = numberOfParticipants;
        this.discount = discount;
        this.paymentType = paymentType;
        this.trip = trip;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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
}
