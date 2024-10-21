package org.group.koipondbackend.dto;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CONSULTING")
@Data
@NoArgsConstructor
public class ConsultingStaffDTO extends StaffDTO {
    private String specialization;
}
