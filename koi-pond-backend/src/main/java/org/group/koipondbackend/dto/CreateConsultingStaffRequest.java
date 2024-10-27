package org.group.koipondbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateConsultingStaffRequest extends CreateStaffRequest {
    @NotBlank(message = "Specialization is required")
    private String specialization;

    private Integer experienceYears;
}
