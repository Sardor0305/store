package uz.pdp.store.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        acceptedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                               .map(Enum::name)
                               .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }

}