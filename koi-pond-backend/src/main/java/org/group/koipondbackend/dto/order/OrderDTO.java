package org.group.koipondbackend.dto.order;

import lombok.Data;
import org.group.koipondbackend.entity.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long serviceId;
    private String serviceName;
    private Long assignedStaffId;
    private String assignedStaffName;
    private OrderStatus status;
    private Double totalAmount;
    private String requirements;
    private List<String> customRequests;
    private String location;
    private String locationNotes;
    private LocalDateTime preferredStartDate;
    private LocalDateTime actualStartDate;
    private LocalDateTime completionDate;
    private LocalDateTime cancelledDate;
    private String cancellationReason;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
