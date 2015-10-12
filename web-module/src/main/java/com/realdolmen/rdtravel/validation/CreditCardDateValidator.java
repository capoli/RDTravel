package com.realdolmen.rdtravel.validation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.Calendar;

@Named
@RequestScoped
public class CreditCardDateValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String[] date = value.toString().split("/");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 2000;
        int month = calendar.get(Calendar.MONTH);
        int inputMonth = Integer.parseInt(date[0]);
        int inputYear = Integer.parseInt(date[1]);
        if(inputMonth > 12) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Month is not valid"));
        }
        else if(inputYear < year) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Creditcard date is expired"));
        }
        else if(inputYear == year && month < inputMonth - 1) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Creditcard date is expired"));
        }
    }
}
