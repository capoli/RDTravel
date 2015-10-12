package com.realdolmen.rdtravel.validation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;


@Named
@RequestScoped
public class NotEmptyValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getLabel(component) + " is required");
            throw new ValidatorException(msg);
        }
    }

    protected String getLabel(UIComponent component) {
        String label = "This field";
        Object labelAttribute = component.getAttributes().get("label");
        if (labelAttribute != null) {
            label = labelAttribute.toString();
        }
        return label;
    }
}
