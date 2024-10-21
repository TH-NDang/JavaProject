package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    // Thêm các thuộc tính khác nếu cần
}