package org.group.koipondbackend.dto.contact;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UpdateContactRequest {
    @NotBlank(message = "Status is required")
    private String status;
    
    private String notes;
}
