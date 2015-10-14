package com.realdolmen.rdtravel.validators;

import com.realdolmen.rdtravel.persistence.CustomerEJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.regex.Pattern;

@Named
@RequestScoped
public class EmailValidator extends NotEmptyValidator {

    @Inject
    private CustomerEJB customerEJB;

    private Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        super.validate(context, component, value);
        String email = value.toString();
        checkDoesntAlreadyExist(email);
        isValidEmail(email);
    }

    private void checkDoesntAlreadyExist(String email) {
        if (customerEJB.findCustomerByEmail(email) != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "This email address is already used by another account");
            throw new ValidatorException(msg);
        }
    }

    private void isValidEmail(String value) {
        if (!pattern.matcher(value).matches()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Please insert a valid email address");
            throw new ValidatorException(msg);
        }
    }
}
