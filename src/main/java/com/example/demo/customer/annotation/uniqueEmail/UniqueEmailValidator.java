package com.example.demo.customer.annotation.uniqueEmail;


import com.example.demo.customer.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<ValidUniqueEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return !customerRepository.existsByEmail(email);
    }
}
