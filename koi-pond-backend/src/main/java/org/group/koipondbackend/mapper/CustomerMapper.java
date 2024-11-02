package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerDTO toDto(Customer c) {
        return new CustomerDTO(c.getId(), c.getUsername(), c.getFullName(), c.getEmail(), c.getPhoneNumber(), c.getAddress());
    }

    public Customer toEntity(CustomerDTO dto) {
        Customer c = new Customer();
        c.setId(dto.getId());
        c.setUsername(dto.getUsername());
        c.setFullName(dto.getFullName());
        c.setEmail(dto.getEmail());
        c.setPhoneNumber(dto.getPhoneNumber());
        c.setAddress(dto.getAddress());
        return c;
    }

    public List<CustomerDTO> toDtoList(List<Customer> customers) {
        return customers.stream().map(this::toDto).collect(Collectors.toList());
    }
}
