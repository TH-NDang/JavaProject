package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.mapper.OrderMapper; // Nếu bạn có mapper
import org.group.koipondbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper; // Nếu bạn có mapper

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper; // Nếu bạn có mapper
    }

    public List<OrderDTO> getOrdersByProjectId(Long projectId) {
        List<Order> orders = orderRepository.findByProjectId(projectId);
        return orders.stream()
                .map(orderMapper::toDto) // Nếu bạn có mapper
                .collect(Collectors.toList());
    }
}