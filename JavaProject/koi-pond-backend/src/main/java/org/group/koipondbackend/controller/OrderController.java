package org.group.koipondbackend.controller;

import java.util.List;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.service.impl.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.group.koipondbackend.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDto) {
        OrderDTO createdOrder = orderService.create(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        orderDto.setId(id);
        OrderDTO updatedOrder = orderService.update(orderDto);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceDTO>> getOrderServices(@PathVariable Long id) {
        // Giả định có hàm lấy danh sách dịch vụ của Order
        List<ServiceDTO> services = orderService.findServicesByOrderId(id);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        OrderDTO updatedOrder = orderService.updateStatus(id, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
