package com.realdolmen.rdtravel.controllers;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class LoginController {

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login";
    }
}
