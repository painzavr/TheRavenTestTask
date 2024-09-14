package com.example.demo.customer.annotation.existingEmail;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistingEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingEmail {
    String message() default "Email field is not editable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}