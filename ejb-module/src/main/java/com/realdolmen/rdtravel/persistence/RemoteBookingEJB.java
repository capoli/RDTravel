package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by OCPAX79 on 5/10/2015.
 */
@Remote
public interface RemoteBookingEJB {
    List<Booking> findBookings();

    Booking findBookingById(Long id);

    Booking createBooking(Booking booking);

    void deleteBooking(Booking booking);

    Booking updateBooking(Booking booking);
    List<String> getPaymentTypes();
}
