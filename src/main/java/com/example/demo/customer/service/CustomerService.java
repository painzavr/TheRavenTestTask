package com.example.demo.customer.service;

import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomer(Long customerId);

    List<Customer> getAllCustomers();

    void deleteCustomer(Long customerId);

    Customer updateCustomer(Long customerId, Customer customer);

}
