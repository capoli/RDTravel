package com.realdolmen.rdtravel.validation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.regex.Pattern;

@Named
@RequestScoped
public class PasswordValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = value.toString();
        checkLength(password.length());
        checkLettersAndNumbers(password);
    }

    private void checkLength(int length) {
        if (length < 5 || length > 20) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A password must be between 5 and 20 characters long");
            throw new ValidatorException(msg);
        }
    }

    private void checkLettersAndNumbers(String password) {
        Pattern pattern = Pattern.compile("^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$");
        if (!pattern.matcher(password).matches()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A password must consist off letters and numbers");
            throw new ValidatorException(msg);
        }
    }
}
