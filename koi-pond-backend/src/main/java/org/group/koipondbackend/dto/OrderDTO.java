package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;              // ID của đơn hàng
    private Long projectId;      // ID của dự án liên quan
    private String orderDetails;  // Chi tiết đơn hàng
    private Double amount;        // Số tiền của đơn hàng
    // Thêm các thuộc tính khác nếu cần
}