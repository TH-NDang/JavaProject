package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO> {

    CustomerDTO toDto(Customer entity);

    Customer toEntity(CustomerDTO dto);
}
