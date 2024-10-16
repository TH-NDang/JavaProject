package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class StaffDTO extends UserDTO {
    private String employeeId;
    private String department;
}
