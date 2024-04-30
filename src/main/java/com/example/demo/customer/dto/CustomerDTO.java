package com.example.demo.customer.dto;

import com.example.demo.customer.annotation.ValidPhone;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name must contain only letters")
    @Size(min = 2, max = 50, message = "Name must have from 2 to 50 characters")
    private String fullName;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email must comply with the format")
    @Size(min = 2, max = 100, message = "Email must have from 2 to 100 characters")
    @Column(unique = true)
    private String email;

    @ValidPhone
    private String phone;
}
