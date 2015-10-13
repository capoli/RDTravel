package com.realdolmen.rdtravel.beans;


import com.realdolmen.rdtravel.domain.Customer;
import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.domain.Role;
import com.realdolmen.rdtravel.persistence.CustomerEJB;
import com.realdolmen.rdtravel.persistence.PartnerEJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Named
@RequestScoped
public class SecurityBean {
    @Inject
    private Principal principal;

    @Inject
    private CustomerEJB customerEJB;

    @Inject
    private PartnerEJB partnerEJB;


    public Customer getCustomer() {
        return customerEJB.findCustomerByName(principal.getName());
    }

    public Partner getPartner() {
        return partnerEJB.findPartnerByName(principal.getName());
    }

    public Role getRole() {
        HttpServletRequest request = getRequest();
        if (request.isUserInRole("PARTNER")) {
            return Role.PARTNER;
        }
        if (request.isUserInRole("CUSTOMER")) {
            return Role.CUSTOMER;
        }
        if (request.isUserInRole("EMPLOYEE")) {
            return Role.EMPLOYEE;
        }
        return null;
    }

    public boolean isCustomer() {
        return getRequest().isUserInRole(Role.CUSTOMER.getValue());
    }

    public boolean isPartner() {
        return getRequest().isUserInRole(Role.PARTNER.getValue());
    }

    public boolean isEmployee() {
        return getRequest().isUserInRole(Role.EMPLOYEE.getValue());
    }

    public boolean isGuest() {
        return principal.getName().equals("anonymous");
    }

    public Principal getPrincipal() {
        return principal;
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
