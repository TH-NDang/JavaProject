// src/main/java/org/group/koipondbackend/controller/OrderController.java
package org.group.koipondbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.dto.order.*;
import org.group.koipondbackend.entity.enums.OrderStatus;
import org.group.koipondbackend.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) OrderStatus status) {
        return ResponseEntity.ok(orderService.getAllOrders(search, status, PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/{id}/history")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<OrderHistoryDTO>> getOrderHistory(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderHistory(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderDTO> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            Authentication authentication) {
        // Assuming you have a way to get user id from authentication
        Long userId = getUserIdFromAuthentication(authentication);
        return ResponseEntity.ok(orderService.createOrder(request, userId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(
            @PathVariable Long id,
            @Valid @RequestBody CancelOrderRequest request,
            Authentication authentication) {
        // Check if user is authorized to cancel the order
        OrderDTO order = orderService.getOrderById(id);
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        boolean isOwner = order.getCustomerId().equals(getUserIdFromAuthentication(authentication));

        if (!isAdmin && !isOwner) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(orderService.cancelOrder(id, request));
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Page<OrderDTO>> getMyOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        return ResponseEntity.ok(orderService.getMyOrders(userId, PageRequest.of(page, size)));
    }

    @GetMapping("/assigned-orders")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Page<OrderDTO>> getAssignedOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        Long staffId = getUserIdFromAuthentication(authentication);
        return ResponseEntity.ok(orderService.getAssignedOrders(staffId, PageRequest.of(page, size)));
    }

    // Helper method to get user ID from authentication
    private Long getUserIdFromAuthentication(Authentication authentication) {
        // Implement based on your authentication setup
        return 1L; // Placeholder
    }
}
