package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Customer;
import com.realdolmen.rdtravel.persistence.CustomerEJB;
import com.realdolmen.rdtravel.viewmodels.RegisterModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterController {

    @Inject
    private CustomerEJB customerEJB;
    private RegisterModel registerModel;

    @PostConstruct
    public void init() {
        registerModel = new RegisterModel();
    }

    public RegisterModel getRegisterModel() {
        return registerModel;
    }

    public void setRegisterModel(RegisterModel registerModel) {
        this.registerModel = registerModel;
    }

    public String register() {
        Customer customer = new Customer(registerModel.getUserName(), registerModel.getPassword(), registerModel.getEmail());
        customerEJB.createCustomer(customer);
        return "/index.faces?faces.redirect=true";
    }
}
