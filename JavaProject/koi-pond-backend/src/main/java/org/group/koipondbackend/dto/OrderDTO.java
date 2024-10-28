package org.group.koipondbackend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private String orderCode;     // Mã đơn hàng
    private String orderName;      // Tên đơn hàng
    private String status;         // Trạng thái đơn hàng
    private LocalDateTime orderDate; // Ngày tạo đơn hàng
    private Double totalAmount;    // Tổng giá trị đơn hàng
    private Long projectId;        // ID dự án liên quan
}
