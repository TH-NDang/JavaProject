package org.group.koipondbackend.dto;

import org.group.koipondbackend.dto.user.UserDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StaffDTO extends UserDTO {
    private String employeeId;
    private String department;
}
