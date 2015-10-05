package com.realdolmen.rdtravel.controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Named
@RequestScoped
public class LoginController {

    public String logout() throws ServletException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        origRequest.logout();
        return "/index.faces";
    }
}
