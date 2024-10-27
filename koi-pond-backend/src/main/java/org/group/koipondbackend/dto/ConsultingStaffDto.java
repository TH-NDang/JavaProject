package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConsultingStaffDto extends StaffDto {
    private String specialization;
    private Integer experienceYears;
    private Integer customersHandled;
}
