package com.realdolmen.rdtravel.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;


@Named
@RequestScoped
public class PositiveIntegerValidator extends NotEmptyValidator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        super.validate(context, component, value);
        checkIfInteger(value, component);
        checkIfPositive(value, component);
    }

    private void checkIfPositive(Object value, UIComponent component) {
        int intValue = (Integer) value;
        if (intValue <= 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getLabel(component) + " has to be a positive value (larger than zero)");
            throw new ValidatorException(msg);
        }
    }

    private void checkIfInteger(Object value, UIComponent component) {
        if (!(value instanceof Integer)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getLabel(component) + " has to be a non-decimal value");
            throw new ValidatorException(msg);
        }
    }
}
