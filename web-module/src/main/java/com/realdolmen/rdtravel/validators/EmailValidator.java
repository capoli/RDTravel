package com.realdolmen.rdtravel.validators;

import com.realdolmen.rdtravel.persistence.CustomerEJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EmailValidator extends NotEmptyValidator {

    @Inject
    private CustomerEJB customerEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        super.validate(context, component, value);
        String email = value.toString();
        checkDoesntAlreadyExist(email);
    }

    private void checkDoesntAlreadyExist(String email) {
        if (customerEJB.findCustomerByEmail(email) != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "This email address is already used by another account");
            throw new ValidatorException(msg);
        }
    }
}
