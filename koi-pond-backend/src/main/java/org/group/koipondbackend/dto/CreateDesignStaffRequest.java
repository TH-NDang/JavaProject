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
public class CreateDesignStaffRequest extends CreateStaffRequest {
    @NotBlank(message = "Design specialization is required")
    private String designSpecialization;

    private String softwareSkills;
    private String portfolioUrl;
}
