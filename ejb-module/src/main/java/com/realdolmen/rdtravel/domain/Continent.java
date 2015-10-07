package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name = "continent")
@XmlAccessorType(XmlAccessType.FIELD)
public class Continent extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @XmlElement(name = "name")
    private String name;

    @Basic(optional = false)
    @Access(value = AccessType.PROPERTY)
    @XmlElement(name = "code")
    private String code;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.PERSIST)
    @XmlElementWrapper(name = "locations")
    @XmlElement(name = "location")
    private List<Location> locations = new ArrayList<>();

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

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }
}
