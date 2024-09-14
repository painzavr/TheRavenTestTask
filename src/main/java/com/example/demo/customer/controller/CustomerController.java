package com.example.demo.customer.controller;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.CustomerRegistrationDTO;
import com.example.demo.customer.service.implementation.CustomerServiceImp;
import com.example.demo.customer.utility.CustomerMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerServiceImp customerServiceImp;

    @PostMapping
    public CustomerDTO registerNewCustomer(@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO){
        return CustomerMapper.INSTANCE.toDTO(customerServiceImp.createCustomer(CustomerMapper.INSTANCE.registrationToEntity(customerRegistrationDTO)));
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        log.info("Get all customers");
        return CustomerMapper.INSTANCE.toDTOs(customerServiceImp.getAllCustomers());
    }

    @GetMapping(path = "/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id){
        log.info("Get customer with id {}", id);
        return CustomerMapper.INSTANCE.toDTO(customerServiceImp.getCustomer(id));
    }

    @PutMapping(path = "/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO){
        log.info("Update customer with id {}", id);
        return CustomerMapper.INSTANCE.toDTO(customerServiceImp.updateCustomer(id, CustomerMapper.INSTANCE.toEntity(customerDTO)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        log.info("Delete customer with id {}", id);
        customerServiceImp.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
