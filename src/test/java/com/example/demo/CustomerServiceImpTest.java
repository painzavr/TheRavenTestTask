package com.example.demo;

import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.exception.EntityNotFoundException;
import com.example.demo.customer.exception.InvalidArgumentException;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.customer.service.implementation.CustomerServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceImpTest {

    @MockBean
    private CustomerRepository customerRepository; // Mock the repository

    @Autowired
    private CustomerServiceImp customerServiceImp; // Inject the service with mocked dependencies

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // Test for creating a customer
    @Test
    void createCustomer_ShouldReturnNewCustomer() {
        // Arrange
        Customer customer = new Customer(null, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Doe", "john@example.com", "1234567890", true);

        // Mock the save method of the repository
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Act
        Customer result = customerServiceImp.createCustomer(customer);

        // Assert
        assertEquals(customer.getFullName(), result.getFullName());
        verify(customerRepository, times(1)).save(any(Customer.class)); // Ensure save was called
    }

    // Test for getting all customers
    @Test
    void getAllCustomers_ShouldReturnListOfCustomers() {
        // Arrange
        List<Customer> customers = Arrays.asList(
                new Customer(1L, System.currentTimeMillis(), System.currentTimeMillis(),
                        "John Doe", "john@example.com", "1234567890", true),
                new Customer(2L, System.currentTimeMillis(), System.currentTimeMillis(),
                        "Jane Doe", "jane@example.com", "0987654321", true)
        );

        // Mock the findAll method of the repository
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerServiceImp.getAllCustomers();

        // Assert
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll(); // Ensure findAll was called
    }

    // Test for getting a customer by ID when the customer exists
    @Test
    void getCustomer_ShouldReturnCustomer_WhenCustomerExists() {
        // Arrange
        Customer customer = new Customer(1L, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Doe", "john@example.com", "1234567890", true);

        // Mock the findById method of the repository
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerServiceImp.getCustomer(1L);

        // Assert
        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
        verify(customerRepository, times(1)).findById(1L); // Ensure findById was called with correct ID
    }

    // Test for getting a customer by ID when the customer does not exist
    @Test
    void getCustomer_ShouldThrowException_WhenCustomerDoesNotExist() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> customerServiceImp.getCustomer(1L));
        verify(customerRepository, times(1)).findById(1L); // Ensure findById was called
    }

    // Test for updating a customer when the IDs match and the customer exists
    @Test
    void updateCustomer_ShouldReturnUpdatedCustomer_WhenIDsMatch() {
        // Arrange
        Customer existingCustomer = new Customer(1L, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Doe", "john@example.com", "1234567890", true);

        Customer updatedCustomer = new Customer(1L, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Smith", "john@example.com", "0987654321", true);

        // Mock the findById and save methods of the repository
        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        // Act
        Customer result = customerServiceImp.updateCustomer(1L, updatedCustomer);

        // Assert
        assertEquals("John Smith", result.getFullName());
        assertEquals("0987654321", result.getPhone());
        verify(customerRepository, times(1)).findById(1L); // Ensure findById was called
        verify(customerRepository, times(1)).save(any(Customer.class)); // Ensure save was called
    }

    // Test for updating a customer when the IDs do not match
    @Test
    void updateCustomer_ShouldThrowException_WhenIDsDoNotMatch() {
        Customer customer = new Customer(1L, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Deer", "joh123123n@example.com", "+0987654321", true);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer updatedCustomer = new Customer(2L, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Smith", "john@example.com", "+0987654321", true);

        // Act & Assert
        assertThrows(InvalidArgumentException.class, () -> customerServiceImp.updateCustomer(1L, updatedCustomer));
        verify(customerRepository, never()).save(any(Customer.class)); // Ensure save was never called
    }

    // Test for deleting a customer by ID
    @Test
    void deleteCustomer_ShouldDeactivateCustomer_WhenCustomerExists() {
        // Arrange
        Customer customer = new Customer(1L, System.currentTimeMillis(), System.currentTimeMillis(),
                "John Doe", "john@example.com", "1234567890", true);

        // Mock the findById method of the repository
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        customerServiceImp.deleteCustomer(1L);

        // Assert
        assertFalse(customer.isActive()); // Ensure the customer is deactivated
        verify(customerRepository, times(1)).findById(1L); // Ensure findById was called
        verify(customerRepository, times(1)).save(customer); // Ensure save was called
    }

    // Test for deleting a customer when the customer does not exist
    @Test
    void deleteCustomer_ShouldThrowException_WhenCustomerDoesNotExist() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> customerServiceImp.deleteCustomer(1L));
        verify(customerRepository, times(1)).findById(1L); // Ensure findById was called
    }
}
