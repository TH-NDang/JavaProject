package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateConstructionStaffRequest extends CreateStaffRequest {
    @NotBlank(message = "Certification is required")
    private String certification;

    private String equipmentExpertise;
    private LocalDate safetyTrainingExpiry;
}
