package com.example.demo;

import com.example.demo.customer.controller.CustomerController;
import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.CustomerRegistrationDTO;
import com.example.demo.customer.service.implementation.CustomerServiceImp;
import com.example.demo.customer.utility.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @Mock
    private CustomerServiceImp customerServiceImp;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerNewCustomer_ShouldReturnCustomerDTO() {
        CustomerRegistrationDTO registrationDTO = new CustomerRegistrationDTO("John Doe", "john@example.com", "+1234567890");
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "john@example.com", "+1234567890");

        when(customerServiceImp.createCustomer(any())).thenReturn(CustomerMapper.INSTANCE.registrationToEntity(registrationDTO));

        CustomerDTO result = customerController.registerNewCustomer(registrationDTO);

        assertEquals(customerDTO.getFullName(), result.getFullName());
        assertEquals(customerDTO.getEmail(), result.getEmail());
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomers() {
        List<CustomerDTO> customerDTOs = Arrays.asList(
                new CustomerDTO(1L, "John Doe", "john@example.com", "+1234567890"),
                new CustomerDTO(2L, "Jane Doe", "jane@example.com", "+0987654321")
        );

        when(customerServiceImp.getAllCustomers()).thenReturn(CustomerMapper.INSTANCE.toEntities(customerDTOs));

        List<CustomerDTO> result = customerController.getAllCustomers();

        assertEquals(2, result.size());
    }

    @Test
    void getCustomer_ShouldReturnCustomerById() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "john@example.com", "+1234567890");

        when(customerServiceImp.getCustomer(1L)).thenReturn(CustomerMapper.INSTANCE.toEntity(customerDTO));

        CustomerDTO result = customerController.getCustomer(1L);

        assertEquals(customerDTO.getId(), result.getId());
    }

    @Test
    void updateCustomer_ShouldReturnUpdatedCustomer() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "john@example.com", "+1234567890");

        when(customerServiceImp.updateCustomer(any(), any())).thenReturn(CustomerMapper.INSTANCE.toEntity(customerDTO));

        CustomerDTO result = customerController.updateCustomer(1L, customerDTO);

        assertEquals(customerDTO.getFullName(), result.getFullName());
        assertEquals(customerDTO.getEmail(), result.getEmail());
    }

    @Test
    void deleteCustomer_ShouldReturnOkResponse() {
        ResponseEntity<?> result = customerController.deleteCustomer(1L);
        assertEquals(ResponseEntity.ok().build(), result);
    }
}
