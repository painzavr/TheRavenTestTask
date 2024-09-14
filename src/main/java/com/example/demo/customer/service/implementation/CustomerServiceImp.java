package com.example.demo.customer.service.implementation;

import com.example.demo.customer.exception.EntityNotFoundException;
import com.example.demo.customer.exception.InvalidArgumentException;
import com.example.demo.customer.service.CustomerService;
import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        log.info("Create customer");
        customer.setCreated(System.currentTimeMillis());
        customer.setUpdated(System.currentTimeMillis());
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        log.info("Get all customers");
        return customerRepository.findAll();
    }


    public Customer updateCustomer(Long id,Customer customer) {
        log.info("Update customer with id {}", id);
        if(Objects.equals(id, customer.getId()) || !customerRepository.existsById(id)){
            Customer customerToUpdate = customerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(id));

            if(!customerToUpdate.getEmail().equals(customer.getEmail())){
                throw new InvalidArgumentException();
            }

            customerToUpdate.setUpdated(System.currentTimeMillis());
            customerToUpdate.setPhone(customer.getPhone());
            customerToUpdate.setFullName(customer.getFullName());

            return customerRepository.save(customerToUpdate);
        }else{
            throw new InvalidArgumentException(customer.getId());
        }
    }

    public Customer getCustomer(Long customerId) {
        log.info("Get customer with id {}", customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(customerId));
    }

    public void deleteCustomer(Long customerId) {
        log.info("Delete customer with id {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(customerId));
        customer.setActive(false);
        customerRepository.save(customer);
    }
}
