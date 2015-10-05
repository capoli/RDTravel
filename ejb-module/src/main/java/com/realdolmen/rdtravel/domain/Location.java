package com.realdolmen.rdtravel.domain;

import javax.persistence.*;

@Entity
public class Location extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    @Access(value = AccessType.PROPERTY)
    private String code;
    @ManyToOne
    @JoinColumn(name = "county_fk")
    private Country country;

    public Location() {
    }

    public Location(String name, String code, Country country) {
        this.name = name;
        setCode(code);
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
