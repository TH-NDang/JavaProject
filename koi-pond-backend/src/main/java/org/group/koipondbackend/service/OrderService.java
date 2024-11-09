package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.dto.order.*;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.entity.OrderHistory;
import org.group.koipondbackend.entity.Services;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.Staff;
import org.group.koipondbackend.entity.enums.OrderStatus;
import org.group.koipondbackend.exception.BadRequestException;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.mapper.OrderMapper;
import org.group.koipondbackend.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ServicesRepository servicesRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public Page<OrderDTO> getAllOrders(String search, OrderStatus status, Pageable pageable) {
        Specification<Order> spec = Specification.where(null);

        if (search != null && !search.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("customer").get("fullName")), "%" + search.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("service").get("name")), "%" + search.toLowerCase() + "%")));
        }

        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }

        return orderRepository.findAll(spec, pageable).map(orderMapper::toDto);
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long id) {
        Order order = findOrderById(id);
        return orderMapper.toDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderHistoryDTO> getOrderHistory(Long orderId) {
        return orderHistoryRepository.findByOrderIdOrderByCreatedAtDesc(orderId)
                .stream()
                .map(orderMapper::toHistoryDto)
                .toList();
    }

    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request, Long customerId) {
        // Get customer
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Get service
        Services service = servicesRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        Order order = orderMapper.createRequestToEntity(request);
        order.setCustomer(customer);
        order.setService(service);
        order.setTotalAmount(service.getPrice()); // You might want to add custom pricing logic here

        order = orderRepository.save(order);

        // Create initial history entry
        createOrderHistory(order, null, OrderStatus.PENDING, "Order created");

        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderDTO updateOrder(Long id, UpdateOrderRequest request) {
        Order order = findOrderById(id);
        OrderStatus oldStatus = order.getStatus();

        if (request.getAssignedStaffId() != null &&
                !request.getAssignedStaffId()
                        .equals(order.getAssignedStaff() != null ? order.getAssignedStaff().getId() : null)) {
            Staff staff = staffRepository.findById(request.getAssignedStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
            order.setAssignedStaff(staff);
        }

        orderMapper.updateEntityFromDto(request, order);

        // If status changed, create history entry
        if (request.getStatus() != null && request.getStatus() != oldStatus) {
            createOrderHistory(order, oldStatus, request.getStatus(), request.getNotes());
        }

        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderDTO cancelOrder(Long id, CancelOrderRequest request) {
        Order order = findOrderById(id);

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new BadRequestException("Cannot cancel completed order");
        }

        OrderStatus oldStatus = order.getStatus();
        order.setStatus(OrderStatus.CANCELLED);
        order.setCancellationReason(request.getCancellationReason());
        order.setCancelledDate(LocalDateTime.now());

        createOrderHistory(order, oldStatus, OrderStatus.CANCELLED, request.getCancellationReason());

        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    private void createOrderHistory(Order order, OrderStatus fromStatus, OrderStatus toStatus, String notes) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        OrderHistory history = OrderHistory.builder()
                .order(order)
                .fromStatus(fromStatus)
                .toStatus(toStatus)
                .changedBy(currentUser)
                .notes(notes)
                .build();

        orderHistoryRepository.save(history);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> getMyOrders(Long userId, Pageable pageable) {
        return orderRepository.findByCustomerId(userId, pageable)
                .map(orderMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> getAssignedOrders(Long staffId, Pageable pageable) {
        return orderRepository.findByAssignedStaffId(staffId, pageable)
                .map(orderMapper::toDto);
    }
}
