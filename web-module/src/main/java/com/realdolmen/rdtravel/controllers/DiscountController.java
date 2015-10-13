package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Airline;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.persistence.CrudEJB;
import com.realdolmen.rdtravel.persistence.PartnerEJB;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.Principal;

@Named
@RequestScoped
public class DiscountController {
    @Inject
    private PartnerEJB partnerEJB;

    @Inject
    private CrudEJB crudEJB;

    @Inject
    private Principal principal;

    private Double discount;

    private Airline airline;

    @PostConstruct
    public void init() {
        Partner partner = partnerEJB.findPartnerByName(principal.getName());
        if (partner != null) {
            airline = partner.getAirline();
            if (airline != null) {
                this.discount = airline.getDiscount();
            }
        }
    }

    public String update() {
        if (discount != null) {
            if (airline != null) {
                airline.setDiscount(discount);
                crudEJB.update(airline);
                return "/index.faces?faces-redirect=true";
            }
        }
        return null;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
