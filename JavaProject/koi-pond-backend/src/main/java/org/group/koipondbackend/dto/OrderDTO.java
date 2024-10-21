package org.group.koipondbackend.dto;

import lombok.Data;
import java.time.LocalDateTime;  // Thêm import này

@Data
public class OrderDTO {
    private Long id;
    private Long projectId;
    private String orderDetails;
    private Double amount;
    private LocalDateTime createdDate;  // Thêm trường này
}
