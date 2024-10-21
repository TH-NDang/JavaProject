package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.mapper.OrderMapper;
import org.group.koipondbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getOrdersByProjectId(Long projectId) {
        List<Order> orders = orderRepository.findByProjectId(projectId);
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
