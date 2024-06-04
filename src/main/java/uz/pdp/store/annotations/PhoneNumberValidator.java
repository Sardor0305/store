package uz.pdp.store.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^998\\d{9}$");

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

}
