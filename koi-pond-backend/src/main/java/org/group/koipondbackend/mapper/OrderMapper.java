package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDto(Order order);

    Order toEntity(OrderDTO orderDTO);
}
