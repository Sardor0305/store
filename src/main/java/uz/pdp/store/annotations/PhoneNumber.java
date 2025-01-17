package uz.pdp.store.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.store.unitls.MessageKey;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default MessageKey.INVALID_PHONE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
