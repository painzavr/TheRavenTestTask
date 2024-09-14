package com.example.demo.customer.exception;

public class InvalidArgumentException extends RuntimeException {

    public static final String ID_IS_NOT_AVAILIBLE = "Id is not available: ";
    public static final String EMAIL_IS_NOT_EDITABLE = "Email field is not editable";

    public InvalidArgumentException(Long bodyId) {
        super(ID_IS_NOT_AVAILIBLE + bodyId);
    }

    public InvalidArgumentException() {
        super(EMAIL_IS_NOT_EDITABLE);
    }
}
