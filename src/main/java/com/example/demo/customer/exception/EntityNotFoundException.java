package com.example.demo.customer.exception;

public class EntityNotFoundException extends RuntimeException {

    public static final String CUSTOMER_WITH_ID = "Customer with ID ";
    public static final String NOT_FOUND = " not found";

    public EntityNotFoundException(Long id) {
        super(CUSTOMER_WITH_ID + id + NOT_FOUND);
    }
}
