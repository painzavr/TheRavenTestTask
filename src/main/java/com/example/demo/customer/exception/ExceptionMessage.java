package com.example.demo.customer.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionMessage {

    private String message;

    public ExceptionMessage(String message) {
        this.message = message;
    }
}
