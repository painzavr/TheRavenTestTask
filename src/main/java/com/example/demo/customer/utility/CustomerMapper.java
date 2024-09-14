package com.example.demo.customer.utility;

import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.CustomerRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "active", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "active", ignore = true)
    Customer registrationToEntity(CustomerRegistrationDTO customerRegistrationDTO);

    List<CustomerDTO> toDTOs(List<Customer> customers);

    List<Customer> toEntities(List<CustomerDTO> customerDTOs);
}
