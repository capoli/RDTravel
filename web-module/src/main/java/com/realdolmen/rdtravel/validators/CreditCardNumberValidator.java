package com.realdolmen.rdtravel.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@RequestScoped
public class CreditCardNumberValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String creditCardNumber = value.toString();
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14})|" +
                "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
                "(?<amex>3[47][0-9]{13})|" +
                "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
                "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

        Pattern pattern = Pattern.compile(regex);

        //Strip all hyphens
        creditCardNumber = creditCardNumber.replaceAll("-", "");

        //Match the card
        Matcher matcher = pattern.matcher(creditCardNumber);

        if (!matcher.matches()) {
            //If card is not valid
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "You must insert a valid creditcardnumber");
            throw new ValidatorException(msg);
        }
    }
}
