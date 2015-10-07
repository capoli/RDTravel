package com.realdolmen.rdtravel.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<>();

    public Customer() {
    }

    public Customer(String username, String password) {
        super(username, password);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }
}
