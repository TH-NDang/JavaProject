package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.mapper.OrderMapper;
import org.group.koipondbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDto(orders);
    }

    public Optional<OrderDTO> findById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDto);
    }

    public OrderDTO create(OrderDTO orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    public OrderDTO update(OrderDTO orderDto) {
        if (orderRepository.existsById(orderDto.getId())) {
            Order order = orderMapper.toEntity(orderDto);
            Order updatedOrder = orderRepository.save(order);
            return orderMapper.toDto(updatedOrder);
        }
        return null; // Hoặc bạn có thể ném một exception ở đây
    }

    public void delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }

    public List<ServiceDTO> getServicesByOrderId(Long id) {
        // Giả sử có một phương thức để lấy dịch vụ theo đơn hàng
        // Chưa có implementation chi tiết ở đây
    }

    public void updateStatus(Long id, String newStatus) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(newStatus);
            orderRepository.save(order);
        }
    }
}
