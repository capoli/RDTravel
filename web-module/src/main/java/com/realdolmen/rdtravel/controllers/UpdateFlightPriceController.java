package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Flight;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UpdateFlightPriceController extends UpdateFlightController {
    private Double customerPrice;

    @PostConstruct
    public void setReadOnly() {
        setIsReadOnly(true);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (flightToUpdate != null) {
            this.customerPrice = flightToUpdate.getCustomerPrice();
        }
    }

    @Override
    protected void completeFlight(Flight flight) {
        flight.setCustomerPrice(customerPrice);
    }

    public Double getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(Double customerPrice) {
        this.customerPrice = customerPrice;
    }
}
