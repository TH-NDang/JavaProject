package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends BaseMapper<Order, OrderDTO> {
    @Override
    public OrderDTO toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        return new OrderDTO(
                entity.getId(),
                entity.getOrderCode(),
                entity.getStatus(),
                entity.getOrderDate(),
                entity.getTotalAmount(),
                entity.getProjectId()
        );
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Order(
                dto.getId(),
                dto.getOrderCode(),
                dto.getStatus(),
                dto.getOrderDate(),
                dto.getTotalAmount(),
                dto.getProjectId()
        );
    }
}
