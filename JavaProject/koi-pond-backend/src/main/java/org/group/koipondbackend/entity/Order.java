package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderCode;
    private String status;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private Double totalAmount;

    @Column(name = "project_id")
    private Long projectId;
}
