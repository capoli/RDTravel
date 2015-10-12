package com.realdolmen.rdtravel.validators;


import com.realdolmen.rdtravel.persistence.CustomerEJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.regex.Pattern;

@Named
@RequestScoped
public class UserNameValidator implements Validator {

    @Inject
    private CustomerEJB customerEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String userName = value.toString();
        checkLength(userName.length());
        checkOnlyLettersAndNumber(userName);
        checkDoesntAlreadyExist(userName);
    }

    private void checkLength(int length) {
        if (length < 5 || length > 20) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A username must be between 5 and 20 characters long");
            throw new ValidatorException(msg);
        }
    }

    private void checkOnlyLettersAndNumber(String userName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (!pattern.matcher(userName).matches()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A username can only consist off letters and numbers");
            throw new ValidatorException(msg);
        }
    }

    private void checkDoesntAlreadyExist(String userName) {
        if (customerEJB.findCustomerByName(userName) != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "This username already exists");
            throw new ValidatorException(msg);
        }
    }
}
