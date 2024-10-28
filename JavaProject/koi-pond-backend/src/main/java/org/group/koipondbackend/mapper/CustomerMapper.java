package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO toDto(Customer entity) {
        if (entity == null) return null;

        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());

        return dto;
    }

    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) return null;

        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());

        return entity;
    }
}
