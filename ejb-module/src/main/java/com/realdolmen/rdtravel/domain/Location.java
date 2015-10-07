package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @XmlElement(name = "name")
    private String name;

    @Basic(optional = false)
    @Access(value = AccessType.PROPERTY)
    @XmlElement(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "continent_fk")
    @XmlElement(name = "continent")
    private Continent continent;

//    @OneToMany(mappedBy = "destination", cascade = CascadeType.PERSIST)
    //@XmlElementWrapper(name = "trips")
    //@XmlElement(name = "trip")
    //private List<Trip> trips = new ArrayList<>();

    public Location() {
    }

    public Location(String name, String code, Continent continent) {
        this.name = name;
        setCode(code);
        this.continent = continent;
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

//    public List<Trip> getTrips() {
//        return trips;
//    }
//
//    public void addTrip(Trip trip) {
//        this.trips.add(trip);
//    }
}
