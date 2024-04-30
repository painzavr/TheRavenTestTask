package com.example.demo.customer.utility;

import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.dto.CustomerDTO;

public class MappingUtils {
    public static Customer dtoToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .created(System.currentTimeMillis())
                .fullName(customerDTO.getFullName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .isActive(true)
                .build();
    }

    public static CustomerDTO customerToDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}
