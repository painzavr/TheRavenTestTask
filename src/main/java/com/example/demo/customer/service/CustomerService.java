package com.example.demo.customer.service;

import com.example.demo.customer.utility.MappingUtils;
import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.customer.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDTO addNewCustomer(CustomerDTO customerDTO){
        if(Validator.validate(customerDTO) && customerRepository.findCustomerByEmail(customerDTO.getEmail()).isEmpty()) {
            Customer customer = MappingUtils.dtoToCustomer(customerDTO);
            return MappingUtils.customerToDto(customerRepository.save(customer));
        }


        return null;
    }
    public List<CustomerDTO> getCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(MappingUtils::customerToDto).toList();
    }


    public CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO) {
        if(!Objects.equals(id, customerDTO.getId())){
            return null;
        }
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            Customer customerToUpdate = customer.get();
            if(Validator.validate(customerDTO) && customerToUpdate.getEmail().equals(customerDTO.getEmail())) {
                customerToUpdate.setUpdated(System.currentTimeMillis());
                customerToUpdate.setPhone(customerDTO.getPhone());
                customerToUpdate.setFullName(customerDTO.getFullName());
                return MappingUtils.customerToDto(customerRepository.save(customerToUpdate));
            }
        }
        return null;
    }

    public CustomerDTO getCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.map(MappingUtils::customerToDto).orElse(null);

    }

    public boolean deleteCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setActive(false);
            customerRepository.save(customer);
            return true;
        }else{
            return false;
        }
    }

}
