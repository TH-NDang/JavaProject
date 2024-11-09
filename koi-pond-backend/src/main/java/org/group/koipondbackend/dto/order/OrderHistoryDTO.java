package org.group.koipondbackend.dto.order;

import lombok.Builder;
import lombok.Data;
import org.group.koipondbackend.entity.enums.OrderStatus;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderHistoryDTO {
    private Long id;
    private Long orderId;
    private OrderStatus fromStatus;
    private OrderStatus toStatus;
    private Long changedById;
    private String changedByName;
    private String notes;
    private LocalDateTime createdAt;
}
