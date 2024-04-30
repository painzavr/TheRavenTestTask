package com.example.demo.customer.controller;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> registerNewCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        CustomerDTO customer = customerService.addNewCustomer(customerDTO);
        if(customer != null){
            return ResponseEntity.ok().body(customer);
        }else{
            return ResponseEntity.badRequest().body("Email is already used");
        }

    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        CustomerDTO response = customerService.getCustomer(id);
        if(response != null){
            return ResponseEntity.ok().body(response);
        }else{
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO){
        CustomerDTO responseCustomer = customerService.updateCustomer(id, customerDTO);
        if(responseCustomer==null){
            return ResponseEntity.badRequest().body("Customer not found");
        }else{
            return ResponseEntity.ok().body(responseCustomer);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){

        if(customerService.deleteCustomer(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
