package org.group.koipondbackend.dto.staff;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStaffStatusRequest {
    @NotBlank(message = "Status is required")
    private String status;

    private String reason;
}
