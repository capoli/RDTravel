package com.realdolmen.rdtravel.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class PositiveDoubleValidator extends NotEmptyValidator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        super.validate(context, component, value);
        String valueString = value.toString();
        double valueDouble = checkIfParsable(valueString, component);
        checkIfPositive(valueDouble, component);
    }

    private double checkIfParsable(String valueString, UIComponent component) {
        try {
            return Double.parseDouble(valueString);
        } catch (NumberFormatException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getLabel(component) + " must be a decimal value.");
            throw new ValidatorException(msg);
        }
    }

    private void checkIfPositive(double value, UIComponent component) {
        if (value < 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getLabel(component) + " must be a positive value.");
            throw new ValidatorException(msg);
        }
    }
}
