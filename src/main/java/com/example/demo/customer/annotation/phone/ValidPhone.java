package com.example.demo.customer.annotation.phone;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhone {
    String message() default "Phone number format is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}