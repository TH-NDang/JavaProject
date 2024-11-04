package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DesignStaffDto extends StaffDto {
    private String designSpecialization;
    private String softwareSkills;
    private String portfolioUrl;
}
