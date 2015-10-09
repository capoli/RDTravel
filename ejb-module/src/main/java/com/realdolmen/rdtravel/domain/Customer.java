package com.realdolmen.rdtravel.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<>();

    @Basic(optional = false)
    private String email;

    public Customer() {
    }

    public Customer(String userName, String password, String email) {
        super(userName, password);
        this.email = email;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
