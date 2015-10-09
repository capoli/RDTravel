package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Booking;
import com.realdolmen.rdtravel.domain.PaymentType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
@LocalBean
public class BookingEJB implements RemoteBookingEJB {
    @Override
    public List<String> getPaymentTypesAsString() {
        return Stream.of(PaymentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentType[] getPaymentTypes() {
        return PaymentType.values();
    }
}
