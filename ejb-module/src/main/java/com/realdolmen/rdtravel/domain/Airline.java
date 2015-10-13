package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Airline extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    private Double discount;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights = new ArrayList<>();


    @OneToMany(mappedBy = "airline")
    private List<Partner> partners = new ArrayList<>();

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public void addPartner(Partner partner) {
        this.partners.add(partner);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
