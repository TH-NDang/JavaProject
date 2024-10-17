package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper extends BaseMapper<Customer, CustomerDTO> {
    @Override
    public CustomerDTO toDto(Customer entity) {
        if (entity == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setFullName(entity.getFullName());
        dto.setPassword(entity.getPassword()); // Cẩn thận với việc tiết lộ mật khẩu
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());

        return dto;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setFullName(dto.getFullName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());

        return entity;
    }

    @Override
    public List<CustomerDTO> toDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}