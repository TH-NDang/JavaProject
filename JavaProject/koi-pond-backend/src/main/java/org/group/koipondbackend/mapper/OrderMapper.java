package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setProjectId(entity.getProjectId());
        dto.setOrderDetails(entity.getOrderDetails());
        dto.setAmount(entity.getAmount());
        dto.setCreatedDate(entity.getCreatedDate());  // thêm ánh xạ này

        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setProjectId(dto.getProjectId());
        entity.setOrderDetails(dto.getOrderDetails());
        entity.setAmount(dto.getAmount());
        entity.setCreatedDate(dto.getCreatedDate());  // thêm xạ này

        return entity;
    }

    public List<OrderDTO> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
