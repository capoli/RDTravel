package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Continent extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    @Access(value = AccessType.PROPERTY)
    private String code;

    @OneToMany(mappedBy = "continent")
    private List<Country> countries = new ArrayList<>();

    public Continent() {
    }

    public Continent(String name, String code) {
        this.name = name;
        setCode(code);
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

    public List<Country> getCountries() {
        return countries;
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }
}
