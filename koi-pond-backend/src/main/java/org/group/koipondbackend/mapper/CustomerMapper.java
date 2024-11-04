package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public interface CustomerMapper {
    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}
