package com.example.demo.customer.repository;

import com.example.demo.customer.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.email =?1")
    Optional<Customer> findCustomerByEmail(String email);
}
