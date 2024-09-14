package com.example.demo.customer.annotation.uniqueEmail;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUniqueEmail {
    String message() default "Such email has already been registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}