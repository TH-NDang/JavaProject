package org.group.koipondbackend.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull(message = "Service ID is required")
    private Long serviceId;

    private String requirements;
    private List<String> customRequests;

    @NotNull(message = "Location is required")
    private String location;
    private String locationNotes;

    @Future(message = "Preferred start date must be in the future")
    private LocalDateTime preferredStartDate;
}
