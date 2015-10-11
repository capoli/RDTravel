package com.realdolmen.rdtravel.controllers;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ExceptionHandlingController {
    public void handleException(Exception e, String errorMessage) {
        //TODO: log the exception
        showErrorMessage(errorMessage);
    }

    protected void showErrorMessage(String errorMessage) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(errorMessage);
        facesContext.addMessage(null, facesMessage);
    }
}
