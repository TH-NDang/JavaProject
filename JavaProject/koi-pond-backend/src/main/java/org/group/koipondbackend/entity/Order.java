package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;  // Thêm import này

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;      // ID của dự án liên quan
    private String orderDetails;  // Chi tiết đơn hàng
    private Double amount;        // Số tiền của đơn hàng
    private LocalDateTime createdDate;  // Thêm trường này

    // Getters and Setters nếu cần
}
