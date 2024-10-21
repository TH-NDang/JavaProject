package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DesignStaffDTO extends StaffDTO {
    private String designSoftware;
}
