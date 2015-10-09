package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
import com.realdolmen.rdtravel.domain.PaymentType;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by OCPAX79 on 5/10/2015.
 */
@Remote
public interface RemoteBookingEJB {
    List<String> getPaymentTypesAsString();

    PaymentType[] getPaymentTypes();
}
