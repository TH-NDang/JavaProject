package org.group.koipondbackend.dto.order;

import lombok.Data;
import org.group.koipondbackend.entity.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateOrderRequest {
    private Long assignedStaffId;
    private OrderStatus status;
    private String requirements;
    private List<String> customRequests;
    private String location;
    private String locationNotes;
    private LocalDateTime preferredStartDate;
    private LocalDateTime actualStartDate;
    private LocalDateTime completionDate;
    private String notes;
}
