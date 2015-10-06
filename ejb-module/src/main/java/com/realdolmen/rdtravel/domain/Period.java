package com.realdolmen.rdtravel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Period extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date periodStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date periodEnd;

    @OneToMany(mappedBy = "period", cascade = CascadeType.PERSIST)
    private List<Trip> trips = new ArrayList<>();

    public Period() {
    }

    public Period(Date periodStart, Date periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public Long getId() {
        return id;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }
}
