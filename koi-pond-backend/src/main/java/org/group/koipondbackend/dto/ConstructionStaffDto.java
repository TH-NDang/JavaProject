package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConstructionStaffDto extends StaffDto {
    private String certification;
    private String equipmentExpertise;
    private LocalDate safetyTrainingExpiry;
}
