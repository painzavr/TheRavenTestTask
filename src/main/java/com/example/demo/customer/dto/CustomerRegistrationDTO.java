package com.example.demo.customer.dto;

import com.example.demo.customer.annotation.uniqueEmail.ValidUniqueEmail;
import com.example.demo.customer.annotation.phone.ValidPhone;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRegistrationDTO {

    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name must contain only letters")
    @Size(min = 2, max = 50, message = "Name must have from 2 to 50 characters")
    @NotNull(message = "Full name cannot be empty")
    private String fullName;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email must comply with the format")
    @Size(min = 2, max = 100, message = "Email must have from 2 to 100 characters")
    @NotNull(message = "Email cannot be empty")
    @ValidUniqueEmail
    private String email;

    @ValidPhone
    private String phone;
}
