package com.realdolmen.rdtravel.validators;

import com.realdolmen.rdtravel.domain.Trip;
import com.realdolmen.rdtravel.persistence.CrudEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class TripValidator implements Validator {
    @EJB
    private CrudEJB crudEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(crudEJB.findById(Trip.class, (Long) value) == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "You must select a trip first");
            throw new ValidatorException(msg);
        }
    }
}
