package com.example.demo.customer.annotation.existingEmail;

import com.example.demo.customer.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistingEmailValidator implements ConstraintValidator<ExistingEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return customerRepository.existsByEmail(email);
    }
}
